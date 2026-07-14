package org.ood.moviebooking.infrastructure.adapter.payment;

import org.ood.moviebooking.application.port.out.PaymentStrategy;
import org.ood.moviebooking.domain.entity.PaymentResult;

import java.math.BigDecimal;

public class EWalletPayment implements PaymentStrategy {
    @Override
    public PaymentResult pay(BigDecimal amount) {
        // TODO: gọi API ví điện tử thật (Momo/ZaloPay SDK)
        return new PaymentResult(true, "Charged " + amount + " via e-wallet");
    }

    @Override
    public PaymentResult refund(BigDecimal amount) {
        return new PaymentResult(true, "Refunded " + amount + " to e-wallet");
    }
}
