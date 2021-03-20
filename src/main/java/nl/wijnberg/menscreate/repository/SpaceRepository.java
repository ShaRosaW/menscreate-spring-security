package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.Space;
import nl.wijnberg.menscreate.domain.SpaceType;
import nl.wijnberg.menscreate.domain.enums.ESpaceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpaceRepository extends JpaRepository<Space, Long> {
    Optional<Space> findBySpaceId(long id);
}
