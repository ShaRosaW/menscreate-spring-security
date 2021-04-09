//package nl.wijnberg.menscreate.domain;
//
//import nl.wijnberg.menscreate.domain.enums.EDayPart;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//
//@Entity
//public class DayPart {
//    @Id
//    @GeneratedValue
//            (strategy = GenerationType.AUTO,
//                    generator="native")
//
//    @GenericGenerator(
//            name = "native",
//            strategy = "native")
//
//    @Column(columnDefinition = "serial")
//    private long id;
//
//    @Enumerated(EnumType.STRING)
//    private EDayPart name;
//
//    public DayPart() {
//    }
//
//    public DayPart(EDayPart name) {
//        this.name = name;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public EDayPart getName() {
//        return name;
//    }
//
//    public void setName(EDayPart name) {
//        this.name = name;
//    }
//}
