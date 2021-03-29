package nl.wijnberg.menscreate.payload.request;

import nl.wijnberg.menscreate.domain.enums.EBookingType;
import nl.wijnberg.menscreate.domain.enums.ESpaceType;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class SpaceRequest {

//    @NotNull
//    private EBookingType bookingType;
//
//    private String bookingimage;
    @NotNull
    private ESpaceType spaceType;

    @NotNull
    private int amountPeople;

    private String extraInfo;



    public SpaceRequest(
//            @NotNull EBookingType bookingType, String bookingimage,
            @NotNull ESpaceType spaceType,
            @NotNull int amountPeople, String extraInfo) {
//        this.bookingType = bookingType;
//        this.bookingimage = bookingimage;
        this.spaceType = spaceType;
        this.amountPeople = amountPeople;
        this.extraInfo = extraInfo;

    }

//    public EBookingType getBookingType() {
//        return bookingType;
//    }
//
//    public void setBookingType(EBookingType bookingType) {
//        this.bookingType = bookingType;
//    }
//
//    public String getBookingimage() {
//        return bookingimage;
//    }
//
//    public void setBookingimage(String bookingimage) {
//        this.bookingimage = bookingimage;
//    }
public ESpaceType getSpaceType() {
    return spaceType;
}

    public void setSpaceType(ESpaceType spaceType) {
        this.spaceType = spaceType;
    }

    public int getAmountPeople() {
        return amountPeople;
    }

    public void setAmountPeople(int amountPeople) {
        this.amountPeople = amountPeople;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    }
