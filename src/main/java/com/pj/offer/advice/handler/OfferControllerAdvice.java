package com.pj.offer.advice.handler;

import javax.servlet.http.HttpServletRequest;
import com.pj.offer.advice.exception.NotFoundException;
import com.pj.offer.advice.exception.ErrorAtributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class OfferControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorAtributes> notFoundException(NotFoundException notFoundException, HttpServletRequest request){
        String description = "Warning: NotFound. Ensure that the Id included in the request exist, is active or is unexpired";
        //HttpStatus code = HttpStatus.NOT_FOUND;
        ErrorAtributes errorAtributes = new ErrorAtributes(description, notFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorAtributes);
    }
}
