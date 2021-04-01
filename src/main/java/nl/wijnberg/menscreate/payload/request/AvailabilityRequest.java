package nl.wijnberg.menscreate.payload.request;

import nl.wijnberg.menscreate.domain.DayPart;
import nl.wijnberg.menscreate.domain.enums.EDayPart;
//import nl.wijnberg.menscreate.domain.enums.ETimeTable;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvailabilityRequest {
    @NotNull
    private LocalDate bookingDate;

    private DayPart dayPart;
//
//    private ETimeTable timeTable;

    public AvailabilityRequest(@NotNull LocalDate bookingDate, DayPart dayPart
//            , ETimeTable timeTable
    ) {
        this.bookingDate = bookingDate;
        this.dayPart = dayPart;
//        this.timeTable = timeTable;
    }

    public AvailabilityRequest(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
        DayPart dayPart = new DayPart();
        dayPart.setName(EDayPart.MORNING);
        this.dayPart = dayPart;
    }

    public AvailabilityRequest() {
        DayPart dayPart = new DayPart();
        dayPart.setName(EDayPart.MORNING);
        this.dayPart = dayPart;
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
}
