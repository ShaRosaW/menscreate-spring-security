package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
//    FileDB findByUserId (Long userId);
//    boolean existsByUser_Id (Long userId);
}
