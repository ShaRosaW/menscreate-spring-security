package nl.wijnberg.menscreate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.wijnberg.menscreate.domain.enums.EBookingSpace;
import nl.wijnberg.menscreate.domain.enums.EDayPart;
import nl.wijnberg.menscreate.domain.enums.ETimeTable;

import javax.persistence.*;

@Entity
@Table(name = "coaching")
public class Coaching {

        @Id
        @GeneratedValue(
                strategy= GenerationType.IDENTITY,
                generator="native"
        )
        @Column(columnDefinition = "serial")
        private long coachingId;

//        @Column
        @Enumerated(EnumType.STRING)
        private EBookingSpace bookingSpace;

//        @Column
//        private LocalDate bookingDate;

        @Column
        private EDayPart dayPart;

        @Column
        private ETimeTable timeTable;

        @Column
        private int amountSessions;

        @Column
        private String specialInfo;

        @ManyToOne (fetch = FetchType.EAGER)
        @JoinColumn(name = "coachingtype_id")
        private CoachingType coachingType;

        @JsonIgnore
        @OneToOne(mappedBy = "coaching")
        private Box box;
}
