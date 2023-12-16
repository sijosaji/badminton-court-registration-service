package com.justplay.badminton.model;

import com.justplay.badminton.enums.PaymentMethod;
import com.justplay.badminton.enums.PaymentMode;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentDetail {
    private UUID id;
    private PaymentMode paymentMode;
    private PaymentMethod paymentMethod;
    private String transactionId;
    private BigDecimal amount;
}
