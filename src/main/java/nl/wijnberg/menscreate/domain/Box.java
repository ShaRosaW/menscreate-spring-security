package nl.wijnberg.menscreate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "box")
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,
                    generator = "native"
    )
    @Column(columnDefinition = "serial", name = "box_id")
    private Long boxId;

    @Column
    private String ingredients;

    @Column
    private String allergyInfo;

    @Column
    private int amountPeople;

    @Column
    private String extraInfo;

    @JsonIgnore
    @OneToOne(mappedBy = "box")
    private BookingType bookingType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boxtype_id")
    private BoxType boxType;

    public Box() {
    }

    public Box(String ingredients, String allergyInfo, int amountPeople, String extraInfo, BoxType boxType) {
        this.ingredients = ingredients;
        this.allergyInfo = allergyInfo;
        this.amountPeople = amountPeople;
        this.extraInfo = extraInfo;
        this.boxType = boxType;
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAllergyInfo() {
        return allergyInfo;
    }

    public void setAllergyInfo(String allergyInfo) {
        this.allergyInfo = allergyInfo;
    }

    public int getAmountPeople() {
        return amountPeople;
    }

    public void setAmountPeople(int amountPeople) {
        this.amountPeople = amountPeople;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public BookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    public BoxType getBoxType() {
        return boxType;
    }

    public void setBoxType(BoxType boxType) {
        this.boxType = boxType;
    }
}



//
//    @Column
//    private String boxName;
//
//    @Column
//    private String boxImage;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "boxtype_id")
//    private BoxType boxType;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "box_celebration",
//            joinColumns =
//                    {@JoinColumn(name = "special_box", referencedColumnName = "box_id")},
//            inverseJoinColumns =
//                    {@JoinColumn(name = "celebration", referencedColumnName = "celebration_id")}
//    )
//    private Celebration celebration;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "box_coaching",
//            joinColumns =
//                    {@JoinColumn(name = "special_box", referencedColumnName = "box_id")},
//            inverseJoinColumns =
//                    {@JoinColumn(name = "coaching", referencedColumnName = "coaching_id")}
//    )
//    private Coaching coaching;
//
//    @OneToMany(mappedBy = "box")
//    private Set<Booking> bookingSet;


//    @Column
//    private boolean isAvailable;
//