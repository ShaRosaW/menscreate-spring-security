//package nl.wijnberg.menscreate.domain;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import nl.wijnberg.menscreate.domain.enums.ESpaceType;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "space")
//public class Space {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY,
//            generator = "native")
//    @Column(columnDefinition = "serial", name = "space_id")
//    private Long spaceId;
//
////    @Enumerated(EnumType.STRING)
////    private ESpaceType space;
//
//    @Column
//    private int amountPeople;
//
//    @Column
//    private String extraInfo;
//
//    @JsonIgnore
//    @OneToOne(mappedBy = "space")
//    private BookingType bookingType;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "spacetype_id")
//    private SpaceType spaceType;
//
//    public Space() {
//    }
//
//    public Space(int amountPeople, String extraInfo, SpaceType spaceType) {
//        this.amountPeople = amountPeople;
//        this.extraInfo = extraInfo;
//        this.spaceType = spaceType;
//    }
//
//    public Space(String name, int amountPeople, String extraInfo) {
//    }
//
//    public Long getSpaceId() {
//        return spaceId;
//    }
//
//    public void setSpaceId(Long spaceId) {
//        this.spaceId = spaceId;
//    }
//
//    public int getAmountPeople() {
//        return amountPeople;
//    }
//
//    public void setAmountPeople(int amountPeople) {
//        this.amountPeople = amountPeople;
//    }
//
//    public String getExtraInfo() {
//        return extraInfo;
//    }
//
//    public void setExtraInfo(String extraInfo) {
//        this.extraInfo = extraInfo;
//    }
//
//    public BookingType getBookingType() {
//        return bookingType;
//    }
//
//    public void setBookingType(BookingType bookingType) {
//        this.bookingType = bookingType;
//    }
//
//    public SpaceType getSpaceType() {
//        return spaceType;
//    }
//
//    public void setSpaceType(SpaceType spaceType) {
//        this.spaceType = spaceType;
//    }
//}
//
//
////
////    @OneToMany(mappedBy = "space")
////    private Set<Booking> bookings;
//
//
////@Column
////    private LocalDate bookingDate;
////
////    @Column
////    private EDayPart dayPart;
////
////    @Column
////    private ETimeTable timeTable;
////
////    @ManyToOne (fetch = FetchType.EAGER)
////    @JoinColumn (name = "user_id")
////    private User user;
////
////    @ManyToOne (fetch = FetchType.EAGER)
////    @JoinColumn (name = "workspot_id")
////    private WorkSpot workSpot;
////
////    @ManyToOne (fetch = FetchType.EAGER)
////    @JoinColumn (name = "workarea_id")
////    private WorkArea workArea;
////
////    @ManyToOne (fetch = FetchType.EAGER)
////    @JoinColumn (name = "kitchenspot_id")
////    private KitchenSpot kitchenSpot;
////
////    @ManyToOne (fetch = FetchType.EAGER)
////    @JoinColumn (name = "kitchenarea_id")
////    private KitchenArea kitchenArea;