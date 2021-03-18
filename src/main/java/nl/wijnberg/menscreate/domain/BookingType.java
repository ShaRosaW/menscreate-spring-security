package nl.wijnberg.menscreate.domain;

import nl.wijnberg.menscreate.domain.enums.EBookingType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "booking_type")
public class BookingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
            generator = "native"
    )
    @Column(columnDefinition = "serial")
    private Long bookingtypeId;

    @Enumerated(EnumType.STRING)
    private EBookingType name;

    @Column
    private String bookingimage;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "booking_space",
            joinColumns =
                    {@JoinColumn(name = "booking_type", referencedColumnName = "bookingtype_id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "space", referencedColumnName = "space_id")}
    )
    private Space space;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "booking_box",
            joinColumns =
                    {@JoinColumn(name = "booking_type", referencedColumnName = "bookingtype_id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "box", referencedColumnName = "box_id")}
    )
    private Box box;

    @OneToMany(mappedBy = "bookingtype")
    private Set<Booking> bookingSet;

    public BookingType(EBookingType name, String bookingimage, Space space, Box box) {
        this.name = name;
        this.bookingimage = bookingimage;
        this.space = space;
        this.box = box;
    }

    public BookingType() {
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

    public String getBookingimage() {
        return bookingimage;
    }

    public void setBookingimage(String bookingimage) {
        this.bookingimage = bookingimage;
    }

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

    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }
}



//    @Column
//    private String bookingtypename;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "bookingtype_id")
//    private BookingType bookingType;