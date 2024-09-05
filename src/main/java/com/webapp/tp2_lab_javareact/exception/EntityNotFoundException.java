package com.webapp.tp2_lab_javareact.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BussinessException {

    public EntityNotFoundException(String message) {
        super(message);
        super.setHttStatus(HttpStatus.NOT_FOUND);
    }
}
