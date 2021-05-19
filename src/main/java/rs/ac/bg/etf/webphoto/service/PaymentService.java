package rs.ac.bg.etf.webphoto.service;

import rs.ac.bg.etf.webphoto.model.dto.PaymentCheckoutResponseDto;
import rs.ac.bg.etf.webphoto.model.dto.PaymentCommitRequestDto;

import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface PaymentService {

    PaymentCheckoutResponseDto checkout(Principal principal, HttpSession session);

    void commit(PaymentCommitRequestDto paymentCommitRequest, Principal principal);

    void cancel(String orderId, Principal principal);

    void refund(String orderId, Principal principal);

}
