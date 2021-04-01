package nl.wijnberg.menscreate.payload.request;

import nl.wijnberg.menscreate.domain.BookingType;
import nl.wijnberg.menscreate.domain.DayPart;
import nl.wijnberg.menscreate.domain.enums.EDayPart;
//import nl.wijnberg.menscreate.domain.enums.ETimeTable;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BookingRequest {
    private long userId;
    private long bookingId;

    @NotNull
    private LocalDate bookingDate;
    @NotNull
    private DayPart dayPart;
//    @NotNull
//    private ETimeTable timeTable;
    @NotNull
    private BookingType bookingType;

    public BookingRequest() {
    }

    public BookingRequest(long userId, long bookingId, @NotNull LocalDate bookingDate, @NotNull DayPart dayPart,
//                          @NotNull ETimeTable timeTable,
                          @NotNull BookingType bookingType) {
        this.userId = userId;
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.dayPart = dayPart;
//        this.timeTable = timeTable;
        this.bookingType = bookingType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public DayPart getDayPart() {
        return dayPart;
    }

    public void setDayPart(DayPart dayPart) {
        this.dayPart = dayPart;
    }
//
//    public ETimeTable getTimeTable() {
//        return timeTable;
//    }
//
//    public void setTimeTable(ETimeTable timeTable) {
//        this.timeTable = timeTable;
//    }

    public BookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

}
