package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.payload.request.UpdateUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public interface UserService {
    ResponseEntity<?> getAllUsers();
    ResponseEntity<?> updateUserById(String token,  @Valid UpdateUserRequest userRequest);
    ResponseEntity<?> findUserByToken(String token);
}
//
//    List<User> getAllUsers();
//    User getUserById(long id);
//    void deleteUser(long id);
//    void updateEmail(long id, User user);
//    void updateUser(long id, User user);
//    long saveUser(User user);