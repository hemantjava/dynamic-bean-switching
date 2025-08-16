package com.hemanttechie.dto;

import lombok.Builder;
import lombok.Data;

@Builder
public record PaymentRequest(String amount, String paymentType, String sender, String receiver){

}

