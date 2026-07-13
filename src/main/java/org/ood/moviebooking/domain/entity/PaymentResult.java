package org.ood.moviebooking.domain.entity;

import lombok.Getter;

@Getter
public class PaymentResult {
    private final boolean success;
    private final String message;

    public PaymentResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
