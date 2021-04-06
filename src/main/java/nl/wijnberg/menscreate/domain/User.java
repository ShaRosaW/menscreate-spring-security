package nl.wijnberg.menscreate.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(columnDefinition = "serial")
    private long id;
    private String username;
    private String email;
    private String password;

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
//    private UserProfileInfo userProfileInfo;

    //for bookings in profile with image file as well
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String phoneNumber;

    // relevant for coaching, which is not implemented for now.
//    @Column
//    private int age;
//    @Column
//    private String gender;
    // turn into enum ?


    @ManyToMany
    @JoinTable (name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

//    @OneToMany(fetch = FetchType.LAZY,
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private Set<Booking> bookings;

//    @OneToOne(mappedBy = "user")
//    private File file;

    public User() {

    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


//    public User(String username, String email, String password, String firstName, String lastName, String phoneNumber, Set<Booking> bookings, File file) {
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.phoneNumber = phoneNumber;
//        this.bookings = bookings;
//        this.file = file;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

//    public Set<Booking> getBookings() {
//        return bookings;
//    }
//
//    public void setBookings(Set<Booking> bookings) {
//        this.bookings = bookings;
//    }
//
//    public File getFile() {
//        return file;
//    }
//
//    public void setFile(File file) {
//        this.file = file;
//    }
//
//    public void setFile(Long fileId) {
//    }

//    public UserProfileInfo getUserProfileInfo() {
//        return userProfileInfo;
//    }
//
//    public void setUserProfileInfo(UserProfileInfo userProfileInfo) {
//        this.userProfileInfo = userProfileInfo;
//    }
}
