package rs.ac.bg.etf.webphoto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.webphoto.model.dto.PaymentCheckoutResponseDto;
import rs.ac.bg.etf.webphoto.model.dto.PaymentCommitRequestDto;
import rs.ac.bg.etf.webphoto.service.PaymentService;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/checkout")
    public PaymentCheckoutResponseDto checkout(Principal principal, HttpSession session) {
        return paymentService.checkout(principal, session);
    }

    @PutMapping("/commit")
    public void commit(@Validated @RequestBody PaymentCommitRequestDto paymentCommitRequest, Principal principal) {
        paymentService.commit(paymentCommitRequest, principal);
    }

    @PutMapping("/cancel/{orderId}")
    public void cancel(@PathVariable String orderId, Principal principal) {
        paymentService.cancel(orderId, principal);
    }

    @PutMapping("/refund/{orderId}")
    public void refund(@PathVariable String orderId, Principal principal) {
        paymentService.refund(orderId, principal);
    }

}
