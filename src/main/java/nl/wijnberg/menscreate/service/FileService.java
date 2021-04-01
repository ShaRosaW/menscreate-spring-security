package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.domain.File;
import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.exceptions.RecordNotFoundException;
import nl.wijnberg.menscreate.repository.FileRepository;
import nl.wijnberg.menscreate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    public File store(MultipartFile file, long userId) throws IOException{

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File imgFile = new File(fileName, file.getContentType(), file.getBytes());

        User user = null;
        if (userRepository.existsById(userId)){
            user = userRepository.findById(userId).orElse(null);
        } imgFile.setUser(user);
        return fileRepository.save(imgFile);
    }

    public File getFile(Long fileId){
        if(!fileRepository.existsById(fileId)){
            throw new RecordNotFoundException();
        } return fileRepository.findById(fileId).get();
    }

    public File getFileByUserId(long userId){
        if (!fileRepository.existsByUser_Id(userId)){
            return fileRepository.findByUserId(userId);
        } else {
            throw new RecordNotFoundException();
        }
    }
//
//    public static String uploadDirectory = System.getProperty("user.dir") + "/file-uploads/";
//
//    public void uploadFile(MultipartFile file) throws IOException {
//        file.transferTo(new File(uploadDirectory + file.getOriginalFilename()));
//    }
}