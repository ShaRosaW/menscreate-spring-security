package nl.wijnberg.menscreate.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "native"
    )
    @Column(columnDefinition = "serial", name = "booking_id")
    private Long bookingId;

    @Column
    private LocalDate bookingDate;

    @Column
    private String boxName;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Booking() {
    }

    //todo: these constructors are not used, leave in or remove?
//    public Booking(LocalDate bookingDate,
//                   String boxName) {
//        this.bookingDate = bookingDate;
//        this.boxName = boxName;
//    }

//    public Booking(LocalDate bookingDate,
//                   String boxName,
//                   User user) {
//        this.bookingDate = bookingDate;
//        this.boxName = boxName;
//        this.user = user;
//    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUser(long userId) {
        this.setUser(userId);
    }

}