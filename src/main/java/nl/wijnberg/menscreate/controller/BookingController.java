package nl.wijnberg.menscreate.controller;

import nl.wijnberg.menscreate.domain.Booking;
import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.payload.request.AvailabilityRequest;
import nl.wijnberg.menscreate.payload.request.BookingRequest;
import nl.wijnberg.menscreate.payload.response.BookingResponse;
import nl.wijnberg.menscreate.payload.response.MessageResponse;
import nl.wijnberg.menscreate.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private BookingService bookingService;

    // Get list of all bookings (Admin only)
    @GetMapping("")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllBookings() {
        List<Booking> bookings = (List<Booking>) bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // Get list of all bookings by user
    @GetMapping("")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<?> getAllBookingsByUser(String username){
        List<User> userBookings = (List<User>) bookingService.getAllBookingsByUser(username);
        return new ResponseEntity<>(userBookings, HttpStatus.OK);
    }

    // Get userinfo by booking ID
    @GetMapping("/user/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> getUserInfoByBookingId(@PathVariable("id") long bookingId){
        Optional<User> user = bookingService.getUserByBookingId(bookingId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    // Get a booking by ID
    @GetMapping(value = "/{bookingId}")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable("bookingId") long bookingId) {
        bookingService.getBookingById(bookingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Create a new booking (Admin only??)
    @PostMapping(value = "")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> createBooking(@RequestBody Booking booking) {
        long newBookingId = bookingService.createBooking(booking);
        return new ResponseEntity<>(newBookingId, HttpStatus.CREATED);
    }

    // Create a new booking by day part
    @PostMapping(value = "")
//    @PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<Object> createBookingByDayPart(
            @PathVariable("id") long id, @RequestBody AvailabilityRequest availabilityRequest){
        long userId = bookingService.saveAvailableDayPart(id, availabilityRequest);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
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

//    @PostMapping(value = "")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<Object> saveBooking(@RequestBody Booking booking) {
//        long newBookingId = bookingService.saveBooking(booking);
//        return new ResponseEntity<>(newBookingId, HttpStatus.OK);
//    }

    // Delete a booking
    //    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping(value = "/{bookingId}")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteBooking(String token,
            @PathVariable("bookingId") long bookingId) {
        bookingService.deleteBooking(token, bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        return ResponseEntity.noContent().build();
    }

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }
}
