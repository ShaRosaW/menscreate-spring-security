package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.enums.ERole;
import nl.wijnberg.menscreate.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}