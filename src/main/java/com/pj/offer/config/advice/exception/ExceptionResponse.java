package com.pj.offer.config.advice.exception;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExceptionResponse extends Exception {

    private String message;
    private LocalDate dateTime;

}
