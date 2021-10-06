package com.pj.offer.config.batch.processor;

import com.pj.offer.domain.model.Offer;
import org.springframework.batch.item.ItemProcessor;

public class OfferItemProcessor implements ItemProcessor<Offer, Offer> {
    @Override
    public Offer process(Offer offer) throws Exception {
        return offer;
    }
}
