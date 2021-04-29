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

    @Autowired
    private UserRepository userRepository;
//, long userId
    public FileDB store(MultipartFile file, User user) throws IOException{

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), user);


//        if (userRepository.existsById(user.getId())){
//            userRepository.findById(user.getId()).orElse(null);
//        }
        fileDB.setUser(user);
        return fileDBRepository.save(fileDB);
    }

    public FileDB getFileById(String id){
        if(!fileDBRepository.existsById(id)){
            throw new RecordNotFoundException();
        }
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getFile(long userId){
        try {
          return fileDBRepository.findAll().stream();
        }
        catch (Exception e){
            return fileDBRepository.findByUserId(userId).stream();
        }
    }
//    return fileDBRepository.findByUserId(userId).stream();
    //        Query q = em.createNamedQuery("Author.findByFirstName");
//        q.setParameter(1, "Thorben");
//        List a = q.getResultList();

//      if (fileDBRepository.existsByUser_Id(userId)){
//        return fileDBRepository.findByUserId(userId);
//    } else {
//        throw new RecordNotFoundException();
//    }
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }


//
//    public static String uploadDirectory = System.getProperty("user.dir") + "/file-uploads/";
//
//    public void uploadFile(MultipartFile file) throws IOException {
//        file.transferTo(new File(uploadDirectory + file.getOriginalFilename()));
//    }

//    User user = null;
//    if (userRepository.existsById(user)){
//        user = (User) userRepository.findById(user).orElse(null);
//    }
//        fileDB.setUser(user);
}