package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {
    Box findByBoxId(Long id);
}
