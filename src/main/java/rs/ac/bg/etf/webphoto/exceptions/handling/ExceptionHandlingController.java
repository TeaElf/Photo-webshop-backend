package rs.ac.bg.etf.webphoto.exceptions.handling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rs.ac.bg.etf.webphoto.exceptions.specifications.ResourceNotFoundException;
import rs.ac.bg.etf.webphoto.exceptions.specifications.SafeModeException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlingController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler(SafeModeException.class)
    public ResponseEntity<ExceptionResponse> safeModeException(SafeModeException ex) {
        return new ResponseEntity<>(prepareResponse(HttpStatus.CONFLICT, ex.getMessage(), ex.getLocalizedMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(prepareResponse(HttpStatus.NOT_FOUND, ex.getMessage(), ex.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

    private ExceptionResponse prepareResponse(HttpStatus status, String message, String debugMessage) {
        ExceptionResponse response = ExceptionResponse.builder()
                .status(status)
                .message(message)
                .debugMessage(debugMessage)
                .timestamp(LocalDateTime.now())
                .build();
        logger.info("Exception: {}", response.toString());
        return response;
    }

}
