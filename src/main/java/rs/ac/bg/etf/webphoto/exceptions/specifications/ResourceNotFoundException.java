package rs.ac.bg.etf.webphoto.exceptions.specifications;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Requested resource not found!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
