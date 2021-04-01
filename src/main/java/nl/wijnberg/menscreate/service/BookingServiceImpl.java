package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.domain.*;
import nl.wijnberg.menscreate.domain.enums.EBookingType;
import nl.wijnberg.menscreate.domain.enums.EDayPart;
import nl.wijnberg.menscreate.domain.enums.ESpaceType;
import nl.wijnberg.menscreate.exceptions.DatabaseErrorException;
import nl.wijnberg.menscreate.exceptions.RecordNotFoundException;
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
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

//    private static final String NOT_FOUND_ERROR = "Error: Request is not found.";

    @Autowired
    private BookingRepository bookingRepository;
    private BookingTypeRepository bookingTypeRepository;
    private UserRepository userRepository;
    private UserService userService;
    private SpaceTypeRepository spaceTypeRepository;
    private BoxTypeRepository boxTypeRepository;
    private DayPartRepository dayPartRepository;

    @Override
    public ResponseEntity<?> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingResponse> bookingResponses = (List<BookingResponse>) createBookingResponse(bookings);
        if (bookings.isEmpty()) {
            return ResponseEntity.badRequest().body(new RecordNotFoundException());
        }
        return ResponseEntity.ok(bookingResponses);
    }

    @Override
    public ResponseEntity<?> getAllBookingsByDate() {
        return null;
    }

    @Override
    public ResponseEntity<?> getAvailabilityDayPartCheck(AvailabilityRequest availabilityRequest) {
        return null;
    }
