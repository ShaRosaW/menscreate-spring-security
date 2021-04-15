package nl.wijnberg.menscreate.payload.response;

//import nl.wijnberg.menscreate.domain.BookingType;
//import nl.wijnberg.menscreate.domain.DayPart;
import nl.wijnberg.menscreate.domain.User;
//import nl.wijnberg.menscreate.domain.enums.EBookingType;
//import nl.wijnberg.menscreate.domain.enums.EDayPart;
//import nl.wijnberg.menscreate.domain.enums.ETimeTable;

import java.time.LocalDate;
import java.util.List;

public class BookingResponse {

    private Long userId;
    private long bookingId;
    private String boxName;
    private String bookingDate;
//    private DayPart dayPart;

//    public BookingResponse(User userId,
//                           String boxName,
//                           String bookingDate
////            , DayPart dayPart
//    ) {
//        this.userId = userId;
//        this.boxName = boxName;
//        this.bookingDate = bookingDate;
////        this.dayPart = dayPart;
//    }

    public BookingResponse(List<BookingResponse> bookingResponses) {
    }

    public BookingResponse(LocalDate bookingDate
//            , EDayPart dayPart
    ) {

    }

    public BookingResponse(User user, Long bookingId, String boxName, String bookingDate
    ) {
        this.userId = user.getId();
        this.setBookingId(bookingId); // todo: investigate later
        this.boxName = boxName;
        this.bookingDate = bookingDate;
    }

    public BookingResponse(User user, String toString) {
    }

    public BookingResponse(User user, String boxName, String toString) {

    }

//    public BookingResponse(User user, String toString, DayPart dayPart) {
//    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

//    public DayPart getDayPart() {
//        return dayPart;
//    }
//
//    public void setDayPart(DayPart dayPart) {
//        this.dayPart = dayPart;
//    }
}



    //    private BookingType bookingType;
//    private String timeTable;
//    private Boolean morning;
//    private Boolean afternoon;
//    private Boolean wholeDay;
//
//    public BookingResponse(long bookingId,
//                           BookingType bookingType,
//                           String bookingDate, String dayPart) {
//        this.userId = userId;
//        this.bookingId = bookingId;
//        this.bookingType = bookingType;
//        this.bookingDate = bookingDate;
//        this.dayPart = dayPart;
////        this.timeTable = timeTable;
//
//    }

//    public BookingResponse(boolean morning, boolean afternoon, boolean wholeDay) {
//        this.morning = morning;
//        this.afternoon = afternoon;
//        this.wholeDay = wholeDay;
//    }

//    public BookingResponse(User user, Long bookingId, EBookingType name, String toString, String name1) {
//    }


//    public BookingResponse(User id, EBookingType name, String bookingDate, String dayPart) {
//
//    }
//
//    public Boolean getMorning() {
//        return morning;
//    }
//
//    public void setMorning(Boolean morning) {
//        this.morning = morning;
//    }
//
//    public Boolean getAfternoon() {
//        return afternoon;
//    }
//
//    public void setAfternoon(Boolean afternoon) {
//        this.afternoon = afternoon;
//    }
//
//    public Boolean getWholeDay() {
//        return wholeDay;
//    }
//
//    public void setWholeDay(Boolean wholeDay) {
//        this.wholeDay = wholeDay;
//    }
//    public BookingResponse(long id, EBookingType name, String toString, String name1, String name2) {
//    }

//    public BookingResponse(long id, Long bookingtypeId, String toString, String name, String name1) {
//    }


//    public String getTimeTable() {
//        return timeTable;
//    }
//
//    public void setTimeTable(String timeTable) {
//        this.timeTable = timeTable;
//    }

//    public BookingType getBookingType() {
//        return bookingType;
//    }
//
//    public void setBookingType(BookingType bookingType) {
//        this.bookingType = bookingType;
//    }
