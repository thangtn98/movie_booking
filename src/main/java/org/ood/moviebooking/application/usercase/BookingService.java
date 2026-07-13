package org.ood.moviebooking.application.usercase;

import lombok.RequiredArgsConstructor;
import org.ood.moviebooking.application.exception.SeatLockExpiredException;
import org.ood.moviebooking.application.exception.SeatUnavailableException;
import org.ood.moviebooking.application.port.out.BookingRepository;
import org.ood.moviebooking.application.port.out.PaymentStrategy;
import org.ood.moviebooking.application.port.out.SeatLockProvider;
import org.ood.moviebooking.application.port.out.ShowRepository;
import org.ood.moviebooking.domain.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class BookingService {
    private final SeatLockProvider seatLockProvider;
    private final PriceCalculator priceCalculator;
    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;

    /**
     * Bước 1: người dùng chọn ghế -> giữ ghé, tạo booking PENDING
     */
    public Booking createBooking(Customer customer, String showId, List<String> seatIds) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new IllegalArgumentException("Show not found: " + showId));

        List<ShowSeat> seats = seatIds.stream()
                .map(show::getShowSeat)
                .peek(seat -> {
                    if (seat == null) throw new SeatUnavailableException(showId);
                })
                .toList();

        seatLockProvider.lockSeats(show, seats, customer.getId()); // ném exception nếu trùng
        seats.forEach(ShowSeat::reserve);

        Booking booking = new Booking(UUID.randomUUID().toString(), customer, show, seats);
        return bookingRepository.save(booking);
    }

    /**
     * Bước 2: người dùng thanh toán trong thời hạn giữ ghế -> xác nhận
     */
    public void confirmBooking(String bookingId, PaymentStrategy paymentStrategy) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found: " + bookingId));

        String userId = booking.getCustomer().getId();

        for (ShowSeat seat : booking.getSeats()) {
            if (!seatLockProvider.validateLock(booking.getShow(), seat, userId)) {
                booking.expire();
                bookingRepository.save(booking);
                throw new SeatLockExpiredException(bookingId);
            }
        }

        BigDecimal amount = priceCalculator.calculate(booking.getSeats(), booking.getShow());
        PaymentResult result = paymentStrategy.pay(amount);
        if (result.isSuccess()) {
            booking.getSeats().forEach(ShowSeat::book);
            booking.confirm(amount);
            bookingRepository.save(booking);
            seatLockProvider.unlockSeats(booking.getShow(), booking.getSeats(), userId);
        } else {
            cancelBooking(bookingId, paymentStrategy);
            throw new IllegalStateException("Payment failed: " + result.getMessage());
        }
    }

    public void cancelBooking(String bookingId, PaymentStrategy paymentStrategy) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found: " + bookingId));

        if (booking.getStatus() == BookingStatus.CONFIRMED && booking.getAmount() != null) {
            paymentStrategy.refund(booking.getAmount());
        }

        booking.getSeats().forEach(ShowSeat::release);
        seatLockProvider.unlockSeats(booking.getShow(), booking.getSeats(), booking.getCustomer().getId());
        booking.cancel();
        bookingRepository.save(booking);
    }

}
