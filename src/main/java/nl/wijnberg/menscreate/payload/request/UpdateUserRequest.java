package nl.wijnberg.menscreate.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UpdateUserRequest {

    @Size(max = 50)
    @Email
    private String email;

//    private long phoneNumber;
//    // or String?
//    private int age;
//    private String gender;
//    // enum gender?

    @Size(min = 6, max = 40)
    private String password;

    @Size(min = 6, max = 40)
    private String repeatedPassword;

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
}

// todo: in user profile make it possible to add more info
//      such as phonenumber, age, gender, profile image