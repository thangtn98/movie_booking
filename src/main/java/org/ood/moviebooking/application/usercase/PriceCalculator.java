package org.ood.moviebooking.application.usercase;

import lombok.RequiredArgsConstructor;
import org.ood.moviebooking.application.port.out.PricingStrategy;
import org.ood.moviebooking.domain.entity.Show;
import org.ood.moviebooking.domain.entity.ShowSeat;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class PriceCalculator {
    private final List<PricingStrategy> strategies;

    public BigDecimal calculate(List<ShowSeat> seats, Show show) {
        BigDecimal fare = BigDecimal.ZERO;
        for (PricingStrategy strategy : strategies) {
            fare = strategy.applyPrice(seats, show, fare);
        }
        return fare;
    }
}
