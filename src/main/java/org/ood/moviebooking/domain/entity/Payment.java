package org.ood.moviebooking.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
public class Payment {
    private final String paymentId;
    private final BigDecimal amount;

    @Setter
    private PaymentResult result;

    public Payment(String paymentId, BigDecimal amount) {
        this.paymentId = paymentId;
        this.amount = amount;
    }
}
