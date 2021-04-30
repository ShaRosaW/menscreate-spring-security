package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    boolean existsById(long id);

}

//    void deleteByUsername(String username);
//    void deleteById(long id);