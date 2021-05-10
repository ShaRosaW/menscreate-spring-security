package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.payload.request.UpdateUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

@Service
@Validated
public interface UserService {

    // Get - Read
    ResponseEntity<?> getAllUsers();
    User findUserByToken(String token);
    Optional<User> getUserByUsername(String username);
    User getUserById(long id);



    //todo: implement later and see if works or remove (update and delete)

    // Put or Post - Update
    ResponseEntity<?> updateUserProfile(String token, @Valid UpdateUserRequest profileUpdate);
    long saveUserProfile(UpdateUserRequest updateUserRequest);
    ResponseEntity<?> updateUser(long id, UpdateUserRequest userUpdate);

    // Delete
    ResponseEntity<?> deleteUser(String token);
    //    void deleteUser(long id);
    // public abstract ResponseEntity<?> deleteUser(String token, String username);
}



