package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.domain.Booking;
import nl.wijnberg.menscreate.exceptions.RecordNotFoundException;
import nl.wijnberg.menscreate.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(long id) {
        if (bookingRepository.existsById(id)) {
            return bookingRepository.findById(id).orElse(null);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public long createBooking(Booking booking) {
        Booking newBooking = bookingRepository.save(booking);
        return newBooking.getId();
    }

    @Override
    public void updateBooking(long id, Booking booking) {

    }

    @Override
    public void deleteBooking(long id) {
        if (!bookingRepository.existsById(id)) throw new RecordNotFoundException();
        bookingRepository.deleteById(id);
    }
    @Override
    public long saveBooking(Booking booking) {
        Booking newBooking = bookingRepository.save(booking);
        return newBooking.getId();
    }


}
