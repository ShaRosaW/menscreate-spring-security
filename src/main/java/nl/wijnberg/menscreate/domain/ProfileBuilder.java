package nl.wijnberg.menscreate.domain;

import nl.wijnberg.menscreate.payload.request.UpdateUserRequest;

public class ProfileBuilder {

    // Registration for User
    private String username;
    private String email;
    private String password;
    // Profile additional info for User
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public ProfileBuilder(UpdateUserRequest updateUserRequest) {
        this.username = updateUserRequest.getUsername();
        this.email = updateUserRequest.getEmail();
        this.password = updateUserRequest.getPassword();
        this.firstName = updateUserRequest.getFirstName();
        this.lastName = updateUserRequest.getLastName();
        this.phoneNumber = updateUserRequest.getPhoneNumber();
    }
    public User buildUser(){
        return new User(username, email, password);
    }
    public UserProfileInfo buildProfileInfo(){
        return new UserProfileInfo(firstName, lastName, phoneNumber);
    }
}
