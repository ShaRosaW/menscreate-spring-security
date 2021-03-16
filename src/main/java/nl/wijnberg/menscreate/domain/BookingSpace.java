package nl.wijnberg.menscreate.domain;

import nl.wijnberg.menscreate.domain.enums.EBookingSpace;
import nl.wijnberg.menscreate.domain.enums.EDayPart;
import nl.wijnberg.menscreate.domain.enums.ETimeTable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class BookingSpace {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long bookingSpaceId;

    @Enumerated(EnumType.STRING)
    private EBookingSpace bookingSpace;

    @Column
    private LocalDate bookingDate;

    @Column
    private EDayPart dayPart;

    @Column
    private ETimeTable timeTable;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "workspot_id")
    private WorkSpot workSpot;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "workarea_id")
    private WorkArea workArea;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kitchenspot_id")
    private KitchenSpot kitchenSpot;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kitchenarea_id")
    private KitchenArea kitchenArea;

    @OneToMany(mappedBy = "booking_space")
    private List<Booking> bookings;
}
