package org.ood.moviebooking.application.port.out;

import org.ood.moviebooking.domain.entity.Booking;

import java.util.Optional;

public interface BookingRepository {
    Booking save(Booking booking);

    Optional<Booking> findById(String BookingId);
}
