package com.pj.offer.config.advice.exception;

import lombok.*;

import java.time.LocalDate;

@Data
public class ExceptionResponse {

    private String message;
    private LocalDate dateTime;

}
