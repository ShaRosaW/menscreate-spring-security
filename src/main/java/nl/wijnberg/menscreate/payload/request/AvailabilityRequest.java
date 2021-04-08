package nl.wijnberg.menscreate.payload.request;

import nl.wijnberg.menscreate.domain.DayPart;
import nl.wijnberg.menscreate.domain.enums.EDayPart;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvailabilityRequest {
    @NotNull
    private LocalDate bookingDate;

    private DayPart dayPart;
    private String boxName;

    public AvailabilityRequest(@NotNull LocalDate bookingDate, DayPart dayPart,
                               String boxName
    ) {
        this.bookingDate = bookingDate;
        this.dayPart = dayPart;
        this.boxName = boxName;
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

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }
}
