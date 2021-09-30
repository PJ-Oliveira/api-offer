package cucumber;

import com.pj.offer.OfferApplication;
import com.pj.offer.domain.model.Offer;
import com.pj.offer.domain.model.Product;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@ContextConfiguration(classes = OfferApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StepDefinitions {

    @LocalServerPort
    private int port;
    private String url = "http://localhost";
    private RestTemplate restTemplate = new RestTemplate();

    @Given("That, well, I can list all offer")
    public void willListAllOffer(){
        String connection = url + ":" + port + "/offers/api/v1";
        List<Offer> allOffers = restTemplate.getForObject(connection, List.class);
        log.info("{}!", allOffers);
        assertTrue(!allOffers.isEmpty());

    }

    @And("I can create a newly offer too")
    public void willSendAOffer(){
        String connection = url + ":" + port + "/offers/api/v1";
        Offer newOffer = new Offer();
        newOffer.setActive(true);
        newOffer.setDescricao("descricao");
        newOffer.setFim(LocalDate.ofYearDay(2035, 30));
        newOffer.setInicio(LocalDate.of(2021, 12, 12));
        newOffer.setDesconto(BigDecimal.valueOf(1L));
        List<Product> products = new ArrayList<Product>();
        newOffer.setProducts(products);
        Offer offer = restTemplate.postForObject(connection, newOffer, Offer.class);
        log.info("{}!", offer);
        assertNotNull(offer);
    }

    @Then("I can search for a another offer")
    public void iCanSearchForAnotherOffer(){
        String connection = url + ":" + port + "/offers/api/v1/" + 1l;
        Offer offer = restTemplate.getForObject(connection, Offer.class);
        log.info("{}!", offer);
        assertNotNull(offer);
    }

    @And("If I want to, I can delete a offer as well")
    public void ifIWantToDelete(){
        String connection = url + ":" + port + "/offers/api/v1/" + 16;
        restTemplate.delete(connection);
    }
    
}
