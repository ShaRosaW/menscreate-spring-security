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
import java.util.Optional;

@Service
@Validated
public interface UserService {
    ResponseEntity<?> getAllUsers();

    ResponseEntity<?> findUserByToken(String token);

    Optional<User> getUserByUsername(String username);

    User getUserById(long id);

    void uploadFile(MultipartFile file) throws IOException;

    ResponseEntity<?> updateUserProfile(String token, @Valid UpdateUserRequest profileUpdate);

    ResponseEntity<?> updateUser(long id, UpdateUserRequest userUpdate);

    void deleteUser(long id);
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

