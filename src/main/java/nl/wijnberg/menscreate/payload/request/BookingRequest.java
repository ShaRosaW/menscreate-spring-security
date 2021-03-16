package nl.wijnberg.menscreate.payload.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public class BookingRequest {
    private long userId;
//    private long bookingId;

    @NotNull
    private LocalDate bookingDate;
    @NotNull
    private int bookingMoment;
    @NotNull
    private int amountPeople;
    private String bookingInfo;
    private String extraInfo;

    // todo: getters, setters and constructor, all arg and no args.
    // also bookingId to al bookingtypes connection.
}
