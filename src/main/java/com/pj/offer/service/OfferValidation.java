package com.pj.offer.service;

import com.pj.offer.advice.exception.OfferException;
import com.pj.offer.domain.model.Offer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@Slf4j
public class OfferValidation {

    public void validateDate(Offer offer){
        log.error("An exception occurred", new OfferException("Please, verify the date"));
       if(offer.getFim().equals(offer.getInicio()))
       {
           throw new OfferException("OfferException: Date Fim  cannot be equals  to Date Inicio");
       } if(offer.getFim().isBefore(offer.getInicio()))
       {
           throw new OfferException("OfferException: Date Fim must be necessarily after Date Inicio");
       } if(offer.getFim().isBefore(LocalDate.now()))
       {
           throw new OfferException("OfferException: Date Fim must be necessarily after Today");
       } if(offer.getInicio().isBefore(LocalDate.now()))
       {
           throw new OfferException("OfferException: Date Início cannot be before Today");
       }
    }



}
