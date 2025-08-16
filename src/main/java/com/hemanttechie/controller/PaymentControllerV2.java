package com.hemanttechie.controller;

import com.hemanttechie.dto.PaymentRequest;
import com.hemanttechie.service.PaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/payment/v2")
//@Log4j2
public class PaymentControllerV2 {

    private static final Logger log = Logger.getAnonymousLogger();

    private final Map<String, PaymentService> paymentServicesMap;

    public PaymentControllerV2(Map<String, PaymentService> paymentServicesMap) {
        this.paymentServicesMap = paymentServicesMap;
    }

    @PostMapping("/pay/v2")
    public String pay(@RequestBody PaymentRequest paymentRequest) {

        String paymentType = paymentRequest.paymentType().toLowerCase();

        PaymentService service = paymentServicesMap.get(paymentType);
         log.info(paymentType+": Injected bean type:"+service);

        if (service == null) {
            throw new IllegalArgumentException("Unsupported payment mode: " + paymentType);
        }
        return service.pay(
                paymentRequest.amount(),
                paymentType,
                paymentRequest.sender(),
                paymentRequest.receiver()
        );

    }
}
