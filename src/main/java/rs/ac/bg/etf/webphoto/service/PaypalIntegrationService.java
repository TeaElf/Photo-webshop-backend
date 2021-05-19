package rs.ac.bg.etf.webphoto.service;

import rs.ac.bg.etf.webphoto.model.Invoice;
import rs.ac.bg.etf.webphoto.model.dto.PaymentCheckoutResponseDto;
import rs.ac.bg.etf.webphoto.model.dto.PaymentCommitRequestDto;

public interface PaypalIntegrationService {

    PaymentCheckoutResponseDto preparePayment(Invoice invoice);

    boolean capturePayment(PaymentCommitRequestDto paymentCommitRequest);

    boolean refundPayment(Invoice invoice);

}
