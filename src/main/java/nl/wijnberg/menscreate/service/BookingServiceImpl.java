package nl.wijnberg.menscreate.service;

import nl.wijnberg.menscreate.domain.*;

import nl.wijnberg.menscreate.domain.enums.ERole;
import nl.wijnberg.menscreate.exceptions.RecordNotFoundException;
import nl.wijnberg.menscreate.payload.request.BookingRequest;
import nl.wijnberg.menscreate.payload.response.BookingResponse;
import nl.wijnberg.menscreate.payload.response.MessageResponse;
import nl.wijnberg.menscreate.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

//    private static final String NOT_FOUND_ERROR = "Error: Request is not found.";

    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserService userService;

    @Autowired
    public void setBookingRepository(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // todo: works
    // get all bookings (for admin)
    @Override
    public ResponseEntity<?> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingResponse> bookingResponses = createBookingResponse(bookings);
        if (bookings.isEmpty()) {
            return ResponseEntity.badRequest().body(new RecordNotFoundException());
        }
        return ResponseEntity.ok(bookingResponses);
    }

    //todo: works
    // get user bookings by token
    @Override
    public ResponseEntity<?> getUserBookings(String token) {
        List<Booking> bookings = findUserBookings(token);
        return ResponseEntity.ok(createBookingResponse(bookings));
    }

    //todo: works
    private List<Booking> findUserBookings(String token) {
//        User userBooking = userService.findUserByToken(token);
//        User userBooking = (User) userService.findUserByToken(token).getBody();
        User userBooking = (User) userService.findUserByToken(token);
        List<Booking> bookings = new ArrayList<>();
        bookings.addAll(userBooking.getBookings());
        return (bookings);
    }

    // find all user bookings by username //todo: make this work
    private List <Booking> findUserBookingsByUsername(String username) {
        User userBooking = userService.getUserByUsername(username).get();
        List<Booking> bookings = new ArrayList<>();
        bookings.addAll(userBooking.getBookings());
        return (bookings);
    }

    //todo: works
    // get booking by booking id
    @Override
    public ResponseEntity<BookingResponse> getBookingById(long bookingId) {
        if (bookingRepository.existsById(bookingId)) {
            Booking booking = bookingRepository.findByBookingId(bookingId);
            BookingResponse bookingResponse = new BookingResponse(
                    booking.getUser().getId(),
                    booking.getBookingId(),
                    booking.getBoxName(),
                    booking.getBookingDate().toString());

            return ResponseEntity.ok(bookingResponse);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There was no booking found with this id " + bookingId + ".");
        }
    }

    //todo: works
    // get booking by booking id with user authentication token
    @Override
    public ResponseEntity<BookingResponse> getUserBookingByBookingId(String token, long bookingId) {

        if (bookingRepository.existsById(bookingId)) {
            Booking booking = bookingRepository.findByBookingId(bookingId);
//            User ownerBooking = userService.findUserByToken(token);
//             User ownerBooking = (User) userService.findUserByToken(token).getBody();
            User ownerBooking = (User) userService.findUserByToken(token);
            if(booking.getUser().getId()
                    == ownerBooking.getId()){
                return getBookingById(bookingId);
            } else {
                throw new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED);
            }
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "There was no booking found with this id " + bookingId + ".");
        }
    }

    //todo: works
    // create new booking
    @Override
    public long createBooking(String token, Booking booking) {
//        User requestUser = userService.findUserByToken(token);
//        User requestUser = (User) userService.findUserByToken(token).getBody();
        User requestUser = (User) userService.findUserByToken(token);
//        long id = requestUser.getId();
        booking.setUser(requestUser);
        Booking newBooking = bookingRepository.save(booking);
        return newBooking.getBookingId();
    }

    //todo: works
    // create a booking response list
    public List <BookingResponse> createBookingResponse(List<Booking> bookings) {
        List<BookingResponse> bookingResponses = new ArrayList<>();

        for (int i = 0; i < bookings.size(); i++) {
            BookingResponse bookingResponse = new BookingResponse(
                    bookings.get(i).getUser().getId(),
                    bookings.get(i).getBookingId(),
                    bookings.get(i).getBoxName(),
                    bookings.get(i).getBookingDate().toString()
            );
            bookingResponses.add(bookingResponse);
        }
//        BookingResponse allBookings = new BookingResponse(
//                bookingResponses
//        );
        return bookingResponses;
    }


        // todo: works
        // update booking by booking id
        @Override
        public Booking updateBooking(long bookingId, BookingRequest bookingRequest, String authorization) {
            // todo: investigate the user info Json array,
            //  it has to do with getting entire User object, that keeps looping.
            if (bookingRepository.existsById(bookingId)) {
//                    User userBookingUpdate = userService.findUserByToken(authorization);
//                User userBookingUpdate = (User) userService.findUserByToken(authorization).getBody();
                User userBookingUpdate = (User) userService.findUserByToken(authorization);
                    Booking existBooking = bookingRepository.findByBookingId(bookingId);

                    if(existBooking.getUser().getId() == userBookingUpdate.getId()) {

                        existBooking.setBookingDate(bookingRequest.getBookingDate());
                        existBooking.setBoxName(bookingRequest.getBoxName());

                        bookingRepository.save(existBooking);
                        return existBooking;
                    } else {
                        throw new ResponseStatusException(
                                HttpStatus.FORBIDDEN, "This booking with id " + bookingId + "does not belong to you.");
                    }
            } else {
                throw new RecordNotFoundException();
            }

        }

        //todo: works
    // delete booking
    @Override
    public ResponseEntity<?> deleteBooking(String token, long bookingId) {
        if (bookingRepository.existsById(bookingId)){
        Booking cancelBooking = bookingRepository.findByBookingId(bookingId);
//            User userBooking = userService.findUserByToken(token);
//            User userBooking = (User) userService.findUserByToken(token).getBody();
            User userBooking = (User) userService.findUserByToken(token);

            Boolean isAdmin = userBooking.getRoles().stream().anyMatch(role -> role.getName() == ERole.ROLE_ADMIN);

            if (cancelBooking.getUser().getId() == userBooking.getId() || isAdmin) {
                bookingRepository.delete(cancelBooking);
                return ResponseEntity.ok(new MessageResponse("Booking with id number: " + bookingId + " was deleted with success"));
            } else {
                throw new ResponseStatusException(
                        HttpStatus.FORBIDDEN, "This booking with this id " + bookingId + "does not belong to you.");
            }
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "There was no booking found with this id " + bookingId + ".");
        }
    }

