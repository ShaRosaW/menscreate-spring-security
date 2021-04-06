package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
    File findByUserId (Long userId);
    boolean existsByUser_Id (Long userId);
}
