package nl.wijnberg.menscreate.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UpdateUserRequest {

    // User Profile Update and option to add user information, from client side.

    @Size(min = 6, max = 40)
    private String username;

    @Size(max = 50)
    @Email
    private String email;

    @Size(min = 6, max = 40)
    private String password;

    @Size(min = 6, max = 40)
    private String repeatedPassword;

    // additional info, for bookings

    @Size(max = 40)
    private String firstName;

    @Size(max = 40)
    private String lastName;

    private String phoneNumber;

    // image file to be uploaded in user profile client side.
    private Long fileId;
    private String name;
    private byte[] image;


    public UpdateUserRequest(
            @Size(min = 6, max = 40) String username,
                             @Size(max = 50) @Email String email, @Size(min = 6, max = 40) String password, @Size(min = 6, max = 40) String repeatedPassword, @Size(max = 40) String firstName, @Size(max = 40) String lastName, String phoneNumber, Long fileId, String name, byte[] image) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.fileId = fileId;
        this.name = name;
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

// todo: in user profile make it possible to add more info
//      such as phonenumber, age, gender, profile image