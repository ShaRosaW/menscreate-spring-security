package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.SpaceType;
import nl.wijnberg.menscreate.domain.enums.ESpaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceTypeRepository extends JpaRepository<SpaceType, Long> {
    SpaceType findByName(ESpaceType name);
    Boolean existsByName(String name);
}
