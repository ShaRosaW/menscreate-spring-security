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

    //todo: For all controllers! to test all endpoints made it easier to have both roles
    // don't forget to check with front end and this apps requirements
    // to change if needed.

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
//    @PreAuthorize("hasRole('ADMIN')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> findUserByToken(@RequestHeader Map<String, String> headers) {
        return userService.findUserByToken(headers.get("authorization"));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
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

    @PostMapping(value = "/{id}/uploads")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void uploadFile(@PathVariable("id") int id, @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        userService.uploadFile(file);
    }

    @PutMapping(value = "/{id}")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> updateUser(@PathVariable("id") int id, @RequestBody UpdateUserRequest updateUserRequest) {
        userService.updateUser(id, updateUserRequest);
        return new ResponseEntity<>(updateUserRequest, HttpStatus.OK);
    }

    @PostMapping(value = "/user")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> saveUser(@RequestBody UpdateUserRequest updateUserRequest) {
        long newId = userService.saveUser(updateUserRequest);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
//    @PreAuthorize("hasRole('USER')")
        @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
