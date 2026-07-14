package org.ood.moviebooking.infrastructure.adapter.pricing;

import org.ood.moviebooking.application.port.out.PricingStrategy;
import org.ood.moviebooking.domain.entity.Show;
import org.ood.moviebooking.domain.entity.ShowSeat;

import java.math.BigDecimal;
import java.util.List;

public class BasePricingStrategy implements PricingStrategy {
    private static final BigDecimal REGULAR_RATE = new BigDecimal("90000");
    private static final BigDecimal PREMIUM_RATE = new BigDecimal("130000");
    private static final BigDecimal RECLINER_RATE = new BigDecimal("180000");

    @Override
    public BigDecimal applyPrice(List<ShowSeat> seats, Show show, BigDecimal input) {
        BigDecimal total = input;
        for (ShowSeat seat : seats) {
            BigDecimal price = switch (seat.getSeat().getType()) {
                case PREMIUM -> PREMIUM_RATE;
                case RECLINER -> RECLINER_RATE;
                default -> REGULAR_RATE;
            };
            total = total.add(price);
        }
        return total;
    }
}
