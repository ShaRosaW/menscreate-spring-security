package nl.wijnberg.menscreate.controller;

import nl.wijnberg.menscreate.domain.Booking;
import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.service.BookingService;
import nl.wijnberg.menscreate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/all")
public class AdminController {

    private UserService userService;
    private BookingService bookingService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/bookings")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

//    @DeleteMapping(value = "/users/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
//        userService.deleteUser(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }
}
