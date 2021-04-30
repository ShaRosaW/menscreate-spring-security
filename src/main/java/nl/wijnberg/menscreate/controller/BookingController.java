package nl.wijnberg.menscreate.controller;

import nl.wijnberg.menscreate.domain.Booking;
import nl.wijnberg.menscreate.payload.request.BookingRequest;
import nl.wijnberg.menscreate.payload.response.BookingResponse;
import nl.wijnberg.menscreate.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Get list of all bookings (Admin only)
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllBookings() {
       return bookingService.getAllBookings();
    }

    // Get all bookings by user by token
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserBookings(@RequestHeader Map<String, String> headers){
        return bookingService.getUserBookings(headers.get("authorization"));
    }

    // Get a booking by ID
    @GetMapping(value = "/{bookingId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getBookingById(@PathVariable("bookingId") long bookingId) {
       ResponseEntity<BookingResponse> booking = bookingService.getBookingById(bookingId);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    // Get userinfo by booking ID
    @GetMapping("/user/{bookingId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserBookingByBookingId(
            @RequestHeader Map<String, String> headers,
            @PathVariable("bookingId") long bookingId){
        return bookingService.getUserBookingByBookingId(headers.get("authorization"), bookingId);
    }

    // Create a new booking by user token
    @PostMapping(value = "/new")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> createBooking(@RequestHeader Map<String, String> headers, @RequestBody Booking booking) {
        long newBookingId = bookingService.createBooking(headers.get("authorization"), booking);
        return new ResponseEntity<>(newBookingId, HttpStatus.CREATED);
    }

    // Update or make a change to a booking
    @PutMapping(value = "/{bookingId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> updateBooking(
            @PathVariable("bookingId") long bookingId,
            @RequestBody BookingRequest bookingRequest,
            @RequestHeader Map<String, String> headers) {
        Booking updatedBooking = bookingService.updateBooking(bookingId, bookingRequest, headers.get("authorization"));
        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
    }

    // Delete a booking by bookingId
    @DeleteMapping(value = "/{bookingId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteBooking(@RequestHeader Map<String, String> headers, @PathVariable("bookingId") long bookingId){
        return bookingService.deleteBooking(headers.get("authorization"), bookingId);
    }

}

        //todo: admin creates a new booking to choose from as user in frontend?
//    @PostMapping(value = "")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Object> saveBooking(@RequestBody Booking booking) {
//        long newBookingId = bookingService.saveBooking(booking);
//        return new ResponseEntity<>(newBookingId, HttpStatus.OK);
//    }