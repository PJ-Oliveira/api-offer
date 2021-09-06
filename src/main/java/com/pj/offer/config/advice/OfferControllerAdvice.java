package com.pj.offer.config.advice;

import com.pj.offer.config.advice.exception.OfferException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class OfferControllerAdvice
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler
            (value = { IllegalArgumentException.class, IllegalStateException.class, OfferException.class, Exception.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException runtimeException, WebRequest request) {
        return handleExceptionInternal(runtimeException, "Resource invalid or problematic",
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException constraintViolationException){
        List<String> errors = new ArrayList<>(constraintViolationException.getConstraintViolations().size());
        constraintViolationException.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_GATEWAY);
    }
}