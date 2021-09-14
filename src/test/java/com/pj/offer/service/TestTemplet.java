package com.pj.offer.service;

import com.pj.offer.domain.Offer;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;

public class TestTemplet {

    @TestTemplate
    public static Offer newOffer(){
        Offer offer = new Offer();
        offer.setId(any());
        return offer;
    }

    @TestTemplate
    public static Pageable newPageable(){
        Pageable pageable = Pageable.ofSize(5);
        return pageable;
    }

    @TestTemplate
    public static Page<Offer> newPage(){
        List<Offer> offer = new ArrayList<>();
        offer.add(newOffer());
        Page<Offer> offerPage = new PageImpl<>(offer);
        return offerPage;
    }

}
