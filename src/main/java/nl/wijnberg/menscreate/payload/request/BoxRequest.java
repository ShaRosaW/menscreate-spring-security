package nl.wijnberg.menscreate.payload.request;

import nl.wijnberg.menscreate.domain.enums.EBookingType;
import nl.wijnberg.menscreate.domain.enums.EBoxType;
import nl.wijnberg.menscreate.domain.enums.ESpaceType;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class BoxRequest {
    @NotNull
    private EBookingType bookingType;

    private String bookingimage;

    private String ingredients;

    private String allergyInfo;

    @NotNull
    private int amountPeople;

    private String extraInfo;

    @NotNull
    private EBoxType boxType;

    public BoxRequest(@NotNull EBookingType bookingType, String bookingimage, String ingredients, String allergyInfo, @NotNull int amountPeople, String extraInfo, @NotNull EBoxType boxType) {
        this.bookingType = bookingType;
        this.bookingimage = bookingimage;
        this.ingredients = ingredients;
        this.allergyInfo = allergyInfo;
        this.amountPeople = amountPeople;
        this.extraInfo = extraInfo;
        this.boxType = boxType;
    }

    public EBookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(EBookingType bookingType) {
        this.bookingType = bookingType;
    }

    public String getBookingimage() {
        return bookingimage;
    }

    public void setBookingimage(String bookingimage) {
        this.bookingimage = bookingimage;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAllergyInfo() {
        return allergyInfo;
    }

    public void setAllergyInfo(String allergyInfo) {
        this.allergyInfo = allergyInfo;
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

    public EBoxType getBoxType() {
        return boxType;
    }

    public void setBoxType(EBoxType boxType) {
        this.boxType = boxType;
    }
}
