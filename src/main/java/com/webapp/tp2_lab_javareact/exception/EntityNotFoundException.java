package com.webapp.tp2_lab_javareact.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(String message) {
        super(message);
        super.setStatusCode(HttpStatus.NOT_FOUND);
    }
}
