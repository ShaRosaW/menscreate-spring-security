package nl.wijnberg.menscreate.domain;

import nl.wijnberg.menscreate.domain.enums.EBookingSpace;
import nl.wijnberg.menscreate.domain.enums.EDayPart;
import nl.wijnberg.menscreate.domain.enums.ETimeTable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "work_area")
public class WorkArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long workAreaId;

    @Enumerated(EnumType.STRING)
    private EBookingSpace bookingSpace;

    @Column
    private LocalDate bookingDate;

    @Column
    private EDayPart dayPart;

    @Column
    private ETimeTable timeTable;

    @Column
    private int amountPeople;

    @Column
    private String bookingInfo;

    @Column
    private String extraInfo;
//    @Column
//    private int bookingMoment;

}
