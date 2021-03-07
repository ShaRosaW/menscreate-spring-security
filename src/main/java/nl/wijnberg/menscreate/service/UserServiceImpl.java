package nl.wijnberg.menscreate.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.exceptions.DatabaseErrorException;
import nl.wijnberg.menscreate.exceptions.RecordNotFoundException;
import nl.wijnberg.menscreate.payload.request.UpdateUserRequest;
import nl.wijnberg.menscreate.payload.response.MessageResponse;
import nl.wijnberg.menscreate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Value("${novi.sec.jwtSecret}")
    private String jwtSecret;

    private static final String PREFIX = "Bearer ";

    private UserRepository userRepository;
    public static String uploadDirectory = System.getProperty("user.dir") + "/fileUploads/";
//    private PasswordEncoder encoder;

    @Override
    public List<User> getAllUsers() {

         return userRepository.findAll();

//        if(users.isEmpty()) {
//            return ResponseEntity.badRequest().body(new MessageResponse("No Users found!"));
//        }
//        return ResponseEntity.ok(users);
    }

    @Override
    public User getUserById(long id) {
        if (userRepository.existsById(id)) {
            return userRepository.findById(id).orElse(null);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        file.transferTo(new File(uploadDirectory + file.getOriginalFilename() ));
    }

    @Override
    public void updateUser(long id, User user) {
        if (userRepository.existsById(id)) {
            try {
                User existingUser = userRepository.findById(id).orElse(null);
                existingUser.setUsername(user.getUsername());
                existingUser.setEmail(user.getEmail());
                existingUser.setPassword(user.getPassword());
                userRepository.save(existingUser);
            }
            catch (Exception ex) {
                throw new DatabaseErrorException();
            }
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public long saveUser(User user) {
        User newUser = userRepository.save(user);
        return newUser.getId();
    }

    @Override
    public void deleteUser(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public ResponseEntity<?> findUserByToken(String token) {
        String username = getUsernameFromToken(token);

        if(userExists(username)) {
            return ResponseEntity.ok(findUserByUsername(username));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
    }

    private String getUsernameFromToken(String token) {
        String tokenWithoutBearer = removePrefix(token);

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
                .parseClaimsJws(tokenWithoutBearer).getBody();

        return claims.getSubject();
    }

    private String removePrefix(String token) {
        return token.replace(PREFIX, "");
    }

    private boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}

//    @Override
//    public ResponseEntity<?> updateUserById(String token, UpdateUserRequest userRequest) {
//        if(token == null || token.isEmpty()) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Invalid token"));
//        }
//        String username =  getUsernameFromToken(token);
//
//        if(userExists(username) && updateRequestIsValid(userRequest)) {
//            User updatedUser = findUserByUsername(username);
//            if(!userRequest.getPassword().isEmpty() && !userRequest.getRepeatedPassword().isEmpty()) {
//                updatedUser.setPassword(encoder.encode(userRequest.getPassword()));
//            }
//            if(userRequest.getEmail() != null && !userRequest.getEmail().isEmpty()) {
//                updatedUser.setEmail(userRequest.getEmail());
//            }
//            return ResponseEntity.ok().body(userRepository.save(updatedUser));
//        }
//
//        return ResponseEntity.badRequest().body(new MessageResponse("User cannot be updated with provided data."));
//    }

//    private boolean updateRequestIsValid(UpdateUserRequest updateUserRequest) {
//        if(updateUserRequest.getPassword().equals(updateUserRequest.getRepeatedPassword())) {
//            return true;
//        }
//        return false;
//    }

//    @Autowired
//    public void setEncoder(PasswordEncoder encoder) {
//        this.encoder = encoder;
//    }