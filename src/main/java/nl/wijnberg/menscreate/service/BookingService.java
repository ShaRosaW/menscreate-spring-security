package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.domain.Booking;
import nl.wijnberg.menscreate.payload.request.AvailabilityRequest;
import nl.wijnberg.menscreate.payload.request.BookingRequest;
import nl.wijnberg.menscreate.payload.request.BoxRequest;
import nl.wijnberg.menscreate.payload.request.SpaceRequest;
import nl.wijnberg.menscreate.payload.response.BookingResponse;
import nl.wijnberg.menscreate.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {

    ResponseEntity<?> getAllBookings();
    ResponseEntity<ResponseEntity<BookingResponse>> getAllBookingsByUser(String username);
    ResponseEntity<?> getAllBookingsByDate();
    ResponseEntity<?> getAvailabilityDayPartCheck(AvailabilityRequest availabilityRequest);
    ResponseEntity<BookingResponse> getBookingById(long bookingId);
    ResponseEntity<?> getUserBookings(String token);
//    Booking getBookingById(long bookingId);
ResponseEntity<MessageResponse> createBookingByDate(BookingRequest bookingRequest);
    ResponseEntity<MessageResponse> createBookingByBookingType(BookingRequest bookingRequest);
    ResponseEntity<MessageResponse> createBookingBySpaceType(SpaceRequest spaceRequest);
    ResponseEntity<MessageResponse> createBookingByBoxType(BoxRequest boxRequest);

    long createBooking(Booking booking);
    BookingRequest updateBooking(long bookingId, BookingRequest bookingUpdate);
//    void updateBooking(long bookingId, Booking booking);
//    long saveBooking(Booking booking);
    ResponseEntity<?>deleteBooking(String token, long bookingId);
//    void deleteBooking(long bookingId);
}
//    Optional<Booking> getBooking(long id);
//ResponseEntity<MessageResponse> createBooking(String token, BookingRequest bookingRequest);
//ResponseEntity<?> getUserBookings(String token);
//ResponseEntity<?> updateBookingById(String token, BookingRequest bookingRequest);
//ResponseEntity<?> deleteBooking(String token, long bookingId);
//ResponseEntity<?> availabilityCheck(String token, AvailabilityRequest );