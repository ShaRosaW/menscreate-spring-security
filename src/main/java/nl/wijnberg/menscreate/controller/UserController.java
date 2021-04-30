package nl.wijnberg.menscreate.controller;

import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.payload.request.UpdateUserRequest;
import nl.wijnberg.menscreate.payload.response.FileResponse;
import nl.wijnberg.menscreate.payload.response.UserFileResponse;
import nl.wijnberg.menscreate.service.FileStorageService;
import nl.wijnberg.menscreate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    //todo: For all controllers! to test all endpoints made it easier to have both roles
    // don't forget to check with front end and this apps requirements
    // to change if needed. (hasRole User or Admin)

    private UserService userService;
    @Autowired
    private FileStorageService storageService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // Get a list of all users (Admin only)
    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get a user by it's token with profile picture attached to token for frontend
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    UserFileResponse findUserByToken(@RequestHeader Map<String, String> headers) {
        User user = userService.findUserByToken(headers.get("authorization"));

        List<FileResponse> fileDbs = storageService.getFile(user.getId())
                .filter(fileDB -> fileDB.getUser().getId() == user.getId())
                .map(dbFile -> {
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

        UserFileResponse response = new UserFileResponse(
                user, fileDbs);
        try {
            return response;
        } catch (Exception e){
            return response;
        }
    }
//    List<FileDB> files =storageService.getFile(user.getId()).collect(Collectors.toList());
//       user.setFiles(files);

    // Get user by ID
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> getUserById(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Get user by username
    @GetMapping(value = "/user/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> getUserByUsername(@PathVariable("username") String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    // todo: saveUserProfile is with updateUserRequest, updateUser same, or @Requestbody to domain User?

    //todo: make this work or leave out
    // update user profile info by token
    @PutMapping("/user/update-profile")
    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public ResponseEntity<?> updateUserProfile(@RequestHeader Map<String, String> headers,
                                               @RequestBody UpdateUserRequest profileUpdate) {
        return userService.updateUserProfile(headers.get("authorization"), profileUpdate);
    }

    //todo: make this work
    // Update new user profile information by ID
    @PostMapping(value = "/user/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> saveUserProfile(@PathVariable("id") int id, @RequestBody UpdateUserRequest updateUserRequest){
        long newId = userService.saveUserProfile(updateUserRequest);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    //todo: make this work
    // Update user information by ID
    @PutMapping(value = "/user/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> updateUser(@PathVariable("id") int id, @RequestBody UpdateUserRequest userUpdate) {
        userService.updateUser(id, userUpdate);
        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }

    //todo: make this work or leave out
    // delete user profile info by token
    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id,
                                        @RequestHeader Map<String, String> headers){
        return userService.deleteUser(headers.get("authorization"));
    }

}


//todo: make this work //Delete user entirely by ID

//    @DeleteMapping(value = "/user/{id}")
////    @PreAuthorize("hasRole('USER')")
//        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
//        userService.deleteUser(id);
//        return new ResponseEntity<>(" User with ID " + id + " has been deleted", HttpStatus.NO_CONTENT);
//    }

//
//    // Upload a file to directory
//    @PostMapping(value = "/{id}/uploads")
////    @PreAuthorize("hasRole('USER')")
//        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public void uploadFileToDir(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
//        userService.uploadFileToDir(file);
//    }

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