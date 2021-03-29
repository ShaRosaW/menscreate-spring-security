package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
    Space findBySpaceId(Long id);
}
