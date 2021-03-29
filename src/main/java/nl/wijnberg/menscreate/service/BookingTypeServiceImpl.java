package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.domain.*;
import nl.wijnberg.menscreate.domain.enums.EBookingType;
import nl.wijnberg.menscreate.domain.enums.ESpaceType;
import nl.wijnberg.menscreate.payload.request.AvailabilityRequest;
import nl.wijnberg.menscreate.payload.request.BookingRequest;
import nl.wijnberg.menscreate.payload.request.BoxRequest;
import nl.wijnberg.menscreate.payload.request.SpaceRequest;
import nl.wijnberg.menscreate.payload.response.BookingResponse;
import nl.wijnberg.menscreate.payload.response.MessageResponse;
import nl.wijnberg.menscreate.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

public class BookingTypeServiceImpl implements BookingService {
    private static final String NOT_FOUND_ERROR = "Error: Request is not found.";

    @Autowired
    private BookingRepository bookingRepository;
    private BookingTypeRepository bookingTypeRepository;
    private UserRepository userRepository;
    private UserService userService;
    private SpaceTypeRepository spaceTypeRepository;
    private BoxTypeRepository boxTypeRepository;


    public String spaceTypeOption(SpaceType spacetype) {
        String typeOfSpace = null;
        switch (spacetype.getName()) {
            case WORK_AREA:
                typeOfSpace = "Meeting space";
                break;
                case WORK_SPOT:
                    typeOfSpace = "Individual space";
                    break;
        } return typeOfSpace;
    }
    public String boxTypeOption(BoxType boxtype) {
        String typeOfBox = null;
        switch (boxtype.getName()) {
            case CAKE:
                typeOfBox = "Choose a cake!";
                break;
            case CATERING:
                typeOfBox = "Book a cateringbox";
                break;
        } return typeOfBox;
    }
    public String bookingTypeOption(BookingType bookingtype) {
        String typeOfBooking = null;
        switch (bookingtype.getName()) {
            case SPACE:
                typeOfBooking = "Booking a space";
                break;
            case BOX:
                typeOfBooking = "Booking a box";
                break;
        } return typeOfBooking;
    }
    public ResponseEntity<MessageResponse> createBookingByBookingType(@Valid BookingRequest bookingRequest){
//        if(Boolean.TRUE.equals(bookingTypeRepository.existsByName(String.valueOf(bookingRequest.getBookingType().getName()))));
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "")
        BookingType bookingType = bookingTypeRepository.findByName(bookingRequest.getBookingType().getName());

        Booking booking = new Booking(
                bookingRequest.getBookingDate().toString(),
                bookingRequest.getDayPart().toString(),
                bookingRequest.getUserId(),
                bookingType
        );

        String typeOfBooking = String.valueOf(bookingRequest.getBookingType());

        if (typeOfBooking == null){
            BookingType spacetype = bookingTypeRepository.findByName(EBookingType.SPACE);

            booking.setBookingType(spacetype);
        } else {
            switch (typeOfBooking){
                case "boxtype":
                BookingType boxtype = bookingTypeRepository.findByName(EBookingType.BOX);
                booking.setBookingType(boxtype);
                break;
            }
        }

        bookingTypeOption(bookingType);
        bookingTypeRepository.save(bookingType);
        return ResponseEntity.ok(new MessageResponse("Type of booking is now set!")
        );
    }

        public ResponseEntity<MessageResponse> createBookingBySpaceType(SpaceRequest spaceRequest){
//    if(Boolean.TRUE.equals(spaceTypeRepository.existsByName(spaceRequest.getSpaceType().name())));
//        Space spacetype = new Space(spaceRequest.getSpaceType().name(),
//                spaceRequest.getAmountPeople(),
//                spaceRequest.getExtraInfo()
//                );
//        String strSpaces = String.valueOf(spaceRequest.getSpaceType());
//        SpaceType spaces = new SpaceType();
//
//        if (strSpaces == null){
//            SpaceType workSpot = spaceTypeRepository.findByName(ESpaceType.WORK_SPOT)
//                    .orElseThrow(() -> new RuntimeException(NOT_FOUND_ERROR));
//            spaces.add(workSpot);
//        } else {
//            strSpaces.forEach(bookingspace -> {
//                if ("meeting".equals(bookingspace)) {
//                     SpaceType workArea = spaceTypeRepository.findByName(ESpaceType.WORK_AREA)
//                             .orElseThrow(() -> new RuntimeException(NOT_FOUND_ERROR));
//                     spaces.add(workArea);
//                }
//            });
//        }
//    spacetype.setSpaceType(spaces);
//    spaceTypeRepository.save(spacetype);

    return ResponseEntity.ok(new MessageResponse("Successfully booked a space!"));
    }

    @Override
    public ResponseEntity<?> getAllBookings() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseEntity<BookingResponse>> getAllBookingsByUser(String username) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllBookingsByDate() {
        return null;
    }

    @Override
    public ResponseEntity<?> getAvailabilityDayPartCheck(AvailabilityRequest availabilityRequest) {
        return null;
    }

    @Override
    public ResponseEntity<BookingResponse> getBookingById(long bookingId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getUserBookings(String token) {
        return null;
    }

    @Override
    public ResponseEntity<MessageResponse> createBookingByDate(BookingRequest bookingRequest) {
        return null;
    }
//
//    @Override
//    public ResponseEntity<MessageResponse> createBookingByBookingType(BookingRequest bookingRequest) {
//        return null;
//    }

//    @Override
//    public ResponseEntity<MessageResponse> createBookingBySpaceType(SpaceRequest spaceRequest) {
//        return null;
//    }

    @Override
    public ResponseEntity<MessageResponse> createBookingByBoxType(BoxRequest boxRequest) {
        return null;
    }

    @Override
    public long createBooking(Booking booking) {
        return 0;
    }

    @Override
    public BookingRequest updateBooking(long bookingId, BookingRequest bookingUpdate) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteBooking(String token, long bookingId) {
        return null;
    }
}
