package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.domain.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    List<Booking> getAllBookings();
    Booking getBookingById(long bookingId);
    long createBooking(Booking booking);
    void updateBooking(long bookingId, Booking booking);
//    long saveBooking(Booking booking);
    void deleteBooking(long bookingId);
}
//    Optional<Booking> getBooking(long id);
//ResponseEntity<MessageResponse> createBooking(String token, BookingRequest bookingRequest);
//ResponseEntity<?> getUserBookings(String token);
//ResponseEntity<?> updateBookingById(String token, BookingRequest bookingRequest);
//ResponseEntity<?> deleteBooking(String token, long bookingId);
//ResponseEntity<?> availabilityCheck(String token, long bookingId);