package com.hemanttechie.service.impl;

import com.hemanttechie.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("paypal")
@Log4j2
public class PaypalPaymentService implements PaymentService {

    @Override
    public String pay(String amount, String mode, String sender, String receiver) {
        //log.info("Processing payment with PayPal: amount={}, mode={}, sender={}, receiver={}", amount, mode, sender, receiver);
        return "paid with paypal " + amount + " from " + sender + " to " + receiver + " using mode " + mode;
    }
}
