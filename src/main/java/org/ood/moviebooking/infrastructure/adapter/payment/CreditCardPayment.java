package org.ood.moviebooking.infrastructure.adapter.payment;

import org.ood.moviebooking.application.port.out.PaymentStrategy;
import org.ood.moviebooking.domain.entity.PaymentResult;

import java.math.BigDecimal;

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public PaymentResult pay(BigDecimal amount) {
        // TODO: gọi cổng thanh toán thẻ thật (Stripe/VNPay SDK)
        return new PaymentResult(true, "Charged " + amount + " via credit card");
    }

    @Override
    public PaymentResult refund(BigDecimal amount) {
        return new PaymentResult(true, "Refunded " + amount + " to credit card");
    }
}
