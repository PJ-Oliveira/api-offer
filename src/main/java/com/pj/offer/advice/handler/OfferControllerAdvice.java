package com.pj.offer.advice.handler;

import javax.servlet.http.HttpServletRequest;
import com.pj.offer.advice.exception.MessageError;
import com.pj.offer.advice.exception.NotFoundException;
import com.pj.offer.advice.exception.ErrorAtributes;
import com.pj.offer.advice.exception.OfferException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class OfferControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorAtributes> notFoundException(NotFoundException notFoundException, HttpServletRequest httpServletRequest){
        ErrorAtributes errorAtributes = new ErrorAtributes("Exception: Offer Not Found", notFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorAtributes);
    }

    @ExceptionHandler(OfferException.class)
    public ResponseEntity<ErrorAtributes> offerException(OfferException offerException, HttpServletRequest httpServletRequest){
        ErrorAtributes errorAtributes = new ErrorAtributes("Date Validation: Please, verify the Dates atributes", offerException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorAtributes);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException,
                                                                  HttpHeaders httpHeaders, HttpStatus httpStatus,
                                                                  WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        for(ObjectError objectError : methodArgumentNotValidException.getAllErrors()){
            details.add(objectError.getDefaultMessage());
            }
        MessageError messageError = new MessageError("Validation Error", details);
        return new ResponseEntity<>(messageError, HttpStatus.BAD_REQUEST);
    }
}
