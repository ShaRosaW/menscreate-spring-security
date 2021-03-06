package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findAllById(long id);
    void deleteById(long id);
}
