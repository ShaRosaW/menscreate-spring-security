package nl.wijnberg.menscreate.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "special_box")
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
                    generator = "native"
    )
    @Column(columnDefinition = "serial")
    private Long boxId;

    @Column
    private String boxName;

    @Column
    private String boxImage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boxtype_id")
    private BoxType boxType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "box_celebration",
            joinColumns =
                    {@JoinColumn(name = "special_box", referencedColumnName = "box_id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "celebration", referencedColumnName = "celebration_id")}
    )
    private Celebration celebration;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "box_coaching",
            joinColumns =
                    {@JoinColumn(name = "special_box", referencedColumnName = "box_id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "coaching", referencedColumnName = "coaching_id")}
    )
    private Coaching coaching;

    @OneToMany(mappedBy = "box")
    private Set<Booking> bookingSet;

}
//    @Column
//    private boolean isAvailable;
//