//package nl.wijnberg.menscreate.domain;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import nl.wijnberg.menscreate.domain.enums.EBoxType;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "box_type")
//public class BoxType {
//    @Id
//    @GeneratedValue(
//            strategy= GenerationType.IDENTITY,
//            generator="native"
//    )
//    @Column(columnDefinition = "serial")
//    private long boxtypeId;
//
//    @Enumerated(EnumType.STRING)
//    private EBoxType name;
//
////    @JsonIgnore
////    @OneToMany (mappedBy = "box_type")
////    private Set<Box> boxSet;
//
//    public long getBoxtypeId() {
//        return boxtypeId;
//    }
//
//    public EBoxType getName() {
//        return name;
//    }
//
//    public void setName(EBoxType name) {
//        this.name = name;
//    }
//
////    public Set<Box> getBoxSet() {
////        return boxSet;
////    }
//}
