package nl.wijnberg.menscreate.controller;

import nl.wijnberg.menscreate.domain.FileDB;
import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.payload.response.FileResponse;
import nl.wijnberg.menscreate.payload.response.MessageResponse;
import nl.wijnberg.menscreate.service.FileStorageService;
import nl.wijnberg.menscreate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileController {

    @Autowired
    private FileStorageService storageService;
    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadFile(
            @RequestParam("file") MultipartFile file, @RequestHeader Map<String, String> headers) {
        String message = "";
        String token = headers.get("authorization");
//        User requestUser = userService.findUserByToken(token);
//        User requestUser = (User) userService.findUserByToken(token).getBody();
        User requestUser = (User) userService.findUserByToken(token);
        try {
            storageService.store(file, requestUser);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
        }
    }

    @GetMapping("")
    public ResponseEntity<List<FileResponse>> getListFiles() {
        List<FileResponse> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new FileResponse(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFileById(@PathVariable("id") String id
//            , @RequestHeader Map<String, String> headers
    ) {
//        String token = headers.get("authorization");
//        User requestUser = (User) userService.findUserByToken(token).getBody();

        FileDB fileDB = storageService.getFileById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileDB.getData());
    }
}



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