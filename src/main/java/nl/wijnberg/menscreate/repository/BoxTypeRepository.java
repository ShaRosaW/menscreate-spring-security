package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.BoxType;
import nl.wijnberg.menscreate.domain.SpaceType;
import nl.wijnberg.menscreate.domain.enums.EBoxType;
import nl.wijnberg.menscreate.domain.enums.ESpaceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoxTypeRepository extends JpaRepository<BoxType, Long> {
    Optional<BoxType> findByName(EBoxType name);
}
