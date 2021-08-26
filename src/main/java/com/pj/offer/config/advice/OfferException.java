package com.pj.offer.config.advice;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class OfferException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public OfferException(Object id){
        super("Resource with id: " + id + "not found");
    }
}
