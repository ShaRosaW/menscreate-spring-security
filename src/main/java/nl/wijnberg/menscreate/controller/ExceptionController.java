package nl.wijnberg.menscreate.controller;

import nl.wijnberg.menscreate.exceptions.BadRequestException;
import nl.wijnberg.menscreate.exceptions.DatabaseErrorException;
import nl.wijnberg.menscreate.exceptions.RecordNotFoundException;
import nl.wijnberg.menscreate.payload.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class ExceptionController extends RuntimeException {
//        ResponseEntityExceptionHandler {


    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Cannot find specified record."));
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> exception(BadRequestException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Request is not valid."));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<MessageResponse> handleMaxSizeException(MaxUploadSizeExceededException exception){
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse("File is too large!"));
    }


    @ExceptionHandler(value = DatabaseErrorException.class)
    public ResponseEntity<Object> exception(DatabaseErrorException exception) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new MessageResponse("A problem occurs in the database"));
    }

}
