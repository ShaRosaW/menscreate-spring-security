package nl.wijnberg.menscreate.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import nl.wijnberg.menscreate.domain.ProfileBuilder;
import nl.wijnberg.menscreate.domain.Role;
import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.domain.UserProfileInfo;
import nl.wijnberg.menscreate.exceptions.DatabaseErrorException;
import nl.wijnberg.menscreate.exceptions.RecordNotFoundException;
import nl.wijnberg.menscreate.payload.request.UpdateUserRequest;
import nl.wijnberg.menscreate.payload.response.MessageResponse;
import nl.wijnberg.menscreate.repository.UserProfileInfoRepository;
import nl.wijnberg.menscreate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Value("${mens.sec.jwtSecret}")
    private String jwtSecret;

    private static final String PREFIX = "Bearer ";

    private PasswordEncoder encoder;
    private UserRepository userRepository;
    private UserProfileInfoRepository profileInfoRepository;
    private UserService userService;
//    private UpdateUserRequest updateUserRequest;
    public static String uploadDirectory = System.getProperty("user.dir") + "/fileUploads/";

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    //todo: works
    // get all users list (for admin)
    @Override
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();
                if(users.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("No Users found!"));
        }
        return ResponseEntity.ok(users);
    }

    //todo:works
    // get user by username
    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //todo: works
    // find user by token
    @Override
    public ResponseEntity<?> findUserByToken(String token) {
        String username = getUsernameFromToken(token);

        if(userExists(username)) {
            return ResponseEntity.ok(findUserByUsername(username));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("User not found"));
    }

    //todo: works
    // find user by username
    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    //todo: works
    // get username from token
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

    //todo: works
    // get user by id
    @Override
    public User getUserById(long id) {
        if (userRepository.existsById(id)) {
            User user = userRepository.findById(id).orElse(null);
            return user;
        }
        else {
            throw new RecordNotFoundException();
        }
    }

//    public Set<Role> getAuthorities()

    @Override
    public void uploadFileToDir(MultipartFile file) throws IOException {
        file.transferTo(new File(uploadDirectory + file.getOriginalFilename() ));
    }

    @Override
    public ResponseEntity<MessageResponse> uploadFileToDB(long id, MultipartFile file) {
        return null;
    }

    // update user profile
    @Override
    public ResponseEntity<?> updateUserProfile(String token, @Valid UpdateUserRequest profileUpdate) {
        if(token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid token"));
        }
        String username = getUsernameFromToken(token);

        if(userExists(username) && updateRequestIsValid(profileUpdate)) {
            User updatedUser = findUserByUsername(username);

            if(!profileUpdate.getPassword().isEmpty() && !profileUpdate.getRepeatedPassword().isEmpty()) {
                updatedUser.setPassword(encoder.encode(profileUpdate.getPassword()));
            }
            if(!userRepository.existsByEmail(profileUpdate.getEmail()) && profileUpdate.getEmail() != null && !profileUpdate.getEmail().isEmpty()) {
                updatedUser.setEmail(profileUpdate.getEmail());
            }
            if(profileUpdate.getPhoneNumber() != null && !profileUpdate.getPhoneNumber().isEmpty()){
            updatedUser.setPhoneNumber(profileUpdate.getPhoneNumber());
            }
            return ResponseEntity.ok().body(userRepository.save(updatedUser));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("User cannot be updated with provided data."));
    }


    private boolean updateRequestIsValid(UpdateUserRequest updateUserRequest) {
        if(updateUserRequest.getPassword().equals(updateUserRequest.getRepeatedPassword())) {
            return true;
        }
        return false;
    }

    public long saveUserProfile(UpdateUserRequest updateUserRequest) {
        User user = new ProfileBuilder(updateUserRequest).buildUser();
        UserProfileInfo userProfileInfo = new ProfileBuilder(updateUserRequest).buildProfileInfo();

        UserProfileInfo addedProfileInfo = profileInfoRepository.save(userProfileInfo);
//        user.setUserProfileInfo(addedProfileInfo);
        userProfileInfo.setUser(user);

        return userRepository.save(user).getId();
    }
    //    @Override
