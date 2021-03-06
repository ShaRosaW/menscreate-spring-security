package nl.wijnberg.menscreate.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String bookingType;

    @Column
    private String bookingInfo;

    @Column
    private Date bookingDate;

    @Column
    private String bookingMoment;

    @ManyToMany
    @JoinTable(name = "bookings",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "booking_id"))
    private Set<User> user;

    public Booking(String bookingType, String bookingInfo, Date bookingDate, String bookingMoment, Set<User> user) {
        this.bookingType = bookingType;
        this.bookingInfo = bookingInfo;
        this.bookingDate = bookingDate;
        this.bookingMoment = bookingMoment;
    }

    public Booking() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public String getBookingInfo() {
        return bookingInfo;
    }

    public void setBookingInfo(String bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingMoment() {
        return bookingMoment;
    }

    public void setBookingMoment(String bookingMoment) {
        this.bookingMoment = bookingMoment;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }
}
