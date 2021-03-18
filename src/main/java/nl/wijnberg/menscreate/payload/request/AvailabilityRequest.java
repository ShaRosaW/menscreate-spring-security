package nl.wijnberg.menscreate.payload.request;

import nl.wijnberg.menscreate.domain.enums.EDayPart;
import nl.wijnberg.menscreate.domain.enums.ETimeTable;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvailabilityRequest {
    @NotNull
    private LocalDate bookingDate;

    private EDayPart dayPart;

    private ETimeTable timeTable;

    public AvailabilityRequest(@NotNull LocalDate bookingDate, EDayPart dayPart, ETimeTable timeTable) {
        this.bookingDate = bookingDate;
        this.dayPart = dayPart;
        this.timeTable = timeTable;
    }

    public AvailabilityRequest() {
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
}
