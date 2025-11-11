package cat.itacademy.s04.s02.n01.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FruitNotFoundException extends RuntimeException {
    public FruitNotFoundException() {
        super("Fruit not found.");
    }
}