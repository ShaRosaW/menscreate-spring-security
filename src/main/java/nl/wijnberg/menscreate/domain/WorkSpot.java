package nl.wijnberg.menscreate.domain;

import nl.wijnberg.menscreate.domain.enums.EBookingSpace;
import nl.wijnberg.menscreate.domain.enums.EDayPart;
import nl.wijnberg.menscreate.domain.enums.ETimeTable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class WorkSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workSpotId;

    @Enumerated(EnumType.STRING)
    private EBookingSpace bookingSpace;

    @Column
    private LocalDate bookingDate;

    @Column
    private EDayPart dayPart;

    @Column
    private ETimeTable timeTable;
}
