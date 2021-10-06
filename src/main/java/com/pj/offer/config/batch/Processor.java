package com.pj.offer.config.batch;

import com.pj.offer.domain.model.Offer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<Offer, Offer> {


    private static final Map<String, String> OFFER_PATHS = new HashMap<String, String>();

    public Processor(){
        OFFER_PATHS.put("1", "Offer");

    }

    @Override
    public Offer process(Offer offer) throws Exception {

        Long idOffer =offer.getId();
        Long codeOffer = Long.valueOf(OFFER_PATHS.get(idOffer));
        offer.setId(codeOffer);
        return offer;
    }
}