package com.pj.offer.config.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OfferControllerAdvice
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler
            (value = { IllegalArgumentException.class, IllegalStateException.class, OfferException.class, Exception.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException runtimeException, WebRequest request) {
        String bodyOfResponse = "Controller Advice Exception: ou o Id requerido não existe ou, o Método requerido não é apropriado";
        return handleExceptionInternal(runtimeException, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}