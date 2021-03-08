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
    void updateUser(long id, User user);
//    long saveUser(User user);
    void deleteUser(long id);

//    ResponseEntity<?> updateUserById(String token,  @Valid UpdateUserRequest userRequest);
}

//ResponseEntity<?> getAllUsers();
//
//    void updateEmail(long id, User user);