//    //todo: works
//    // delete booking
//    @Override
//    public ResponseEntity<?> deleteBooking(String token, long bookingId) {
//
//        Optional<Role> adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
//        User userBooking = (User) userService.findUserByToken(token).getBody();
//        List<Booking> bookingsUser = findUserBookings(token);
//        List<Long> bookingList = new ArrayList<>();
//        for (Booking b : bookingsUser) {
//            bookingList.add(b.getBookingId());
//        }
//        if (bookingRepository.existsById(bookingId)) {
//            if (bookingList.contains(bookingId) || userBooking.getRoles().contains(adminRole)) {
//                Booking cancelBooking = bookingRepository.findByBookingId(bookingId);
//                bookingRepository.deleteByBookingId(cancelBooking.getBookingId());
//                return ResponseEntity.ok(new MessageResponse("Booking with id number: " + bookingId + " was deleted with success"));
//            } else {
//                throw new ResponseStatusException(
//                        HttpStatus.FORBIDDEN, "This booking with this id " + bookingId + "does not belong to you.");
//            }
//        }   else{
//                throw new ResponseStatusException(
//                        HttpStatus.NOT_FOUND, "There was no booking found with this id " + bookingId + ".");
//            }
//
//    }

//    cancelBooking.getUser().getId();
    //== userBooking.getId()
    //cancelBooking.getUser().getRoles().contains(userBooking)
}

//    //todo: works
//    // delete booking
//    @Override
//    public ResponseEntity<?> deleteBooking(String token, long bookingId) {
//        if (bookingRepository.existsById(bookingId)){
//        Booking cancelBooking = bookingRepository.findByBookingId(bookingId);
//            User userBooking = (User) userService.findUserByToken(token).getBody();
//
//            if (cancelBooking.getUser().getId() == userBooking.getId()) {
//                bookingRepository.delete(cancelBooking);
//                return ResponseEntity.ok(new MessageResponse("Booking with id number: " + bookingId + " was deleted with success"));
//            } else {
//                throw new ResponseStatusException(
//                        HttpStatus.FORBIDDEN, "This booking with this id " + bookingId + "does not belong to you.");
//            }
//        } else {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "There was no booking found with this id " + bookingId + ".");
//        }
//    }
//}

