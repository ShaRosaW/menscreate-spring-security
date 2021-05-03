package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.domain.Booking;
import nl.wijnberg.menscreate.payload.request.BookingRequest;
import nl.wijnberg.menscreate.payload.response.BookingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {

    // Get - Read
    ResponseEntity<?> getAllBookings();
    ResponseEntity<?> getUserBookings(String token);
    ResponseEntity<BookingResponse> getUserBookingByBookingId(String token, long bookingId);
    ResponseEntity<BookingResponse> getBookingById(long bookingId);

    // Post - Create
    long createBooking(String token, Booking booking);

    // Put or Post - Update
    Booking updateBooking(long bookingId, BookingRequest bookingRequest, String authorization);

    // Delete
    ResponseEntity<?>deleteBooking(String token, long bookingId);

}

//todo: implement later and see if works or remove (check date availabilty by token)

//  ResponseEntity<?> availabilityCheck(String token, AvailabilityRequest );



