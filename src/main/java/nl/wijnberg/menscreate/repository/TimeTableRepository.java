package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.Booking;
import nl.wijnberg.menscreate.domain.SpaceType;
import nl.wijnberg.menscreate.domain.enums.ESpaceType;
import nl.wijnberg.menscreate.domain.enums.ETimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeTableRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByName(ETimeTable name);
}
