package nl.wijnberg.menscreate.repository;

import nl.wijnberg.menscreate.domain.FileDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
    List<FileDB> findByUserId (long userId);
}

//    FileDB findByUserId (long userId);
//    boolean existsByUser_Id (long userId);