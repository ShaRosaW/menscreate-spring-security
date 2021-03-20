package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.Box;
import nl.wijnberg.menscreate.domain.SpaceType;
import nl.wijnberg.menscreate.domain.enums.ESpaceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoxRepository extends JpaRepository<Box, Long> {
    Optional<Box> findByBoxId(long id);
}
