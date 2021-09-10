package com.pj.offer.advice.handler;

import javax.servlet.http.HttpServletRequest;

import com.pj.offer.advice.exception.NotFoundException;
import com.pj.offer.advice.exception.InvalidException;
import com.pj.offer.advice.exception.ErrorAtributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class OfferControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidException.class)
    public ResponseEntity<ErrorAtributes> invalidException(InvalidException invalidException, HttpServletRequest request){
        String detail = "Ensure that the Id included in the request is not expired";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorAtributes errorAtributes = new ErrorAtributes(Instant.now(), invalidException.getMessage(), request.getRequestURI(), status.value(), detail);
        return ResponseEntity.status(status).body(errorAtributes);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorAtributes> notFoundException(NotFoundException notFoundException, HttpServletRequest request){
        String detail = "Ensure that the Id included in the request exist";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorAtributes errorAtributes = new ErrorAtributes(Instant.now(), notFoundException.getMessage(), request.getRequestURI(), status.value(), detail);
        return ResponseEntity.status(status).body(errorAtributes);
    }

}
