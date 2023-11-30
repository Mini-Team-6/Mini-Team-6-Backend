package ybe.mini.travelserver.global.exception;


import org.springframework.http.HttpStatus;

public interface ErrorMessage {
    HttpStatus getStatus();

    String getMessage();
}