//    // todo: works
//    // update booking by booking id
//    @Override
//    public Booking updateBooking(long bookingId, BookingRequest bookingRequest, String authorization) {
//        // todo: investigate the user info Json array, perhaps check the createbookingresponse list by findallbookingsbyusername
//        if (bookingRepository.existsById(bookingId)) {
//                User userBookingUpdate = (User) userService.findUserByToken(authorization).getBody();
//                Booking existBooking = bookingRepository.findByBookingId(bookingId);
//
//                if(bookingRepository.findByBookingId(bookingId).getUser().getId()
//                        == userService.findUserByToken(authorization).getBody()) {
//
//                    existBooking.setBookingDate(bookingRequest.getBookingDate());
//                    existBooking.setBoxName(bookingRequest.getBoxName());
//
//                    bookingRepository.save(existBooking);
//                    return existBooking;
//                } else {
//                    throw new ResponseStatusException(
//                            HttpStatus.FORBIDDEN, "This booking with id " + bookingId + "does not belong to you.");
//                }
//        } else {
//            throw new RecordNotFoundException();
//        }
//
//    }


//    // create booking response list
//    public ResponseEntity<List<BookingResponse>> createBookingResponse(List<Booking> bookings) {
//        List<BookingResponse> bookingResponses = new ArrayList<>();
//
//        for (int i = 0; i < bookings.size(); i++) {
//            BookingResponse bookingResponse = new BookingResponse(
//                    bookings.get(i).getUser(),
//                    bookings.get(i).getBookingId(),
//                    bookings.get(i).getBoxName(),
//                    bookings.get(i).getBookingDate().toString()
//            );
//            bookingResponses.add(bookingResponse);
//        }
////        BookingResponse allBookings = new BookingResponse(
////                bookingResponses
////        );
//        return ResponseEntity.ok(bookingResponses);
//    }

//    // get all user bookings by username //todo: make this work
//    public List <BookingResponse> getAllBookingsByUsername(String username) {
//        List<Booking> bookings = bookingRepository.findAllBookingsByUser(userRepository.findByUsername(username));
//        return createBookingResponse(bookings);
//    }
//    // get all user bookings by username //todo: make this work
//    public ResponseEntity<ResponseEntity<List<BookingResponse>>> getAllBookingsByUsername(String username) {
//        List<Booking> bookings = bookingRepository.findAllBookingsByUser(userRepository.findByUsername(username));
//        return ResponseEntity.ok(createBookingResponse(bookings));
//    }


//        List<Booking> userBookings = findUserBookings(token);
//                        existBooking.setUser(bookingRequest.getUserId()));
//    if (bookingRepository.existsById(bookingRequest.getBookingId())){
//            } else  {
//                throw new DatabaseErrorException();


//
//
//
//    BookingResponse bookingResponse = new BookingResponse(
//            booking.getUser(),
//            booking.getBookingId(),
//            booking.getBoxName(),
//            booking.getBookingDate().toString());
//
//            return bookingResponse;

//     get user by booking id
//    public ResponseEntity<BookingResponse> getUserByBookingId(String token, long bookingId) {
//        User owner = (User) userService.findUserByToken(token).getBody();
//        String username = owner.getUsername();
//
//        if (bookingRepository.existsById(bookingId)) {
//            if (bookingRepository.findByBookingId(bookingId).getUser().getId()
//                    == userRepository.findByUsername(username).get().getId()) {
////                return getBookingById(bookingId);
//            } else {
//                throw new ResponseStatusException(
//                        HttpStatus.UNAUTHORIZED);
////            Booking booking = ;
////            return findById(booking.);
//            }
//        }   else {
//            throw new RecordNotFoundException();
//        }
//    }

////    @Override
//    public ResponseEntity<BookingResponse> getUserByBookingId(String token, long bookingId) {
//        if (bookingRepository.existsById(bookingId)) {
//        User ownerBooking = (User) userService.findUserByToken(token).getBody();
//        Booking existBooking = bookingRepository.findByBookingId(bookingId);
//
//        if (existBooking.getUser().getId() == ownerBooking.getId()) {
//
//            existBooking.setBookingDate(getBookingDate());
//            existBooking.setBoxName(bookingRequest.getBoxName());
//
//            bookingRepository.save(existBooking);
//            return existBooking;
//        }
//
//    }
//    // get user by booking id
//    public Optional<User> getUserByBookingId(long bookingId) {
//        if (bookingRepository.existsById(bookingId)) {
//            Booking booking = bookingRepository.findByBookingId(bookingId);
//            return userRepository.findById(booking.getUser().getId());
//        } else {
//            throw new RecordNotFoundException();
//        }
//    }




