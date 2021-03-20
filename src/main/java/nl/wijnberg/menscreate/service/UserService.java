package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.payload.request.UpdateUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Service
@Validated
public interface UserService {
    List<User> getAllUsers();
    ResponseEntity<?> findUserByToken(String token);
    User getUserById(long id);
    void uploadFile(MultipartFile file) throws IOException;
    void updateUser(long id, UpdateUserRequest userUpdate);
//    long saveUser(User user);
    void deleteUser(long id);

    long saveUser(UpdateUserRequest updateUserRequest);

//    ResponseEntity<?> updateUserById(String token, @Valid UpdateUserRequest userUpdate);
    // public abstract Optional<User> getUserByUsername(String username)
    //public abstract ResponseEntity<?> deleteUser(String token, String username);
    //public Optional<User> getUserByUsername(String username)
}

//ResponseEntity<?> getAllUsers();
//
//    void updateEmail(long id, User user);

