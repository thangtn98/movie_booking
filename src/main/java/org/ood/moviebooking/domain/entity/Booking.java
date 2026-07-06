package org.ood.moviebooking.domain.entity;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class Booking {
    private final String bookingId;
    private final Customer customer;
    private final Show show;
    private final List<ShowSeat> seats;
    private BookingStatus status;
    private BigDecimal amount;

    public Booking(String bookingId, Customer customer, Show show, List<ShowSeat> seats) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.show = show;
        this.seats = seats;
        this.status = BookingStatus.PENDING;
    }

    public void confirm(BigDecimal amount) {
        this.amount = amount;
        this.status = BookingStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
    }

    public void expire() {
        this.status = BookingStatus.EXPIRED;
    }
}
