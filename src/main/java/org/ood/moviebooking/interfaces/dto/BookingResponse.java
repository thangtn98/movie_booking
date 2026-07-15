package org.ood.moviebooking.interfaces.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class BookingResponse {
    public String bookingId;
    public String status;
    public BigDecimal amount;
}
