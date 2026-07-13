package org.ood.moviebooking.infrastructure.adapter.lock;

import lombok.RequiredArgsConstructor;
import org.ood.moviebooking.application.exception.SeatUnavailableException;
import org.ood.moviebooking.application.port.out.SeatLockProvider;
import org.ood.moviebooking.domain.entity.SeatLock;
import org.ood.moviebooking.domain.entity.Show;
import org.ood.moviebooking.domain.entity.ShowSeat;
import org.ood.moviebooking.domain.entity.ShowSeatStatus;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class InMemorySeatLockProvider implements SeatLockProvider {

    private final Duration lockTimeout;
    private final Map<Show, Map<ShowSeat, SeatLock>> locks = new ConcurrentHashMap<>();


    @Override
    public synchronized void lockSeats(Show show, List<ShowSeat> seats, String userId) {
        for (ShowSeat seat : seats) {
            if (isLockedByAnother(show, seat, userId) || seat.getStatus() == ShowSeatStatus.BOOKED) {
                throw new SeatUnavailableException(seat.getSeat().getSeatId());
            }
        }

        Map<ShowSeat, SeatLock> showLocks = locks.computeIfAbsent(show, s -> new ConcurrentHashMap<>());
        Instant now = Instant.now();
        for (ShowSeat seat : seats) {
            showLocks.put(seat, new SeatLock(seat, userId, now, lockTimeout));
        }
    }

    @Override
    public synchronized void unlockSeats(Show show, List<ShowSeat> seats, String userId) {
        Map<ShowSeat, SeatLock> showLocks = locks.get(show);
        if (showLocks == null) return;
        for (ShowSeat seat : seats) {
            SeatLock lock = showLocks.get(seat);
            if (lock != null && lock.getLockedBy().equals(userId)) {
                showLocks.remove(seat);
            }
        }
    }

    @Override
    public boolean validateLock(Show show, ShowSeat showSeat, String userId) {
        Map<ShowSeat, SeatLock> showLocks = locks.get(show);
        if (showLocks == null) return false;
        SeatLock lock = showLocks.get(showSeat);
        return lock != null && !lock.isExpired() && lock.getLockedBy().equals(userId);
    }

    private boolean isLockedByAnother(Show show, ShowSeat seat, String userId) {
        Map<ShowSeat, SeatLock> showLocks = locks.get(show);
        if (showLocks == null) return false;
        SeatLock lock = showLocks.get(seat);
        if (lock == null || lock.isExpired()) return false;
        return !lock.getLockedBy().equals(userId);
    }
}
