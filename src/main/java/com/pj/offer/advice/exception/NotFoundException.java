package com.pj.offer.advice.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 8857758950933747662L;

    public NotFoundException(String message) {
        super(message);
    }
}
