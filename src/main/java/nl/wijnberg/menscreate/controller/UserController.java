package nl.wijnberg.menscreate.controller;

import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.payload.request.UpdateUserRequest;
import nl.wijnberg.menscreate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping(value = "/users")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Object> getAllUsers() {
//        List<User> users = userService.getAllUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findUserByToken(@RequestHeader Map<String, String> headers) {
        return userService.findUserByToken(headers.get("authorization"));
    }

    @GetMapping(value = "/user/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> getUserById(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @PostMapping("/update")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> updateUser(@RequestHeader Map<String, String> headers,
//                                        @RequestBody UpdateUserRequest updateRequest) {
//        return userService.updateUserById(headers.get("authorization"), updateRequest);
//    }

    // todo: test if value /update/pathvariable works or with user/update/pv or w/o /
    //  update, same goes for delete, etc. and same goes for bookingcontroller.

    @PostMapping(value = "/user/{id}/uploads")
    @PreAuthorize("hasRole('USER')")
    public void uploadFile(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        userService.uploadFile(file);
    }

    @PutMapping(value = "/user/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        userService.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
        long newId = userService.saveUser(user);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/user/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
