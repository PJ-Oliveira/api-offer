package com.pj.offer.service;
import com.pj.offer.domain.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

public class TestTemplet {

    public static Offer newOffer(){
        Offer offer = new Offer();
        offer.setId(1L);
        return offer;
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
