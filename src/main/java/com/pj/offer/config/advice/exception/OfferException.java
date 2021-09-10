package com.pj.offer.config.advice.exception;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class OfferException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public OfferException(String string){super();}
}
