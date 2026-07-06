package org.ood.moviebooking.domain.entity;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class Seat {
    private final String seatId;
    private final int row;
    private final int number;
    private final SeatType type;

    public Seat(String seatId, int row, int number, SeatType type) {
        this.seatId = Objects.requireNonNull(seatId);
        this.row = row;
        this.number = number;
        this.type = Objects.requireNonNull(type);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Seat seat)) return false;
        return Objects.equals(seatId, seat.seatId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(seatId);
    }
}
