package nl.wijnberg.menscreate.controller;

import nl.wijnberg.menscreate.payload.request.UpdateUserRequest;
import nl.wijnberg.menscreate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateUser(@RequestHeader Map<String, String> headers,
                                        @RequestBody UpdateUserRequest updateRequest) {
        return userService.updateUserById(headers.get("authorization"), updateRequest);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> findUserByToken(@RequestHeader Map<String, String> headers) {
        return userService.findUserByToken(headers.get("authorization"));
    }

}
