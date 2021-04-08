package nl.wijnberg.menscreate.payload.request;

import nl.wijnberg.menscreate.domain.DayPart;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BookingRequest {
    private long userId;
    private long bookingId;
    @NotNull
    private String boxName;
    @NotNull
    private LocalDate bookingDate;
    @NotNull
    private DayPart dayPart;


    public BookingRequest(long userId, long bookingId, @NotNull String boxName, @NotNull LocalDate bookingDate, @NotNull DayPart dayPart) {
        this.userId = userId;
        this.bookingId = bookingId;
        this.boxName = boxName;
        this.bookingDate = bookingDate;
        this.dayPart = dayPart;
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

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
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
}


//    public BookingRequest(long userId, long bookingId, @NotNull LocalDate bookingDate, @NotNull DayPart dayPart,
////                          @NotNull ETimeTable timeTable,
////                          @NotNull BookingType bookingType
//    ) {
//        this.userId = userId;
//        this.bookingId = bookingId;
//        this.bookingDate = bookingDate;
//        this.dayPart = dayPart;
////        this.timeTable = timeTable;
////        this.bookingType = bookingType;
//    }

    //    @NotNull
//    private ETimeTable timeTable;
//    @NotNull
//    private BookingType bookingType;
//
//    public ETimeTable getTimeTable() {
//        return timeTable;
//    }
//
//    public void setTimeTable(ETimeTable timeTable) {
//        this.timeTable = timeTable;
//    }
//
//    public BookingType getBookingType() {
//        return bookingType;
//    }
//
//    public void setBookingType(BookingType bookingType) {
//        this.bookingType = bookingType;
//    }


