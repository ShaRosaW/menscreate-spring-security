package nl.wijnberg.menscreate.domain;

import javax.persistence.*;

@Entity
@Table
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator="native"
    )
    @Column(columnDefinition = "serial")
    private Long bookingId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookingspace_id")
    private BookingSpace bookingSpace;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "box_id")
    private Box box;

//    @Enumerated(EnumType.STRING)
//    private EBookingSpace bookingSpace;

//    @Column
//    private LocalDate bookingDate;
//
//    @Column
//    private EDayPart dayPart;
//
//    @Column
//    private ETimeTable timeTable;
//
//    @ManyToOne (fetch = FetchType.EAGER)
//    @JoinColumn (name = "user_id")
//    private User user;
//
//    @ManyToOne (fetch = FetchType.EAGER)
//    @JoinColumn (name = "workspot_id")
//    private WorkSpot workSpot;
//
//    @ManyToOne (fetch = FetchType.EAGER)
//    @JoinColumn (name = "workarea_id")
//    private WorkArea workArea;
//
//    @ManyToOne (fetch = FetchType.EAGER)
//    @JoinColumn (name = "kitchenspot_id")
//    private KitchenSpot kitchenSpot;
//
//    @ManyToOne (fetch = FetchType.EAGER)
//    @JoinColumn (name = "kitchenarea_id")
//    private KitchenArea kitchenArea;

    public Booking() {

    }

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
}
