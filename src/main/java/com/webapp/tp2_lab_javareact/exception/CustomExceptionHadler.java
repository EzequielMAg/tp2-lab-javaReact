package com.webapp.tp2_lab_javareact.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHadler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        final int statusCode = status.value();
        Map<String, Object> responseBody = new LinkedHashMap<>();

        responseBody.put("Status Code", statusCode + " (" + HttpStatus.valueOf(statusCode).getReasonPhrase() + ")");

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        responseBody.put("Message", errors);

        return new ResponseEntity<>(responseBody, headers, status);
    }

    @ExceptionHandler({BussinessException.class})
    public ResponseEntity<Object> handleBussinessException (
            BussinessException ex, WebRequest request
    ) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        //responseBody.put("timestamp", new Date());
        responseBody.put("Status Code", ex.getHttStatus().value() + " (" + ex.getHttStatus().getReasonPhrase() + ")");
        responseBody.put("message", ex.getMessage());

        return new ResponseEntity<>(responseBody, ex.getHttStatus());
    }
}
