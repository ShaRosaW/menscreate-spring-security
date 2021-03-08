package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileUploadService {

//    @Autowired
//    private FileUploadRepository fileUploadRepository;
//
//    public static String uploadDirectory = System.getProperty("user.dir") + "/file-uploads/";
//
//    public void uploadFile(MultipartFile file) throws IOException {
//        file.transferTo(new File(uploadDirectory + file.getOriginalFilename()));
//    }
}