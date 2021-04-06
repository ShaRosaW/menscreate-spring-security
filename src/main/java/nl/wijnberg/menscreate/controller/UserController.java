package nl.wijnberg.menscreate.controller;

import nl.wijnberg.menscreate.domain.File;
import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.payload.request.UpdateUserRequest;
import nl.wijnberg.menscreate.payload.response.FileResponse;
import nl.wijnberg.menscreate.payload.response.MessageResponse;
import nl.wijnberg.menscreate.service.FileService;
import nl.wijnberg.menscreate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    //todo: For all controllers! to test all endpoints made it easier to have both roles
    // don't forget to check with front end and this apps requirements
    // to change if needed.

    private UserService userService;
    private FileService fileService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // Get a list of all users (Admin only)
    @GetMapping(value = "")
//    @PreAuthorize("hasRole('ADMIN')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = (List<User>) userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get a user by it's token
    @GetMapping("/user")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> findUserByToken(@RequestHeader Map<String, String> headers) {
        return userService.findUserByToken(headers.get("authorization"));
    }

    // Get user by ID
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> getUserById(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // todo: test if value /update/pathvariable works or with user/update/pv or w/o /
    //  update, same goes for delete, etc. and same goes for bookingcontroller.

    // Upload a file to directory
    @PostMapping(value = "/{id}/uploads")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void uploadFileToDir(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        userService.uploadFileToDir(file);
    }

    // Upload a file to the database by user ID
    @PostMapping(value = "/upload/{id}")
//    @PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> uploadFileToDB(@PathVariable ("id") long userId, @RequestParam("file") MultipartFile file){
        try {
            fileService.store(file, userId);
            String message = "Picture was uploaded successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        } catch (Exception e) {
            String message = "Could not upload this picture" + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
        }
    }

    // Get a file by user ID
    @GetMapping("/download/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<byte[]> getFile(@PathVariable ("id") String id) {
        File imgFile = fileService.getFileById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imgFile.getName() + "\"")
                .body(imgFile.getImage());
    }

    // Get a list of files from the database (for Admin Only)
    @GetMapping(value = "/files")
//    @PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<FileResponse>> getFilesList() {
        List<FileResponse> files = fileService.getAllFiles().map(imgFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(imgFile.getId())
                    .toUriString();

            return new FileResponse(
                    imgFile.getName(),
                    fileDownloadUri,
                    imgFile.getType(),
                    imgFile.getImage().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    // Update new user profile information by ID
//    @PutMapping(value = "/{id}")
//    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> saveUserProfile(@PathVariable("id") int id, @RequestBody UpdateUserRequest updateUserRequest){
        long newId = userService.saveUserProfile(updateUserRequest);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    // todo: saveUserProfile is with updateUserRequest, updateUser same, or connect Requestbody to domain User?

    // Update user information by ID
    @PutMapping(value = "/{id}")
//    @PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> updateUser(@PathVariable("id") int id, @RequestBody UpdateUserRequest userUpdate) {
        userService.updateUser(id, userUpdate);
        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }

    // Delete user entirely by ID
    @DeleteMapping(value = "/{id}")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

//    @PostMapping("/update")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> updateUser(@RequestHeader Map<String, String> headers,
//                                        @RequestBody UpdateUserRequest updateRequest) {
//        return userService.updateUserById(headers.get("authorization"), updateRequest);
//    }

//    @PostMapping(value = "/user")
////    @PreAuthorize("hasRole('USER')")
//        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<Object> saveUser(@RequestBody UpdateUserRequest updateUserRequest) {
//        long newId = userService.saveUser(updateUserRequest);
//        return new ResponseEntity<>(newId, HttpStatus.CREATED);
//    }