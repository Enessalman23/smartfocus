package com.enessalman.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SmartFocusException extends RuntimeException {

    public SmartFocusException(String message) {
        super(message);
    }
}
