package nl.wijnberg.menscreate.repository;


import nl.wijnberg.menscreate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    User save();
//    void deleteByUsername(String username);
}
