package cat.itacademy.s04.s02.n01.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFruitException extends RuntimeException {
    public InvalidFruitException(String message) {
        super(message);
    }
}