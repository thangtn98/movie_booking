package org.ood.moviebooking.domain.entity;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ShowSeat {
    private final Seat seat;
    private ShowSeatStatus status;
    private BigDecimal price;

    public ShowSeat(Seat seat, BigDecimal price) {
        this.seat = seat;
        this.price = price;
        this.status = ShowSeatStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return status == ShowSeatStatus.AVAILABLE;
    }

    public void reserve() {
        if (status != ShowSeatStatus.AVAILABLE) {
            throw new IllegalStateException("Seat " + seat.getSeatId() + " is not available");
        }
        this.status = ShowSeatStatus.RESERVED;
    }

    public void book() {
        this.status = ShowSeatStatus.BOOKED;
    }

    public void release() {
        this.status = ShowSeatStatus.AVAILABLE;
    }
}
