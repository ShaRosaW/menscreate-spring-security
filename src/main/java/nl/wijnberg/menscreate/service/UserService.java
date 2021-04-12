package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.payload.request.UpdateUserRequest;
import nl.wijnberg.menscreate.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Service
@Validated
public interface UserService {

    // Get - Read
    ResponseEntity<?> getAllUsers();
    ResponseEntity<?> findUserByToken(String token);
    Optional<User> getUserByUsername(String username);
    User getUserById(long id);

    // Post - Create
    void uploadFileToDir(MultipartFile file) throws IOException;
    ResponseEntity<MessageResponse> uploadFileToDB(long userId, MultipartFile file);

    // Put or Post - Update
    ResponseEntity<?> updateUserProfile(String token, @Valid UpdateUserRequest profileUpdate);
    long saveUserProfile(UpdateUserRequest updateUserRequest);
    ResponseEntity<?> updateUser(long id, UpdateUserRequest userUpdate);

    // Delete
    void deleteUser(long id);
//    ResponseEntity<?> deleteUser(String token, String username);
}

//    long saveUser(UpdateUserRequest updateUserRequest);
//    List<User> getAllUsers();
//    long saveUser(User user);
    // public abstract Optional<User> getUserByUsername(String username)
    //public abstract ResponseEntity<?> deleteUser(String token, String username);
    //public Optional<User> getUserByUsername(String username)


//ResponseEntity<?> getAllUsers();
//
//    void updateEmail(long id, User user);

