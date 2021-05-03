package nl.wijnberg.menscreate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
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

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private UserProfileInfo userProfileInfo;

    // for bookings in profile
    @JsonIgnore
    private String firstName;
    @JsonIgnore
    private String lastName;
    @JsonIgnore
    private String phoneNumber;

    @ManyToMany
    @JoinTable (name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Booking> bookings;

    @JsonIgnore
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
//    @LazyCollection(LazyCollectionOption.FALSE)
    private List<FileDB> files;

//    @OneToOne(mappedBy = "user")
//    private FileDB fileDB;

//    private List<FileResponse> userFiles;

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

    public long getId() {
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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking){
        this.bookings.add(booking);
    }

    public UserProfileInfo getUserProfileInfo() {
        return userProfileInfo;
    }

    public void setUserProfileInfo(UserProfileInfo userProfileInfo) {
        this.userProfileInfo = userProfileInfo;
    }

    public List<FileDB> getFiles() {
        return files;
    }

    public void setFiles(List<FileDB> files) {
        this.files = files;
    }

}
