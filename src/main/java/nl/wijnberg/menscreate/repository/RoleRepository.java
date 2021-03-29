package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.enums.ERole;
import nl.wijnberg.menscreate.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}