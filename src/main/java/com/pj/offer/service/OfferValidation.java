package com.pj.offer.service;

import com.pj.offer.advice.exception.OfferException;
import com.pj.offer.domain.Offer;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class OfferValidation {

    public void validateDate(Offer offer){
       if(offer.getFim().equals(offer.getInicio()))
       {
           throw new OfferException("OfferException: Date Fim  cannot be equals  to Date Inicio");
       } else if(offer.getFim().isBefore(offer.getInicio()))
       {
           throw new OfferException("OfferException: Date Fim must be necessarily after Date Inicio");
       } else if(offer.getFim().isBefore(LocalDate.now()))
       {
           throw new OfferException("OfferException: Date Fim must be necessarily after Today");
       } else if(offer.getInicio().isBefore(LocalDate.now()))
       {
           throw new OfferException("OfferException: Date Início cannot be before Today");
       }
    }

}
