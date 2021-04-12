package rs.ac.bg.etf.webphoto.exceptions.specifications;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class SafeModeException extends RuntimeException {

    public SafeModeException() {
        super("Action isn't safe!");
    }

    public SafeModeException(String message) {
        super(message);
    }

}
