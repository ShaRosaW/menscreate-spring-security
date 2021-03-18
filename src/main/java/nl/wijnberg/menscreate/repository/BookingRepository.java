package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findAllByBookingId(long bookingId);
    void deleteByBookingId(long bookingId);

}
