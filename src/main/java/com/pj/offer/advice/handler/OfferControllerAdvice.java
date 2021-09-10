package com.pj.offer.advice.handler;

import javax.servlet.http.HttpServletRequest;

import com.pj.offer.advice.exception.OfferException;
import com.pj.offer.advice.exception.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class OfferControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OfferException.class)
    public ResponseEntity<StandardError> resourceNotFound(OfferException offerException, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(offerException.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }


}
