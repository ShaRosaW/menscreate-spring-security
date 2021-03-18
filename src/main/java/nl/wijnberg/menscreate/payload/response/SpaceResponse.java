package nl.wijnberg.menscreate.payload.response;


public class SpaceResponse {
    private Long id;

    private String bookingType;

    private String bookingimage;

    private int amountPeople;

    private String extraInfo;

    private String spaceType;


    public SpaceResponse(Long id, String bookingType, String bookingimage, int amountPeople, String extraInfo, String spaceType) {
        this.id = id;
        this.bookingType = bookingType;
        this.bookingimage = bookingimage;
        this.amountPeople = amountPeople;
        this.extraInfo = extraInfo;
        this.spaceType = spaceType;
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

    public String getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(String spaceType) {
        this.spaceType = spaceType;
    }
}
