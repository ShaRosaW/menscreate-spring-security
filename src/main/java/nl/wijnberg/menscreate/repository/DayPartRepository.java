package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.Booking;
import nl.wijnberg.menscreate.domain.enums.EDayPart;
import nl.wijnberg.menscreate.domain.enums.ETimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DayPartRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByName(EDayPart name);
}
