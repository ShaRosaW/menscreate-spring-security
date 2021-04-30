package nl.wijnberg.menscreate.payload.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BookingRequest {
    private long userId;
    private long bookingId;
    @NotNull
    private String boxName;
    @NotNull
    private LocalDate bookingDate;

    public BookingRequest(long userId,
                          long bookingId,
                          @NotNull String boxName,
                          @NotNull LocalDate bookingDate
    ) {
        this.userId = userId;
        this.bookingId = bookingId;
        this.boxName = boxName;
        this.bookingDate = bookingDate;
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

}



