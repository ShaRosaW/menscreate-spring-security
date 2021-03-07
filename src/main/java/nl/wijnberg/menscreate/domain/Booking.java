package nl.wijnberg.menscreate.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

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

    public Booking() {

    }

    public Booking(String bookingType, String bookingInfo, Date bookingDate, String bookingMoment) {
        this.bookingType = bookingType;
        this.bookingInfo = bookingInfo;
        this.bookingDate = bookingDate;
        this.bookingMoment = bookingMoment;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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
