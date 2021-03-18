package nl.wijnberg.menscreate.payload.response;

import nl.wijnberg.menscreate.domain.BookingType;
import nl.wijnberg.menscreate.domain.enums.EDayPart;
import nl.wijnberg.menscreate.domain.enums.ETimeTable;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BookingResponse {

    private long userId;
    private long bookingId;
    private LocalDate bookingDate;
    private EDayPart dayPart;
    private ETimeTable timeTable;
    private BookingType bookingType;

    public BookingResponse(long userId, long bookingId, LocalDate bookingDate, EDayPart dayPart, ETimeTable timeTable, BookingType bookingType) {
        this.userId = userId;
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.dayPart = dayPart;
        this.timeTable = timeTable;
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

    public EDayPart getDayPart() {
        return dayPart;
    }

    public void setDayPart(EDayPart dayPart) {
        this.dayPart = dayPart;
    }

    public ETimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(ETimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public BookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }
}