package rs.ac.bg.etf.webphoto.service.impl;

import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCaptureRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.payments.CapturesRefundRequest;
import com.paypal.payments.Refund;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.webphoto.model.Invoice;
import rs.ac.bg.etf.webphoto.model.dto.PaymentCheckoutResponseDto;
import rs.ac.bg.etf.webphoto.model.dto.PaymentCommitRequestDto;

import java.io.IOException;

@Service
public class PaypalIntegrationServiceImpl extends AbstractPaypalProxy {

    public PaypalIntegrationServiceImpl(PayPalHttpClient client) {
        super(client);
    }

    @Override
    public PaymentCheckoutResponseDto preparePayment(Invoice invoice) {
        try {
            OrdersCreateRequest request = new OrdersCreateRequest();
            request.prefer("return=minimal");
            request.requestBody(buildOrderRequest(invoice));
            HttpResponse<Order> response = client.execute(request);
            return new PaymentCheckoutResponseDto(response.result().id());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean capturePayment(PaymentCommitRequestDto paymentCommitRequest) {
        OrdersCaptureRequest request = new OrdersCaptureRequest(paymentCommitRequest.getOrderId());
        request.requestBody(new OrderRequest());
        try {
            HttpResponse<Order> response = client.execute(request);
            if(response.statusCode() == 201) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean refundPayment(Invoice invoice) {
        CapturesRefundRequest request = new CapturesRefundRequest(invoice.getOrderId());
        request.prefer("return=representation");
        request.requestBody(buildRefundRequest(invoice));
        try {
            HttpResponse<Refund> response = client.execute(request);
            if(response.statusCode() == 201) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
