package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.BookingType;
import nl.wijnberg.menscreate.domain.Role;
import nl.wijnberg.menscreate.domain.enums.EBookingType;
import nl.wijnberg.menscreate.domain.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingTypeRepository extends JpaRepository<BookingType, Long> {
    Optional<BookingType> findByName(EBookingType name);
}
