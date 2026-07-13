package org.ood.moviebooking.domain.entity;

import lombok.Getter;

import java.time.Duration;
import java.time.Instant;

public class SeatLock {
    @Getter
    private final ShowSeat seat;
    @Getter
    private final String lockedBy;
    private final Instant lockedAt;
    private final Duration timeout;

    public SeatLock(ShowSeat seat, String lockedBy, Instant lockedAt, Duration timeout) {
        this.seat = seat;
        this.lockedBy = lockedBy;
        this.lockedAt = lockedAt;
        this.timeout = timeout;
    }

    public boolean isExpired() {
        return lockedAt.plus(timeout).isBefore(Instant.now());
    }
}
