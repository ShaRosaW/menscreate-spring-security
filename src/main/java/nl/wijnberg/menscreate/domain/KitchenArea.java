package nl.wijnberg.menscreate.domain;

import nl.wijnberg.menscreate.domain.enums.EBookingSpace;
import nl.wijnberg.menscreate.domain.enums.EDayPart;
import nl.wijnberg.menscreate.domain.enums.ETimeTable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "kitchen_area")
public class KitchenArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kitchenAreaId;

    @Enumerated(EnumType.STRING)
    private EBookingSpace bookingSpace;

    @Column
    private LocalDate bookingDate;

    @Column
    private EDayPart dayPart;

    @Column
    private ETimeTable timeTable;

    @Column
    private String bookingInfo;

    @Column
    private int amountPeople;

//    @Column
//    private int bookingMoment;
}
