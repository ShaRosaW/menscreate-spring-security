package nl.wijnberg.menscreate.domain;

import nl.wijnberg.menscreate.domain.enums.EBookingType;
import nl.wijnberg.menscreate.payload.request.BookingRequest;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "booking_type")
public class BookingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "native"
    )
    @Column(columnDefinition = "serial", name = "id")
    private Long bookingtypeId;

    @Enumerated(EnumType.STRING)
    private EBookingType name;

//    @Column
//    private String bookingimage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "booking_space",
            joinColumns =
                    {@JoinColumn(name = "booking_type", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "space", referencedColumnName = "space_id")}
    )
    private Space space;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "booking_box",
            joinColumns =
                    {@JoinColumn(name = "booking_type", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "box", referencedColumnName = "box_id")}
    )
    private Box box;

//    @OneToMany(mappedBy = "bookings")
//    private Set<Booking> bookingSet;

    public BookingType(EBookingType name, Space space, Box box) {
        this.name = name;
        this.space = space;
        this.box = box;
    }

    public BookingType(EBookingType name, BookingType bookingTypeName) {
    }

    public BookingType(String toString, String name) {
    }

    public BookingType(String toString, String toString1, BookingType bookingTypeName) {
    }

    public BookingType() {

    }

    public BookingType(BookingRequest bookingRequest){
        this.name = bookingRequest.getBookingType().getName();
        this.box = bookingRequest.getBookingType().getBox();
    }


    public Long getBookingtypeId() {
        return bookingtypeId;
    }

    public void setBookingtypeId(Long bookingtypeId) {
        this.bookingtypeId = bookingtypeId;
    }

    public EBookingType getName() {
        return name;
    }

    public void setName(EBookingType name) {
        this.name = name;
    }
//
//    public String getBookingimage() {
//        return bookingimage;
//    }
//
//    public void setBookingimage(String bookingimage) {
//        this.bookingimage = bookingimage;
//    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public void setBooking(Booking booking) {
    }

    public void add(BookingType bookingType) {
    }

//    public Set<Booking> getBookingSet() {
//        return bookingSet;
//    }
//
//    public void setBookingSet(Set<Booking> bookingSet) {
//        this.bookingSet = bookingSet;
//    }
}



//    @Column
//    private String bookingtypename;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "bookingtype_id")
//    private BookingType bookingType;