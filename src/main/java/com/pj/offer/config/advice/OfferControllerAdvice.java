package com.pj.offer.config.advice;

import com.pj.offer.config.advice.exception.ExceptionResponse;
import com.pj.offer.config.advice.exception.OfferException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class OfferControllerAdvice
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler
            (value = { IllegalArgumentException.class, ExceptionResponse.class, OfferException.class, InvocationTargetException.class})
    protected ResponseEntity<Object> handleConflict(
            OfferException offerException, WebRequest request) {
        return handleExceptionInternal(offerException, "Inexistent or expired Id",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}