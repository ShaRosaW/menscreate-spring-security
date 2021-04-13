package nl.wijnberg.menscreate.controller;

import nl.wijnberg.menscreate.domain.Booking;
import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.payload.request.BookingRequest;
import nl.wijnberg.menscreate.payload.response.BookingResponse;
import nl.wijnberg.menscreate.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private BookingService bookingService;

    // Get list of all bookings (Admin only)
    @GetMapping("/list/all")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllBookings() {
        List<Booking> bookings = (List<Booking>) bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // Get bookings by user
    @GetMapping("/user")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserBookings(@RequestHeader Map<String, String> headers){
        return bookingService.getUserBookings(headers.get("authorization"));
    }

    // Get list of all bookings by user
    @GetMapping("/user/{username}")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<?> getAllBookingsByUser(@PathVariable("username") String username){
        List<User> userBookings = (List<User>) bookingService.getAllBookingsByUsername(username);
        return new ResponseEntity<>(userBookings, HttpStatus.OK);
    }

    // Get a booking by ID
    @GetMapping(value = "/{bookingId}")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable("bookingId") long bookingId) {
        bookingService.getBookingById(bookingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Create a new booking by user token
    @PostMapping(value = "/new")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> createBooking(@RequestHeader Map<String, String> headers, @RequestBody Booking booking) {
        long newBookingId = bookingService.createBooking(headers.get("authorization"), booking);
        return new ResponseEntity<>(newBookingId, HttpStatus.CREATED);
    }

    // Update or make a change to a booking
    @PutMapping(value = "/{bookingId}")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> updateBooking(
            @PathVariable("bookingId") long bookingId,
            @RequestBody BookingRequest bookingRequest) {
        BookingRequest updatedBooking = bookingService.updateBooking(bookingId, bookingRequest);
        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
//        return ResponseEntity.noContent().build();
    }

    // Delete a booking
    //    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    @DeleteMapping(value = "/{bookingId}")
////    @PreAuthorize("hasRole('USER')")
//        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<?> deleteBooking(String token,
//            @PathVariable("bookingId") long bookingId) {
//        bookingService.deleteBooking(token, bookingId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////        return ResponseEntity.noContent().build();
//    }
    @DeleteMapping(value = "/{bookingId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteBooking(@PathVariable("bookingId") long bookingId, @RequestHeader Map<String, String> headers){
        return bookingService.deleteBooking(headers.get("authorization"), bookingId);
    }

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }
}




//    // Get userinfo by booking ID
//    @GetMapping("/user/{id}")
////    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<Object> getUserInfoByBookingId(@PathVariable("id") long bookingId){
//        Optional<User> user = bookingService.getUserByBookingId(bookingId);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }



//    // Create a new booking by day part
//    @PostMapping(value = "/{id}/new")
////    @PreAuthorize("hasRole('USER')")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    ResponseEntity<Object> createBookingByDayPart(
//            @PathVariable("id") long id, @RequestBody AvailabilityRequest availabilityRequest){
//        long userId = bookingService.saveAvailableDayPart(id, availabilityRequest);
//        return new ResponseEntity<>(userId, HttpStatus.CREATED);
//    }


//    @PostMapping(value = "")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<Object> saveBooking(@RequestBody Booking booking) {
//        long newBookingId = bookingService.saveBooking(booking);
//        return new ResponseEntity<>(newBookingId, HttpStatus.OK);
//    }