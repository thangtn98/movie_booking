package org.ood.moviebooking.application.port.out;

import org.ood.moviebooking.domain.entity.Show;
import org.ood.moviebooking.domain.entity.ShowSeat;

import java.math.BigDecimal;
import java.util.List;

public interface PricingStrategy {
    BigDecimal applyPrice(List<ShowSeat> seats, Show show, BigDecimal input);
}
