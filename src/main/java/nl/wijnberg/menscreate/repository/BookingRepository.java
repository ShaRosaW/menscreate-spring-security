package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.Booking;
import nl.wijnberg.menscreate.domain.User;
//import nl.wijnberg.menscreate.domain.enums.EBookingType;
//import nl.wijnberg.menscreate.domain.enums.EDayPart;
import nl.wijnberg.menscreate.payload.response.BookingResponse;
import nl.wijnberg.menscreate.payload.response.MessageResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findByBookingId(Long bookingId);
    List<Booking> findAll();
    List<Booking> findAllBookingsByUser(Optional<User> user);
//    List<Booking> findBookingByDayPart_Name(EDayPart dayPart);
//    List<Booking> findBookingByBookingType_Name(EBookingType bookingType);
//    List<Booking> findByBookingDate(LocalDate bookingDate);
//    boolean existsByUser_IdAndBookingDate(Long userId, LocalDate bookingDate);
    ResponseEntity<MessageResponse> deleteByBookingId(Long bookingId);


}
