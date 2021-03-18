package nl.wijnberg.menscreate.payload.response;

public class BoxResponse {

    private Long id;

    private String bookingType;

    private String bookingimage;

    private String ingredients;

    private String allergyInfo;

    private int amountPeople;

    private String extraInfo;

    private String boxType;

    public BoxResponse() {
    }

    public BoxResponse(Long id, String bookingType, String bookingimage, String ingredients, String allergyInfo, int amountPeople, String extraInfo, String boxType) {
        this.id = id;
        this.bookingType = bookingType;
        this.bookingimage = bookingimage;
        this.ingredients = ingredients;
        this.allergyInfo = allergyInfo;
        this.amountPeople = amountPeople;
        this.extraInfo = extraInfo;
        this.boxType = boxType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
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

    public String getBoxType() {
        return boxType;
    }

    public void setBoxType(String boxType) {
        this.boxType = boxType;
    }
}