//        return createBookingResponse(bookings);
//    }

    public ResponseEntity<ResponseEntity<BookingResponse>> getAllBookingsByUser(String username) {
        List<Booking> bookings = bookingRepository.findAllBookingsByUser(userRepository.findByUsername(username));
        return ResponseEntity.ok(createBookingResponse(bookings));
    }

    @Override
    public ResponseEntity<BookingResponse> getBookingById(long bookingId) {
        if (bookingRepository.existsById(bookingId)) {
            Booking booking = bookingRepository.findByBookingId(bookingId);
            BookingResponse bookingResponse = new BookingResponse(
                    booking.getUser(),
                    booking.getBookingType(),
                    booking.getBookingDate().toString(),
                    booking.getDayPart());
//                    booking.getTimeTable().name());
            return ResponseEntity.ok(bookingResponse);
//            return bookingRepository.findById(bookingId).orElse(null);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public long saveAvailableDayPart(long userId, AvailabilityRequest availabilityRequest){
        DayPart dayPart = dayPartRepository.findByName(availabilityRequest.getDayPart().getName());
        Booking booking = new Booking(availabilityRequest.getBookingDate(), dayPart);

        if (userRepository.existsById(userId)){
            User user = userRepository.findById(userId).orElse(null);
            if (!bookingRepository.existsByUser_IdAndBookingDate(userId, booking.getBookingDate())){

                Set<Booking> bookings = user.getBookings();
                bookings.add(booking);
                user.setBookings(bookings);
                booking.setUser(user);
                bookingRepository.save(booking);
                return userId;
            } else {
                throw new DatabaseErrorException();
            }
        } else {
            throw new RecordNotFoundException();
        }
    }

    public EDayPart getDayPartOfBooking(long bookingId){
        if (bookingRepository.existsById(bookingId)){
            Booking booking = bookingRepository.findByBookingId(bookingId);
            DayPart dayPart = booking.getDayPart();
            booking.setDayPart(dayPart);
        } return null;
    }
    public void updateDayPartOfBooking(long bookingId, EDayPart eDayPart){
        if (bookingRepository.existsById(bookingId)) {
            Booking booking = bookingRepository.findByBookingId(bookingId);
            DayPart dayPart = dayPartRepository.findByName(eDayPart);
            booking.setDayPart(dayPart);
            bookingRepository.save(booking);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public void addSpaceTypeToBooking(long bookingId, long spaceTypeId, BookingType bookingType){
        if (bookingRepository.existsById(bookingId) && spaceTypeRepository.existsById(spaceTypeId) && bookingTypeRepository.existsById(bookingType.getBookingtypeId())){
            Booking booking = bookingRepository.findByBookingId(bookingId);
            if (booking.getBookingType().getName()== EBookingType.SPACE){
                SpaceType spaceType = spaceTypeRepository.findByName(ESpaceType.WORK_SPOT);
            }
        }
    }
////        BookingResponse bookingResponse = new BookingResponse();
////        bookingResponse.setBookingDate(availabilityRequest.getBookingDate().toString());
////        bookingResponse.setDayPart(availabilityRequest.getDayPart().toString());
////        bookingResponse.setTimeTable(availabilityRequest.getTimeTable().toString());
//        List<Booking> bookings = bookingRepository.findAll();
//        BookingResponse bookingResponse = checkIfDayPartAvailable(bookings, availabilityRequest.getBookingDate());
//        return ResponseEntity.ok(bookingResponse);
//    }



//    public BookingResponse checkIfDayPartAvailable(List<Booking> bookings, LocalDate bookingDate) {
//
//        BookingResponse availableResponse = new BookingResponse(
//                true,
//                true,
//                true
//        );
//        for (int i = 0; i < bookings.size(); i++){
//            if (bookings.get(i).getBookingDate().isEqual(bookingDate)){
//                EDayPart momentOfDay = bookings.get(i).getName();
//
//                switch (momentOfDay){
//                    case MORNING:
//                        availableResponse.setMorning(false);
//                        break;
//                    case AFTERNOON:
//                        availableResponse.setAfternoon(false);
//                        break;
//                    case WHOLE_DAY:
//                        availableResponse.setWholeDay(false);
//                        break;
//                }
//            }
//        } return availableResponse;
//    }

//    public ResponseEntity<MessageResponse> createBookingByDate(@Valid BookingRequest bookingRequest) {
//        List<Booking> bookings = bookingRepository.findAll();
//        for (int i = 0; i < bookings.size(); i++) {
//            if (bookingRequest.getBookingDate().isEqual(bookings.get(i).getBookingDate())) {
//                if (0 == bookingRequest.getDayPart().compareTo(bookings.get(i).getName())) {
//                    throw new ResponseStatusException(HttpStatus.CONFLICT, "No Bookings available.");
//                }
//            }
//        }
////        User username = userService.getUserByUsername().get();
////            List<Booking> bookingsOfUser = (List<Booking>) getUserBookings(token);
////        String username = userService.getUserById(bookingRequest.getUserId()).getId();
//        Booking booking = new Booking(
//                bookingRequest.getUserId(),
//                bookingRequest.getBookingDate().toString(),
//                bookingRequest.getDayPart().toString()
//
//        );
//        BookingType bookingType = bookingTypeRepository.findByName(bookingRequest.getBookingType().getName());
//        booking.setBookingType(bookingType);
//        bookingRepository.save(booking);
//
//        return ResponseEntity.ok(new MessageResponse("Booking was successful!"));
//
//    }

    @Override
    public ResponseEntity<MessageResponse> createBookingByBookingType(BookingRequest bookingRequest) {
        return null;
    }
//    public String bookingTypeOption(BookingType bookingtype) {
//        String typeOfBooking = null;
//        switch (bookingtype.getName()) {
//            case SPACE:
//                typeOfBooking = "Booking a space";
//                break;
//            case BOX:
//                typeOfBooking = "Booking a box";
//                break;
//        } return typeOfBooking;
//    }
//    public ResponseEntity<MessageResponse> createBookingByBookingType(@Valid BookingRequest bookingRequest){
//        BookingType bookingTypeName = bookingTypeRepository.findByName(bookingRequest.getBookingType().getName());
//        BookingType bookingtype = new BookingType(
//                bookingRequest.getBookingType().getName(),
//                bookingTypeName
//        );
////        bookingtype.setName(EBookingType.valueOf(bookingTypeOption(bookingTypeName)));
//        bookingTypeRepository.save(bookingtype);
//        return ResponseEntity.ok(new MessageResponse("Type of booking is now set!")
//        );
//    }

    @Override
    public ResponseEntity<MessageResponse> createBookingBySpaceType(SpaceRequest spaceRequest) {
        return null;
    }

    @Override
    public ResponseEntity<MessageResponse> createBookingByBoxType(BoxRequest boxRequest) {
        return null;
    }
//    public ResponseEntity<MessageResponse> createBookingBySpaceType(SpaceRequest spaceRequest){
////    if(Boolean.TRUE.equals(spaceTypeRepository.existsByName(spaceRequest.getSpaceType().name())));
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
//
//    return ResponseEntity.ok(new MessageResponse("Successfully booked a space!"));
//    }

//    public ResponseEntity<MessageResponse> createBookingByBoxType(BoxRequest boxRequest){
//
//    }

    public Optional<User> getUserByBookingId(long bookingId){
        if(bookingRepository.existsById(bookingId)){
            Booking booking = bookingRepository.findByBookingId(bookingId);
            return userRepository.findById(booking.getUser().getId());
        } else {
            throw new RecordNotFoundException();
        }
    }
//
//    public ResponseEntity<?> getUserBookings(String token){
//        User userBooking = (User) userService.findUserByToken(token).getBody();
//        List<Booking> bookings = new ArrayList<>();
//        return (ResponseEntity<?>) bookings;
//    }

    @Override
    public long createBooking(Booking booking) {
        Booking newBooking = bookingRepository.save(booking);
        return newBooking.getBookingId();
    }

    public ResponseEntity<BookingResponse> createBookingResponse(List<Booking> bookings) {
        List<BookingResponse> bookingResponses = new ArrayList<>();

        for (int i = 0; i < bookings.size(); i++) {
            BookingResponse bookingResponse = new BookingResponse(
                    bookings.get(i).getUser(),
                    bookings.get(i).getBookingId(),
                    bookings.get(i).getBookingType().getName(),
                    bookings.get(i).getBookingDate().toString(),
                    bookings.get(i).getDayPart().toString()
//                    bookings.get(i).getTimeTable().name()
            );
            bookingResponses.add(bookingResponse);
        }
        BookingResponse allBookings = new BookingResponse(
                bookingResponses
        );
        return ResponseEntity.ok(allBookings);
    }


//    @Override
//    public ResponseEntity<?> updateBookingById(long bookingId, BookingRequest bookingRequest) {
//        return null;
//    }

    @Override
    public BookingRequest updateBooking(long bookingId, BookingRequest bookingUpdate) {
        if (bookingRepository.existsById(bookingId)) {
            try {
                Booking existBooking = bookingRepository.findByBookingId(bookingId);
//                        .orElse(null);
//                        .get();
                existBooking.setBookingType(bookingUpdate.getBookingType());
                existBooking.setBookingDate(bookingUpdate.getBookingDate());
                existBooking.setDayPart(bookingUpdate.getDayPart());
//                existBooking.setTimeTable(bookingUpdate.getTimeTable());
                existBooking.setUser(bookingUpdate.getUserId());
                bookingRepository.save(existBooking);
            } catch (Exception exception) {
                throw new DatabaseErrorException();
            }
        } else {
            throw new RecordNotFoundException();
        }
        return bookingUpdate;
    }

    public void saveBookingTypeToBooking(Booking booking, BookingType bookingType) {
        booking.addBookingType(bookingType);
        bookingType.setBooking(booking);
        bookingTypeRepository.save(bookingType);
    }

    @Override
    public ResponseEntity<?> deleteBooking(String token, long bookingId) {
        if (bookingRepository.existsById(bookingId)) {
//            Booking booking = bookingRepository.findByBookingId(bookingId);
            User userBooking = (User) userService.findUserByToken(token).getBody();
//            List<Booking> bookingsOfUser = (List<Booking>) getUserBookings(token);
            String username = userBooking.getUsername();

            if (bookingRepository.findByBookingId(bookingId).getUser().getId()
                    == userRepository.findByUsername(username).get().getId()) {
                return bookingRepository.deleteByBookingId(bookingId);
            } else throw new RecordNotFoundException();
        } return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @Override
    public ResponseEntity<Object> createBookingByDayPart(AvailabilityRequest availabilityRequest) {
        return null;
    }

    @Override
    public ResponseEntity<MessageResponse> createBookingByDate(BookingRequest bookingRequest) {
        return null;
    }


}

//    @Override
//    public long saveBooking(Booking booking) {
//        Booking newBooking = bookingRepository.save(booking);
//        return newBooking.getBookingId();
//    }