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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class OfferControllerAdvice
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OfferException.class)
    protected ResponseEntity<Object> handleConflict(OfferException offerException, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDate.now());
        response.setMessage("Invalid");
        ResponseEntity<Object> objectResponseEntity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        return objectResponseEntity;
    }

    //qual dos dois melhor se adequaria aqui?

    @ExceptionHandler(OfferException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleOfferException(OfferException offerException)
    {
        return new ResponseEntity<>
                ("invalid due to error: " + offerException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}