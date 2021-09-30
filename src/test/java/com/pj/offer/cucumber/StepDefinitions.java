package com.pj.offer.cucumber;

import com.pj.offer.domain.model.Offer;
import com.pj.offer.domain.model.Product;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StepDefinitions {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl = "http://localhost:8080/offers/api/v1";

    @Given("I can list all offer")
    public void willListAllOffer(){
        restTemplate.getForObject(postUrl, List.class);
        List<Offer> allOffers = restTemplate.getForObject(postUrl, List.class);
        log.info(allOffers);
        assertTrue(!allOffers.isEmpty());

    }

    @Given("I am sending a offer to be created with active <true>, desconto <10.00>," +
            "descricao <descricao>, fim <\"2031-12-31\">, inicio <\"2021-12-31\">," +
            "products <\"products\": \"name\": \"teste\", \"type\": \"testes\">\n")
    public void willSendAOffer(){
        Offer newOffer = new Offer();
        newOffer.setActive(true);
        newOffer.setDescricao("descricao");
        newOffer.setFim(LocalDate.ofYearDay(2035, 30));
        newOffer.setInicio(LocalDate.of(2021, 12, 12));
        newOffer.setProducts(null);
        Offer offer = restTemplate.postForObject(postUrl, newOffer, Offer.class);
        log.info(offer);
        assertNotNull(offer);
        throw new PendingException();
    }


    @Then("I should be able to see my newly offer")
    public void willBeAbleToSeeOffer(){

        throw new PendingException();
    }






}
