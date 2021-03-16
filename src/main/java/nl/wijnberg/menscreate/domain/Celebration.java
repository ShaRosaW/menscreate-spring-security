package nl.wijnberg.menscreate.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "celebration")
public class Celebration {
    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
            generator="native"
    )
    @Column(columnDefinition = "serial")
    private long celebrationId;

//    @Column
//    private LocalDate bookingDate;

    @Column
    private String ingredients;

    @Column
    private String allergyInfo;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "celebrationtype_id")
    private CelebrationType celebrationType;
}


// todo: if a celebration is booked with an area, connect them, if not guest?
//    @Enumerated(EnumType.STRING)
//    private EBookingType bookingType;
//    @Column
//    private EDayPart dayPart;
//    @Column
//    private ETimeTable timeTable;
//    private int amountPeople;