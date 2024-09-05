package com.webapp.tp2_lab_javareact.exception;

import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.http.HttpStatus;

public class RequiredAttributeException extends BussinessException {

    public RequiredAttributeException(String message) {
        super(message);
        super.setHttStatus(HttpStatus.BAD_REQUEST);
    }
}
