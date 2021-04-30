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

    // get user bookings by token
    @Override
    public ResponseEntity<?> getUserBookings(String token) {
        List<Booking> bookings = findUserBookings(token);
        return ResponseEntity.ok(createBookingResponse(bookings));
    }

    // find userBookings by token
    private List<Booking> findUserBookings(String token) {
        User userBooking = userService.findUserByToken(token);
        List<Booking> bookings = new ArrayList<>();
        bookings.addAll(userBooking.getBookings());
        return (bookings);
    }

    // find all user bookings by username //todo: make this work or remove?
    private List<Booking> findUserBookingsByUsername(String username) {
        User userBooking = userService.getUserByUsername(username).get();
        List<Booking> bookings = new ArrayList<>();
        bookings.addAll(userBooking.getBookings());
        return (bookings);
    }

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

    // get booking by booking id with user authentication token
    @Override
    public ResponseEntity<BookingResponse> getUserBookingByBookingId(String token, long bookingId) {
        if (bookingRepository.existsById(bookingId)) {
            Booking booking = bookingRepository.findByBookingId(bookingId);
            User ownerBooking = userService.findUserByToken(token);
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

//        if (booking.getUser().getId() == ownerBooking.getId()) {
//            booking.setBookingDate(getBookingDate());
//            booking.setBoxName(bookingRequest.getBoxName());
//            bookingRepository.save(booking);
//            return booking;


    // create new booking
    @Override
    public long createBooking(String token, Booking booking) {
        User requestUser = userService.findUserByToken(token);
        booking.setUser(requestUser);
        Booking newBooking = bookingRepository.save(booking);
        return newBooking.getBookingId();
    }

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
        return bookingResponses;
    }

    // update booking by booking id
    @Override
    public Booking updateBooking(long bookingId, BookingRequest bookingRequest, String authorization) {
        if (bookingRepository.existsById(bookingId)) {
            User userBookingUpdate = userService.findUserByToken(authorization);
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

    // delete booking
    @Override
    public ResponseEntity<?> deleteBooking(String token, long bookingId) {
        if (bookingRepository.existsById(bookingId)){
        Booking cancelBooking = bookingRepository.findByBookingId(bookingId);
            User userBooking = userService.findUserByToken(token);

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

}