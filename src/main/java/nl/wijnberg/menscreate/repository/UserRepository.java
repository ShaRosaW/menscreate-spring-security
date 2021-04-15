package nl.wijnberg.menscreate.repository;


import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.domain.UserProfileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    void deleteByUsername(String username);

    boolean existsById(String username);

    boolean existsById(long id);

    void deleteById(long id);
}

//    User save();
