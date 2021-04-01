package nl.wijnberg.menscreate.payload.request;

import nl.wijnberg.menscreate.domain.BoxType;
import nl.wijnberg.menscreate.domain.enums.EBoxType;

import javax.validation.constraints.NotNull;

public class BoxRequest {
//    @NotNull
//    private EBookingType bookingType;

//    private String bookingimage;

    private String ingredients;

//    private String allergyInfo;

    @NotNull
    private int amountPeople;

    private String extraInfo;

    @NotNull
    private BoxType boxType;

    public BoxRequest(
//            @NotNull EBookingType bookingType,
//                      String bookingimage,
                      String ingredients,
//                      String allergyInfo,
                      @NotNull int amountPeople, String extraInfo, @NotNull BoxType boxType) {
//        this.bookingType = bookingType;
//        this.bookingimage = bookingimage;
        this.ingredients = ingredients;
//        this.allergyInfo = allergyInfo;
        this.amountPeople = amountPeople;
        this.extraInfo = extraInfo;
        this.boxType = boxType;
    }

    public BoxRequest(String ingredients, @NotNull int amountPeople, String extraInfo) {
        this.ingredients = ingredients;
        this.amountPeople = amountPeople;
        this.extraInfo = extraInfo;
        BoxType boxType = new BoxType();
        boxType.setName(EBoxType.CAKE);
    }
    public BoxRequest() {
        BoxType boxType = new BoxType();
        boxType.setName(EBoxType.CAKE);
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

//    public String getAllergyInfo() {
//        return allergyInfo;
//    }
//
//    public void setAllergyInfo(String allergyInfo) {
//        this.allergyInfo = allergyInfo;
//    }

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

    public BoxType getBoxType() {
        return boxType;
    }

    public void setBoxType(BoxType boxType) {
        this.boxType = boxType;
    }
}
