package com.wdc.web.wdc.exceptions;
import org.springframework.http.HttpStatus;

public class UserNotFound {
    private final String message;
    private final HttpStatus status;

    public UserNotFound(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}