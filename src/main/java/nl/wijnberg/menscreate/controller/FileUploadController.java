package nl.wijnberg.menscreate.controller;

import nl.wijnberg.menscreate.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000")
public class FileUploadController {

//    @Autowired
//    FileUploadService fileUploadService;
//
////    @GetMapping(value = "/")
////    public String fileUpload() {
////        return "redirect:/file-upload"; // TODO: check the get and post!
////    }
//
//    @PostMapping("/file-upload") // TODO: in React declare same page redirect
//        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public void uploadFile(@RequestParam("file") MultipartFile file)
//            throws IllegalStateException, IOException {
//        fileUploadService.uploadFile(file);
//    }
}