// update booking by booking id
//    @Override
//    public ResponseEntity<?> updateByBookingId(String token, long bookingId) {
//        if (bookingRepository.existsById(bookingId)) {
//            Booking booking = bookingRepository.findByBookingId(bookingId);
//            User userBookingUpdate = (User) userService.findUserByToken(token).getBody();
//            if (booking.getUser().getId() == userBookingUpdate.getId()) {
//                Booking bookingUpdate = bookingRepository.findByBookingId(bookingId);
//                bookingUpdate.setBookingDate(booking.getBookingDate());
//                bookingUpdate.setBoxName(booking.getBoxName());
//                bookingRepository.save(bookingUpdate);
//                return ResponseEntity.ok(bookingUpdate);
//            } else {
//                throw new DatabaseErrorException();
//            }
//        } else {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "There was no booking found with this id " + bookingId + ".");
//        }
//    }


//        public ResponseEntity<?> findUserBookings(String token){
//        User userBooking = (User) userService.findUserByToken(token).getBody();
//        List<Booking> bookings = new ArrayList<>();
//        return (ResponseEntity<?>) bookings;
//    }


//            String username = userBooking.getUsername();
//         if (bookingRepository.findByBookingId(bookingId).getUser().getId()
//                == userRepository.findByUsername(username).get().getId()) {
//             cancelBooking.setBookingId(bookingId);


//            List<Booking> bookingsUser = findUserBookings(token);
//             bookingsUser.contains(bookingId);
//            List<Long> listOfBookingId = new ArrayList<>();
//            for (Booking b : bookingsUser) {
//                listOfBookingId.add(b.getBookingId());
//            }

//            if (
//                    bookingRepository.findByBookingId(bookingId).getUser().getId()
//                    == userRepository.findByUsername(username).get().getId()) {
//                    == userService.findUserByToken(token).getBody()) {
//                    == userRepository.findByUsername(username).get().getId()) {
//                booking.setBookingId(bookingId);}
//                listOfBookingId.contains(bookingId);
//                bookingRepository.delete(cancelBooking);
//                return bookingRepository.deleteByBookingId(cancelBooking.getBookingId());

// @Override
//    public ResponseEntity<?> deleteBooking(String token, long bookingId) {
//
//        Booking cancelBooking = bookingRepository.findByBookingId(bookingId);
//            User userBooking = (User) userService.findUserByToken(token).getBody();
//            List<Booking> bookingsUser = findUserBookings(token);
////            String username = userBooking.getUsername();
//
//            List<Long> listOfBookingId = new ArrayList<>();
//            for (Booking b : bookingsUser) {
//                listOfBookingId.add(b.getBookingId());
//            }
//
//        if (bookingRepository.existsById(bookingId) ?
//                listOfBookingId.contains(bookingId) :
//                userRepository.existsById(userBooking.getUsername())) {
//
////            if (
////                    bookingRepository.findByBookingId(bookingId).getUser().getId()
////                    == userRepository.findByUsername(username).get().getId()) {
////                    == userService.findUserByToken(token).getBody()) {
////                    == userRepository.findByUsername(username).get().getId()) {
////                booking.setBookingId(bookingId);}
////                listOfBookingId.contains(bookingId);
////                bookingRepository.delete(cancelBooking);
////                return bookingRepository.deleteByBookingId(cancelBooking.getBookingId());
//
//            bookingRepository.delete(cancelBooking);
//            return ResponseEntity.ok(new MessageResponse("Booking with id number: " + bookingId + " was deleted with success"));
//
//        } else {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "There was no booking found with this id " + bookingId + ".");
//        }
//    }



