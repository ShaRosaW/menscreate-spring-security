package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.UserProfileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileInfoRepository extends JpaRepository <UserProfileInfo, Long>{
    //    Optional<UserProfileInfo> findUserProfileByLastName(String lastname);
}
