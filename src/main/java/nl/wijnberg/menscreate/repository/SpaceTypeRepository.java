package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.Role;
import nl.wijnberg.menscreate.domain.SpaceType;
import nl.wijnberg.menscreate.domain.enums.ERole;
import nl.wijnberg.menscreate.domain.enums.ESpaceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpaceTypeRepository extends JpaRepository<SpaceType, Long> {
    Optional<SpaceType> findByName(ESpaceType name);
}