//    public long saveUser(UpdateUserRequest updateUserRequest) {
//        User newUser = userRepository.save();
//        return newUser.getId();
//    }

    //update user
    @Override
    public ResponseEntity<?> updateUser(long id, UpdateUserRequest userUpdate) {
        if (userRepository.existsById(id)) {
            try {
                User existingUser = userRepository.findById(id).orElse(null);
//                existingUser.setUsername(userUpdate.getUsername());
                existingUser.setEmail(userUpdate.getEmail());
                existingUser.setPassword(encoder.encode(userUpdate.getPassword()));
                existingUser.setFirstName(userUpdate.getFirstName());
                existingUser.setLastName(userUpdate.getLastName());
                existingUser.setPhoneNumber(userUpdate.getPhoneNumber());
//                existingUser.setFile(userUpdate.getFileId());
                return ResponseEntity.ok().body(userRepository.save(existingUser));
            }
            catch (Exception ex) {
                throw new DatabaseErrorException();
            }
        }
        else {
            throw new RecordNotFoundException();
        }
    }

//    @Override
//    public ResponseEntity<?> deleteUser(String token, String username) {
//        return null;
//    }
    // todo: find out why user cannot be deleted..
    @Override
    public ResponseEntity<?> deleteUser(String token) {
        String username = getUsernameFromToken(token);
        Optional<User> userProfile = userRepository.findByUsername(username);
        if(userProfile.isPresent() == userExists(username)){
            userRepository.deleteById(username);
            return ResponseEntity.ok().body(new MessageResponse("User has been deleted"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("User can not be deleted"));
    }


    //    @Override
    //    public ResponseEntity<?> deleteUser(String token, String username) {
    //        User userProfile = (User) userService.findUserByToken(getUsernameFromToken(token)).getBody();
    //        userRepository.findByUsername(userProfile.getUsername());
    //        if(userExists(userProfile.getUsername())){
    //            userRepository.deleteByUsername(username);
    //            return ResponseEntity.ok().body(new MessageResponse("User has been deleted"));
    //        }
    //        return ResponseEntity.badRequest().body(new MessageResponse("User can not be deleted"));
    //    }

    //        == profileInfoRepository.findByUserUsername(userProfile.getUsername())) {
    //userRepository.deleteByUsername(userProfile.getUsername());
//userProfile.getUsername() == userRepository.deleteByUsername(username))


    //    @Override
//    public ResponseEntity<?> updateUserById(String token, UpdateUserRequest userUpdate) {
//        if(token == null || token.isEmpty()) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Invalid token"));
//        }
//        String username =  getUsernameFromToken(token);
//
//        if(userExists(username) && updateRequestIsValid(userUpdate)) {
//            User updatedUser = findUserByUsername(username);
//            if(!userUpdate.getPassword().isEmpty() && !userUpdate.getRepeatedPassword().isEmpty()) {
//                updatedUser.setPassword(encoder.encode(userUpdate.getPassword()));
//            }
//            if(!userRepository.existsByEmail(userUpdate.getEmail()) && userUpdate.getEmail() != null && !userUpdate.getEmail().isEmpty()) {
//                updatedUser.setEmail(userUpdate.getEmail());
//            }
//            if(userUpdate.getPhoneNumber() != null && !userUpdate.getPhoneNumber().isEmpty()){
//            updatedUser.setPhoneNumber(userUpdate.getPhoneNumber());
//            }
//            return ResponseEntity.ok().body(userRepository.save(updatedUser));
//        }
//
//        return ResponseEntity.badRequest().body(new MessageResponse("User cannot be updated with provided data."));
//    }

//    // delete user
//    @Override
//    public void deleteUser(long id) {
//        if (userRepository.existsById(id)) {
//            if (userRepository.findById(id).equals(profileInfoRepository.findById(id))){
//            userRepository.deleteById(id);}
//        }
//        else {
//            throw new RecordNotFoundException();
//        }
//    }

//    @Override
//    public ResponseEntity<?> deleteUser(String token, String username) {
//        User userProfile = (User) userService.findUserByToken(getUsernameFromToken(token)).getBody();
//        if (userRepository.findByUsername(userProfile.getUsername())
//        == profileInfoRepository.findByUserUsername(userProfile.getUsername())) {
//            userRepository.deleteByUsername(userProfile.getUsername());
//            return ResponseEntity.ok().body(new MessageResponse("User has been deleted"));
//        }
//        return ResponseEntity.badRequest().body(new MessageResponse("User can not be deleted"));
//    }
//userProfile.getUsername() == userRepository.deleteByUsername(username))


}


//    @Override
//    public long saveUser(UpdateUserRequest updateUserRequest) {
//        User newUser = userRepository.save();
//        return newUser.getId();
//    }





