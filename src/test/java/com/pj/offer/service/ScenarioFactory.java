package com.pj.offer.service;
import com.pj.offer.domain.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScenarioFactory {

    public static Offer newOffer(){
        Offer offer = new Offer();
        offer.setId(1L);
        offer.setIdProduct(1L);
        offer.setActive(true);
        offer.setFim(LocalDate.ofEpochDay(1L));
        offer.setId(1L);
        offer.setInicio(LocalDate.ofEpochDay(1L));
        offer.setDesconto(BigDecimal.valueOf(1L));
        offer.setDescricao("Descricao");
        return offer;
    }

    public static Optional<Offer> newOptionalOffer(){
        Offer offer = new Offer();
        offer.setId(1L);
        offer.setIdProduct(1L);
        offer.setActive(true);
        offer.setFim(LocalDate.ofEpochDay(1L));
        offer.setId(1L);
        offer.setInicio(LocalDate.ofEpochDay(1L));
        offer.setDesconto(BigDecimal.valueOf(1L));
        offer.setDescricao("Descricao");
        return Optional.of(offer);
    }

    public static Offer emptyOffer(){
        return new Offer();
    }

    public static Pageable newPageable(){
        Pageable pageable = Pageable.ofSize(5);
        return pageable;
    }

    public static Page<Offer> newPage(){
        List<Offer> offer = new ArrayList<>();
        offer.add(newOffer());
        Page<Offer> offerPage = new PageImpl<>(offer);
        return offerPage;
    }

}
