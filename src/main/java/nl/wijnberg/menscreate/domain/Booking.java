package nl.wijnberg.menscreate.domain;

import nl.wijnberg.menscreate.domain.enums.EDayPart;
import nl.wijnberg.menscreate.domain.enums.ETimeTable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "native"
    )
    @Column(columnDefinition = "serial")
    private Long bookingId;

    @Column
    private LocalDate bookingDate;

    @Column
    private EDayPart dayPart;

    @Column
    private ETimeTable timeTable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookingtype_id")
    private BookingType bookingType;

    public Booking() {
    }

    public Booking(LocalDate bookingDate, EDayPart dayPart, ETimeTable timeTable, User user, BookingType bookingType) {
        this.bookingDate = bookingDate;
        this.dayPart = dayPart;
        this.timeTable = timeTable;
        this.user = user;
        this.bookingType = bookingType;
    }

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

    public EDayPart getDayPart() {
        return dayPart;
    }

    public void setDayPart(EDayPart dayPart) {
        this.dayPart = dayPart;
    }

    public ETimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(ETimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }
}



//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "space_id")
//    private Space space;

//    @OneToMany(mappedBy = "booking")
//    private List<BookingSerie> bookingSeries;


//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "workspot_id")
//    private WorkSpot workSpot;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "workarea_id")
//    private WorkArea workArea;


//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "box_id")
//    private Box box;

//    @Enumerated(EnumType.STRING)
//    private EBookingSpace bookingSpace;

//

//
//    @ManyToOne (fetch = FetchType.EAGER)
//    @JoinColumn (name = "kitchenspot_id")
//    private KitchenSpot kitchenSpot;
//
//    @ManyToOne (fetch = FetchType.EAGER)
//    @JoinColumn (name = "kitchenarea_id")
//    private KitchenArea kitchenArea;



//    @Column
//    private String bookingInfo;
//    @Column
//    private String bookingType;
//    @Column
//    private Date bookingDate;
//    @Column
//    private Integer bookingMoment;
//
//
//
////    @ManyToMany
////    @JoinTable(name = "bookings",
////            joinColumns = @JoinColumn(name = "user_id"),
////            inverseJoinColumns = @JoinColumn(name = "booking_id"))
////    private Set<User> user;
//
//
//
//    public Booking(String bookingType, String bookingInfo, Date bookingDate, Integer bookingMoment, User user) {
//        this.bookingType = bookingType;
//        this.bookingInfo = bookingInfo;
//        this.bookingDate = bookingDate;
//        this.bookingMoment = bookingMoment;
//        this.user = user;
//    }
//
//    public Long getBookingId() {
//        return bookingId;
//    }
//
//    public void setBookingId(Long bookingId) {
//        this.bookingId = bookingId;
//    }
//
//    public String getBookingType() {
//        return bookingType;
//    }
//
//    public void setBookingType(String bookingType) {
//        this.bookingType = bookingType;
//    }
//
//    public String getBookingInfo() {
//        return bookingInfo;
//    }
//
//    public void setBookingInfo(String bookingInfo) {
//        this.bookingInfo = bookingInfo;
//    }
//
//    public Date getBookingDate() {
//        return bookingDate;
//    }
//
//    public void setBookingDate(Date bookingDate) {
//        this.bookingDate = bookingDate;
//    }
//
//    public Integer getBookingMoment() {
//        return bookingMoment;
//    }
//
//    public void setBookingMoment(Integer bookingMoment) {
//        this.bookingMoment = bookingMoment;
//    }

//    public Set<User> getUser() {
//        return user;
//    }
//
//    public void setUser(Set<User> user) {
//        this.user = user;
//    }

