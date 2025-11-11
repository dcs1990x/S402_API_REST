package cat.itacademy.s04.s02.n01;

import org.springframework.http.ResponseEntity;

public interface Command<I, O> {

    ResponseEntity<O> execute(I input);
}