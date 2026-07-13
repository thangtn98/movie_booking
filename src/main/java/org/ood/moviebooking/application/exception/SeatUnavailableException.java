package org.ood.moviebooking.application.exception;

public class SeatUnavailableException extends RuntimeException {
    public SeatUnavailableException(String seatId) {
        super("Seat " + seatId + " is not available");
    }
}
