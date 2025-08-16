package com.hemanttechie.service.impl;

import com.hemanttechie.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("razorpay")
@Slf4j
public class RazorpayPaymentService implements PaymentService {

    @Override
    public String pay(String amount, String mode, String sender, String receiver) {
      //  log.info("Processing payment with Razorpay: amount={}, mode={}, sender={}, receiver={}", amount, mode, sender, receiver);
        return "paid with razorpay " + amount + " from " + sender + " to " + receiver + " using mode " + mode;
    }
}
