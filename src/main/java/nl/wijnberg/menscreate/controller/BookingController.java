package nl.wijnberg.menscreate.controller;

import nl.wijnberg.menscreate.domain.Booking;
import nl.wijnberg.menscreate.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private BookingService bookingService;

    @GetMapping(value = "/{bookingId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> getBookingById(@PathVariable("bookingId") long bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @PostMapping(value = "")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> createBooking(@RequestBody Booking booking) {
        long newBookingId = bookingService.createBooking(booking);
        return new ResponseEntity<>(newBookingId, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{bookingId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> updateBooking(
            @PathVariable("bookingId") long bookingId,
            @RequestBody Booking booking) {
        bookingService.updateBooking(bookingId, booking);
        return new ResponseEntity<>(HttpStatus.OK);
//        return ResponseEntity.noContent().build();
    }

//    @PostMapping(value = "")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<Object> saveBooking(@RequestBody Booking booking) {
//        long newBookingId = bookingService.saveBooking(booking);
//        return new ResponseEntity<>(newBookingId, HttpStatus.OK);
//    }

    //    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping(value = "/{bookingId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> deleteBooking(
            @PathVariable("bookingId") long bookingId) {
        bookingService.deleteBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        return ResponseEntity.noContent().build();
    }

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }
}
