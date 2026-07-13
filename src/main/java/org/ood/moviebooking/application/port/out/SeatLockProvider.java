package org.ood.moviebooking.application.port.out;

import org.ood.moviebooking.domain.entity.Show;
import org.ood.moviebooking.domain.entity.ShowSeat;

import java.util.List;

public interface SeatLockProvider {
    void lockSeats(Show show, List<ShowSeat> seats, String userId);

    void unlockSeats(Show show, List<ShowSeat> seats, String userId);

    boolean validateLock(Show show, ShowSeat showSeat, String userId);
}
