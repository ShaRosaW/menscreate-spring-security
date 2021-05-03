package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.domain.FileDB;
import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.exceptions.RecordNotFoundException;
import nl.wijnberg.menscreate.repository.FileDBRepository;
import nl.wijnberg.menscreate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    // Store The Multipart file with user to repository to database
    public FileDB store(MultipartFile file, User user) throws IOException{

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), user);

        fileDB.setUser(user);
        return fileDBRepository.save(fileDB);
    }

    //todo: implement later and see if works or remove
//    public FileDB getFileById(String id){
//        if(!fileDBRepository.existsById(id)){
//            throw new RecordNotFoundException();
//        }
//        return fileDBRepository.findById(id).get();
//    }

    // Stream the Files in database to get a file by userId
    public Stream<FileDB> getFile(long userId){
        try {
          return fileDBRepository.findAll().stream();
        }
        catch (Exception e){
            return fileDBRepository.findByUserId(userId).stream();
        }
    }

    // Stream the Files in database to get a list of files
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

}