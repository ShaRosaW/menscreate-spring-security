package nl.wijnberg.menscreate.payload.response;

public class BookingResponse {

    private long userId;
    private long bookingId;
    private String boxName;
    private String bookingDate;


    public BookingResponse(long userId, long bookingId, String boxName, String bookingDate) {
        this.setUserId(userId);
        this.setBookingId(bookingId);
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

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

}