//    @Override
//    public ResponseEntity<?> deleteBooking(String token, long bookingId) {
//        if (bookingRepository.existsById(bookingId)) {
//            Booking cancelBooking = bookingRepository.findByBookingId(bookingId);
////            User userBooking = (User) userService.findUserByToken(token).getBody();
//            List<Booking> bookingsUser = findUserBookings(token);
////            String username = userBooking.getUsername();
//
//            List<Long> listOfBookingId = new ArrayList<>();
//            for (Booking b : bookingsUser) {
//                listOfBookingId.add(b.getBookingId());
//            }
//
//            if (bookingRepository.findByBookingId(bookingId).getUser().getId()
//                    == userService.findUserByToken(token).getBody()) {
////                    == userRepository.findByUsername(username).get().getId()) {
////                booking.setBookingId(bookingId);}
//                listOfBookingId.contains(bookingId);
//                return bookingRepository.deleteByBookingId(cancelBooking.getBookingId());
//            }
//            return ResponseEntity.ok(new MessageResponse("Booking with id number: " + bookingId + " was deleted with success"));
//
//        } else {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "There was no booking found with this id " + bookingId + ".");
//        }
//    }

//    @Override
//    public ResponseEntity<?> getAllBookingsByDate() {
//        return null;
//    }


//
//    @Override
//    public ResponseEntity<MessageResponse> createBookingByDate(BookingRequest bookingRequest) {
//        return null;
//    }





//    // save day part to booking
//    public long saveAvailableDayPart(long userId, AvailabilityRequest availabilityRequest){
//        DayPart dayPart = dayPartRepository.findByName(availabilityRequest.getDayPart().getName());
//        Booking booking = new Booking(availabilityRequest.getBookingDate(), dayPart);
//
//        if (userRepository.existsById(userId)){
//            User user = userRepository.findById(userId).orElse(null);
//            if (!bookingRepository.existsByUser_IdAndBookingDate(userId, booking.getBookingDate())){
//
////                Set<Booking> bookings = user.getBookings();
////                bookings.add(booking);
////                user.setBookings(bookings);
//                booking.setUser(user);
//                bookingRepository.save(booking);
//                return userId;
//            } else {
//                throw new DatabaseErrorException();
//            }
//        } else {
//            throw new RecordNotFoundException();
//        }
//    }

// get day part of booking
//    public EDayPart getDayPartOfBooking(long bookingId){
//        if (bookingRepository.existsById(bookingId)){
//            Booking booking = bookingRepository.findByBookingId(bookingId);
////            DayPart dayPart = booking.getDayPart();
////            booking.setDayPart(dayPart);
//        } return null;
////    }
//
//    // update day part of booking
//    public void updateDayPartOfBooking(long bookingId, EDayPart eDayPart){
//        if (bookingRepository.existsById(bookingId)) {
//            Booking booking = bookingRepository.findByBookingId(bookingId);
//            DayPart dayPart = dayPartRepository.findByName(eDayPart);
////            booking.setDayPart(dayPart);
//            bookingRepository.save(booking);
//        } else {
//            throw new RecordNotFoundException();
//        }
//    }



//
//    @Override
//    public ResponseEntity<?> getAvailabilityDayPartCheck(AvailabilityRequest availabilityRequest) {
//        return null;
//    }
////        return createBookingResponse(bookings);
////    }
//
//    @Override
//    public ResponseEntity<Object> createBookingByDayPart(AvailabilityRequest availabilityRequest) {
//        return null;
//    }



//    public void addSpaceTypeToBooking(long bookingId, long spaceTypeId, BookingType bookingType){
//        if (bookingRepository.existsById(bookingId) && spaceTypeRepository.existsById(spaceTypeId) && bookingTypeRepository.existsById(bookingType.getBookingtypeId())){
//            Booking booking = bookingRepository.findByBookingId(bookingId);
//            if (booking.getBookingType().getName()== EBookingType.SPACE){
//                SpaceType spaceType = spaceTypeRepository.findByName(ESpaceType.WORK_SPOT);
//            }
//        }
//    }
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

//    @Override
//    public ResponseEntity<MessageResponse> createBookingByBookingType(BookingRequest bookingRequest) {
//        return null;
//    }
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

//    @Override
//    public ResponseEntity<MessageResponse> createBookingBySpaceType(SpaceRequest spaceRequest) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<MessageResponse> createBookingByBoxType(BoxRequest boxRequest) {
//        return null;
//    }
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

//





//    @Override
//    public ResponseEntity<?> updateBookingById(long bookingId, BookingRequest bookingRequest) {
//        return null;
//    }



//    public void saveBookingTypeToBooking(Booking booking, BookingType bookingType) {
//        booking.addBookingType(bookingType);
//        bookingType.setBooking(booking);
//        bookingTypeRepository.save(bookingType);
//    }



//    @Override
//    public long saveBooking(Booking booking) {
//        Booking newBooking = bookingRepository.save(booking);
//        return newBooking.getBookingId();
//    }