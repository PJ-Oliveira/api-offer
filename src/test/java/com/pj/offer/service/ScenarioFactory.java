package com.pj.offer.service;
import com.pj.offer.domain.Offer;
import com.pj.offer.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ScenarioFactory {


    public static Product newProduct() {
        Product product = new Product();
        product.setIdProduct(17L);
        product.setName("Colgate");
        product.setType("Toothpaste");
        return product;
    }



    public static Offer newOffer(){
        Offer offer = new Offer();
        offer.setId(1L);
        offer.setDesconto(BigDecimal.valueOf(1L));
        offer.setFim(LocalDate.ofYearDay(2035, 30));
        offer.setInicio(LocalDate.of(2021, 12, 12));
        offer.setActive(true);
        offer.setDescricao("Descrição");
        return offer;
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

    public static Offer fimIsEqualToInicio(){
        Offer offer = new Offer();
        offer.setFim(LocalDate.ofYearDay(2020, 30)); //fim
        offer.setInicio(LocalDate.ofYearDay(2020, 30)); //inicio
        return offer;
    }

    public static Offer fimIsBeforeToInicio(){
        Offer offer = new Offer();
        offer.setFim(LocalDate.ofYearDay(2018, 30));
        offer.setInicio(LocalDate.ofYearDay(2020, 30));
        return offer;
    }

    public static Offer fimIsBeforeToNow(){
        Offer offer = new Offer();
        offer.setFim(LocalDate.of(2020, 12, 12));
        offer.setInicio(LocalDate.of(1990, 12, 12));
        return offer;
    }

    public static Offer inicioIsBeforeToNow(){
        Offer offer = new Offer();
        offer.setFim(LocalDate.ofYearDay(2035, 30));
        offer.setInicio(LocalDate.of(1990, 12, 12));
        return offer;
    }

    public static Offer validDateRange(){
        Offer offer = new Offer();
        offer.setFim(LocalDate.ofYearDay(2035, 30));
        offer.setInicio(LocalDate.of(2021, 12, 12));
        return offer;
    }
}
