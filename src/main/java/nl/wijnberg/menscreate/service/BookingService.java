package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.domain.Booking;
import nl.wijnberg.menscreate.domain.User;
//import nl.wijnberg.menscreate.domain.enums.EDayPart;
import nl.wijnberg.menscreate.payload.request.BookingRequest;
//import nl.wijnberg.menscreate.payload.request.BoxRequest;
//import nl.wijnberg.menscreate.payload.request.SpaceRequest;
import nl.wijnberg.menscreate.payload.response.BookingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookingService {

    // Get - Read
    ResponseEntity<?> getAllBookings();
    ResponseEntity<ResponseEntity<List<BookingResponse>>> getAllBookingsByUsername(String username);
//    ResponseEntity<?> getAllBookingsByDate();
//    ResponseEntity<?> getAvailabilityDayPartCheck(AvailabilityRequest availabilityRequest);
//    EDayPart getDayPartOfBooking(long bookingId);
    ResponseEntity<?> getUserBookings(String token);
    Optional<User> getUserByBookingId(long bookingId);
    ResponseEntity<BookingResponse> getBookingById(long bookingId);


    // Post - Create
//    ResponseEntity<Object> createBookingByDayPart(AvailabilityRequest availabilityRequest);
//    ResponseEntity<MessageResponse> createBookingByDate(BookingRequest bookingRequest);
//    ResponseEntity<MessageResponse> createBookingByBookingType(BookingRequest bookingRequest);
//    ResponseEntity<MessageResponse> createBookingBySpaceType(SpaceRequest spaceRequest);
//    ResponseEntity<MessageResponse> createBookingByBoxType(BoxRequest boxRequest);
    long createBooking(String token, Booking booking);
//    long saveAvailableDayPart(long userId, AvailabilityRequest availabilityRequest);
    //    long saveBooking(Booking booking);

    // Put or Post - Update
    Booking updateBooking(long bookingId, BookingRequest bookingRequest);
//    void updateDayPartOfBooking(long bookingId, EDayPart eDayPart);
//    void updateBooking(long bookingId, Booking booking);
//    ResponseEntity<?> updateBookingById(String token, BookingRequest bookingRequest);


    // Delete
    ResponseEntity<?>deleteBooking(String token, long bookingId);
//    void deleteBooking(long bookingId);
}

//    Booking getBookingById(long bookingId);
//    Optional<Booking> getBooking(long id);
//    ResponseEntity<?> availabilityCheck(String token, AvailabilityRequest );
//    ResponseEntity<MessageResponse> createBooking(String token, BookingRequest bookingRequest);


