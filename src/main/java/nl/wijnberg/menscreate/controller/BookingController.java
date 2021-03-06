package nl.wijnberg.menscreate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/booking")
public class BookingController {

//    private BookingService bookingService;
//
//    @Autowired
//    public void setBookingService(BookingService bookingService) {
//        this.bookingService = bookingService;
//    }
}
