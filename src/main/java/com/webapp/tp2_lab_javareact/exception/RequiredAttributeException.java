package com.webapp.tp2_lab_javareact.exception;

import org.springframework.http.HttpStatus;

public class RequiredAttributeException extends BusinessException {

    public RequiredAttributeException(String message) {
        super(message);
    }
}
