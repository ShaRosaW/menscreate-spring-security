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

    // Get list of all bookings (Admin only) //todo: make this work
    @GetMapping("/list/all")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllBookings() {
       return bookingService.getAllBookings();
    }
//       // Get list of all bookings (Admin only) //todo: make this work
//    @GetMapping("/list/all")
////    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<?> getAllBookings() {
//        List<Booking> bookings = (List<Booking>) bookingService.getAllBookings();
//        return new ResponseEntity<>(bookings, HttpStatus.OK);
//    }

    //todo: works
    // Get bookings by user
    @GetMapping("/user")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserBookings(@RequestHeader Map<String, String> headers){
        return bookingService.getUserBookings(headers.get("authorization"));
    }

//    // Get list of all bookings by user //todo: make this work or remove?
//    @GetMapping("/user/{username}")
////    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    ResponseEntity<?> getAllBookingsByUser(@PathVariable("username") String username){
//        List<User> userBookings = (List<User>) bookingService.getAllBookingsByUsername(username);
//        return new ResponseEntity<>(userBookings, HttpStatus.OK);
//    }
//    // Get list of all bookings by user //todo: make this work or remove?
//    @GetMapping("/user/{username}")
////    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    ResponseEntity<?> getAllBookingsByUser(@PathVariable("username") String username){
//        List<User> userBookings = (List<User>) bookingService.getAllBookingsByUsername(username);
//        return new ResponseEntity<>(userBookings, HttpStatus.OK);
//    }

    //todo: works
    // Get a booking by ID
    @GetMapping(value = "/{bookingId}")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getBookingById(@PathVariable("bookingId") long bookingId) {
       BookingResponse booking = bookingService.getBookingById(bookingId);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }


    // Get userinfo by booking ID //todo: make this work or remove?
    @GetMapping("/user/{bookingId}")
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserInfoByBookingId(@PathVariable("bookingId") long bookingId){
        ResponseEntity<BookingResponse> user = bookingService.getUserByBookingId(bookingId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //todo: works
    // Create a new booking by user token
    @PostMapping(value = "/new")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> createBooking(@RequestHeader Map<String, String> headers, @RequestBody Booking booking) {
        long newBookingId = bookingService.createBooking(headers.get("authorization"), booking);
        return new ResponseEntity<>(newBookingId, HttpStatus.CREATED);
    }

    //todo: works
    // Update or make a change to a booking
    @PutMapping(value = "/{bookingId}")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> updateBooking(
            @PathVariable("bookingId") long bookingId,
            @RequestBody BookingRequest bookingRequest,
            @RequestHeader Map<String, String> headers) {
        Booking updatedBooking = bookingService.updateBooking(bookingId, bookingRequest, headers.get("authorization"));
        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
//        return ResponseEntity.noContent().build();
    }

//    // Update or make a change to a booking
//    @PutMapping(value = "/{bookingId}")
////    @PreAuthorize("hasRole('USER')")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<?> updateBookingById(
//            @RequestHeader Map<String, String> headers,
//            @PathVariable("bookingId") long bookingId) {
//        return bookingService.updateByBookingId(headers.get("authorization"), bookingId);
//    }

    //todo: works
    @DeleteMapping(value = "/{bookingId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteBooking(@RequestHeader Map<String, String> headers, @PathVariable("bookingId") long bookingId){
        return bookingService.deleteBooking(headers.get("authorization"), bookingId);
    }

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }
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