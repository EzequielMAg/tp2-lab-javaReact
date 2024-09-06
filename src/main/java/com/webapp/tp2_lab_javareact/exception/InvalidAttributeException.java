package com.webapp.tp2_lab_javareact.exception;

import org.springframework.http.HttpStatus;

public class InvalidAttributeException extends BusinessException {

    public InvalidAttributeException(String message) {
        super(message);
        super.setHttStatus(HttpStatus.BAD_REQUEST);
    }
}
