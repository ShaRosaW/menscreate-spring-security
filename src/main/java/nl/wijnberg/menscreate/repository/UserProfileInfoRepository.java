package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.UserProfileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileInfoRepository extends JpaRepository <UserProfileInfo, Long>{

    Optional<UserProfileInfo> findById(Long id);

}
//    Optional<UserProfileInfo> findByUserUsername(String username);