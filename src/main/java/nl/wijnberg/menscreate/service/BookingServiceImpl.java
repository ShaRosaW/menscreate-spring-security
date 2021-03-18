package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.domain.Booking;
import nl.wijnberg.menscreate.exceptions.DatabaseErrorException;
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
    public Booking getBookingById(long bookingId) {
        if (bookingRepository.existsById(bookingId)) {
            return bookingRepository.findById(bookingId).orElse(null);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public long createBooking(Booking booking) {
        Booking newBooking = bookingRepository.save(booking);
        return newBooking.getBookingId();
    }

    @Override
    public void updateBooking(long bookingId, Booking booking) {
        if (bookingRepository.existsById(bookingId)) {
            try {
                Booking existBooking = bookingRepository.findAllByBookingId(bookingId).orElse(null);
//                        .get();
                existBooking.setBookingType(booking.getBookingType());
                existBooking.setBookingDate(booking.getBookingDate());
                existBooking.setDayPart(booking.getDayPart());
                existBooking.setTimeTable(booking.getTimeTable());
                existBooking.setUser(booking.getUser());
                bookingRepository.save(existBooking);
            } catch (Exception exception) {
                throw new DatabaseErrorException();
            }
        } else {
            throw new RecordNotFoundException();
        }
    }

//    @Override
//    public long saveBooking(Booking booking) {
//        Booking newBooking = bookingRepository.save(booking);
//        return newBooking.getBookingId();
//    }

    @Override
    public void deleteBooking(long bookingId) {
        if (!bookingRepository.existsById(bookingId)) throw new RecordNotFoundException();
        bookingRepository.deleteByBookingId(bookingId);
    }

}
