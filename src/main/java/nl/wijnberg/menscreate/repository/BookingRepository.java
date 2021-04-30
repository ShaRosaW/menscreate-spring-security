package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findByBookingId(Long bookingId);
    List<Booking> findAll();

}
