package rs.ac.bg.etf.webphoto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.etf.webphoto.model.Invoice;
import rs.ac.bg.etf.webphoto.model.dto.CartItemDto;
import rs.ac.bg.etf.webphoto.model.dto.PaymentCheckoutResponseDto;
import rs.ac.bg.etf.webphoto.model.dto.PaymentCommitRequestDto;
import rs.ac.bg.etf.webphoto.model.enums.InvoiceStatus;
import rs.ac.bg.etf.webphoto.service.CartService;
import rs.ac.bg.etf.webphoto.service.InvoiceService;
import rs.ac.bg.etf.webphoto.service.PaymentService;
import rs.ac.bg.etf.webphoto.service.PaypalIntegrationService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final CartService cartService;

    private final InvoiceService invoiceService;

    private final PaypalIntegrationService paypalIntegrationService;

    @Override
    @Transactional
    public PaymentCheckoutResponseDto checkout(Principal principal, HttpSession session) {
        // step 1: fetch user cart
        List<CartItemDto> cartItems = cartService.findCart(session);
        // step 2: create invoice
        Invoice invoice = invoiceService.createInvoice(cartItems, principal);
        // step 3: checkout - create order on paypal
        PaymentCheckoutResponseDto checkoutResponse = paypalIntegrationService.preparePayment(invoice);
        // step 4: set paypal_internal_id to invoice and update
        invoice.setOrderId(checkoutResponse.getId());
        invoiceService.update(invoice);
        return checkoutResponse;
    }

    @Override
    @Transactional
    public void commit(PaymentCommitRequestDto paymentCommitRequest, Principal principal) {
        // step 1: fetch invoice
        Invoice invoice = invoiceService.findByOrderId(paymentCommitRequest.getOrderId());
        if(!InvoiceStatus.IN_PROGRESS.equals(invoice.getStatus())) {
            throw new RuntimeException("Only in_progress transactions can be committed");
        }
        // step 2: check result of transaction with pay-pal api
        if(paypalIntegrationService.capturePayment(paymentCommitRequest)) {
            // step 3a: if transaction is successfully realized, change status and commit state, notify customer
            invoice.setStatus(InvoiceStatus.REALIZED);
            // todo: send email -> product delivery service
        } else {
            // step 3b: if transaction isn't successfully realized, change status to FAILED
            invoice.setStatus(InvoiceStatus.FAILED);
        }
        invoiceService.update(invoice);
    }

    @Override
    @Transactional
    public void cancel(String orderId, Principal principal) {
        Invoice invoice = invoiceService.findByOrderId(orderId);
        if(!InvoiceStatus.IN_PROGRESS.equals(invoice.getStatus()))
            throw new RuntimeException("Only transactions that have not been executed in full can be canceled!");
        invoice.setStatus(InvoiceStatus.FAILED);
        invoiceService.update(invoice);
    }

    @Override
    @Transactional
    public void refund(String orderId, Principal principal) {
        Invoice invoice = invoiceService.findByOrderId(orderId);
        if(!InvoiceStatus.REALIZED.equals(invoice.getStatus()))
            throw new RuntimeException("Only realized transactions can be refunded");
        if(paypalIntegrationService.refundPayment(invoice)) {
            invoice.setStatus(InvoiceStatus.REFUNDED);
            invoiceService.update(invoice);
        }
    }
}
