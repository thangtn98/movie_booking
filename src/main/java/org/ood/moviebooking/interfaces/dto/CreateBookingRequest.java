package org.ood.moviebooking.interfaces.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateBookingRequest {
    public String customerId;
    public String showId;
    public List<String> seatIds;
}
