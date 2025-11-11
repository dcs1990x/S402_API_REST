package cat.itacademy.s04.s02.n01.model;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private String message;

    public ErrorResponse(String message){
        this.message = message;
    }
}