package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.domain.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    List<Booking> getAllBookings();
    Booking getBookingById(long id);
//    Optional<Booking> getBooking(long id);
    long createBooking(Booking booking);
    void updateBooking(long id, Booking booking);
    void deleteBooking(long id);
    long saveBooking(Booking booking);

}
