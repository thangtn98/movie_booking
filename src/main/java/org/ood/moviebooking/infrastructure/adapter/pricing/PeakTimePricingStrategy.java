package org.ood.moviebooking.infrastructure.adapter.pricing;

import org.ood.moviebooking.application.port.out.PricingStrategy;
import org.ood.moviebooking.domain.entity.Show;
import org.ood.moviebooking.domain.entity.ShowSeat;

import java.math.BigDecimal;
import java.util.List;

public class PeakTimePricingStrategy implements PricingStrategy {
    private static final BigDecimal PEAK_MULTIPLIER = new BigDecimal("1.2");

    @Override
    public BigDecimal applyPrice(List<ShowSeat> seats, Show show, BigDecimal input) {
        int hour = show.getStartTime().getHour();
        return isPeakHour(hour) ? input.multiply(PEAK_MULTIPLIER) : input;
    }

    private boolean isPeakHour(int hour) {
        return hour >= 18 && hour <= 22;
    }
}
