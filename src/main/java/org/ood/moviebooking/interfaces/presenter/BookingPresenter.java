package org.ood.moviebooking.interfaces.presenter;

import org.ood.moviebooking.domain.entity.Booking;
import org.ood.moviebooking.interfaces.dto.BookingResponse;

public class BookingPresenter {
    public static BookingResponse toResponse(Booking booking) {
        return BookingResponse.builder()
                .bookingId(booking.getBookingId())
                .status(booking.getStatus().name())
                .amount(booking.getAmount())
                .build();
    }
}
