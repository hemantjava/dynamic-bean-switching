package com.hemanttechie.controller;

import com.hemanttechie.dto.PaymentRequest;
import com.hemanttechie.service.impl.PaypalPaymentService;
import com.hemanttechie.service.impl.RazorpayPaymentService;
import com.hemanttechie.service.impl.StripePaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    public static final String PAYPAL = "paypal";
    public static final String RAZORPAY = "razorpay";
    public static final String STRIPE = "stripe";

    private final PaypalPaymentService paypalPaymentService;
    private final RazorpayPaymentService razorpayPaymentService;
    private final StripePaymentService stripePaymentService;

    public PaymentController(PaypalPaymentService paypalPaymentService, RazorpayPaymentService razorpayPaymentService,
                             StripePaymentService stripePaymentService) {
        this.paypalPaymentService = paypalPaymentService;
        this.razorpayPaymentService = razorpayPaymentService;
        this.stripePaymentService = stripePaymentService;
    }


    @PostMapping("/pay")
    public String pay(@RequestBody PaymentRequest paymentRequest) {

        String amount = paymentRequest.amount();
        String paymentType = paymentRequest.paymentType();
        String sender = paymentRequest.sender();
        String receiver = paymentRequest.receiver();

        return switch (paymentType.toLowerCase()) {
            case PAYPAL ->
                    paypalPaymentService.pay(amount, paymentType, sender, receiver);
            case RAZORPAY ->
                    razorpayPaymentService.pay(amount, paymentType, sender, receiver);
            case STRIPE ->
                    stripePaymentService.pay(amount, paymentType, sender, receiver);
            default ->
                    throw new IllegalArgumentException("Unsupported payment mode: " + paymentType);
        };
    }
}
