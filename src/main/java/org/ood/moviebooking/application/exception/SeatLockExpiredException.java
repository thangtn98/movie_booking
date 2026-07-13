package org.ood.moviebooking.application.exception;

public class SeatLockExpiredException extends RuntimeException {
    public SeatLockExpiredException(String bookingId) {
        super("Lock expired for booking " + bookingId);
    }
}
