package nl.wijnberg.menscreate.payload.response;

import nl.wijnberg.menscreate.domain.User;
import nl.wijnberg.menscreate.domain.enums.EBookingType;
import nl.wijnberg.menscreate.domain.enums.EDayPart;
//import nl.wijnberg.menscreate.domain.enums.ETimeTable;

import java.time.LocalDate;
import java.util.List;

public class BookingResponse {

    private User userId;
    private long bookingId;
    private EBookingType bookingType;
    private String bookingDate;
    private String dayPart;
//    private String timeTable;
    private Boolean morning;
    private Boolean afternoon;
    private Boolean wholeDay;

    public BookingResponse(long bookingId,
                           EBookingType bookingType,
                           String bookingDate, String dayPart) {
        this.userId = userId;
        this.bookingId = bookingId;
        this.bookingType = bookingType;
        this.bookingDate = bookingDate;
        this.dayPart = dayPart;
//        this.timeTable = timeTable;

    }

    public BookingResponse(List<BookingResponse> bookingResponses) {
    }

    public BookingResponse(User user, Long bookingId, EBookingType name, String toString, String name1) {
    }

    public BookingResponse(LocalDate bookingDate, EDayPart dayPart) {

    }

    public BookingResponse(User id, EBookingType name, String bookingDate, String dayPart) {

    }

    public BookingResponse(boolean morning, boolean afternoon, boolean wholeDay) {
        this.morning = morning;
        this.afternoon = afternoon;
        this.wholeDay = wholeDay;
    }

    public Boolean getMorning() {
        return morning;
    }

    public void setMorning(Boolean morning) {
        this.morning = morning;
    }

    public Boolean getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(Boolean afternoon) {
        this.afternoon = afternoon;
    }

    public Boolean getWholeDay() {
        return wholeDay;
    }

    public void setWholeDay(Boolean wholeDay) {
        this.wholeDay = wholeDay;
    }
//    public BookingResponse(long id, EBookingType name, String toString, String name1, String name2) {
//    }

//    public BookingResponse(long id, Long bookingtypeId, String toString, String name, String name1) {
//    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getDayPart() {
        return dayPart;
    }

    public void setDayPart(String dayPart) {
        this.dayPart = dayPart;
    }

//    public String getTimeTable() {
//        return timeTable;
//    }
//
//    public void setTimeTable(String timeTable) {
//        this.timeTable = timeTable;
//    }

    public EBookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(EBookingType bookingType) {
        this.bookingType = bookingType;
    }
}