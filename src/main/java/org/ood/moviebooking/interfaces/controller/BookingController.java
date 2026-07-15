package org.ood.moviebooking.interfaces.controller;

import lombok.RequiredArgsConstructor;
import org.ood.moviebooking.application.port.out.PaymentStrategy;
import org.ood.moviebooking.application.usercase.BookingService;
import org.ood.moviebooking.domain.entity.Booking;
import org.ood.moviebooking.domain.entity.Customer;
import org.ood.moviebooking.interfaces.dto.BookingResponse;
import org.ood.moviebooking.interfaces.dto.CreateBookingRequest;
import org.ood.moviebooking.interfaces.presenter.BookingPresenter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final PaymentStrategy defaultPaymentStrategy;

    @PostMapping
    public BookingResponse createBooking(@RequestBody CreateBookingRequest request) {
        Customer customer = new Customer(request.getCustomerId(), "", "");
        Booking booking = bookingService.createBooking(customer, request.getShowId(), request.getSeatIds());
        return BookingPresenter.toResponse(booking);
    }

    @PostMapping("/{bookingId}/confirm")
    public void confirmBooking(@PathVariable String bookingId){
        bookingService.confirmBooking(bookingId, defaultPaymentStrategy);
    }

    @PostMapping("/{bookingId}/cancel")
    public void cancelBooking(@PathVariable String bookingId) {
        bookingService.cancelBooking(bookingId, defaultPaymentStrategy);
    }
}
