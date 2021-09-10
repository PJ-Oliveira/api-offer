package com.pj.offer.validator;

import com.pj.offer.config.advice.exception.OfferException;
import com.pj.offer.domain.Offer;
import com.pj.offer.domain.dto.OfferDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;

@Service
public class OfferValidation {

    public OfferDto validate(Offer offer) {
            if(offer.getFim().isAfter(LocalDate.now()) && offer != null) {
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(offer, (Type) OfferDto.class);
            }
            throw new OfferException();
    }
}
