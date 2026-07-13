package org.ood.moviebooking.application.port.out;

import org.ood.moviebooking.domain.entity.PaymentResult;

import java.math.BigDecimal;

public interface PaymentStrategy {
    PaymentResult pay(BigDecimal amount);

    PaymentResult refund(BigDecimal amount);
}
