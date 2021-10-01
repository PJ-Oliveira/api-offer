package cucumber;
import com.pj.offer.domain.model.Offer;
import com.pj.offer.domain.model.Product;
import com.pj.offer.domain.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ScenarioFactoryCucumber {

    public static User userToBeUsed(){
        User newUser = new User();
        newUser.setUsername("admin");
        newUser.setPassword("12345");
        return newUser;
    }

    public static Offer offerInactive(){
        Offer newOffer = new Offer();
        newOffer.setActive(false);
        newOffer.setDescricao("descricao");
        newOffer.setFim(LocalDate.ofYearDay(2035, 30));
        newOffer.setInicio(LocalDate.of(2021, 12, 12));
        newOffer.setDesconto(BigDecimal.valueOf(1L));
        List<Product> products = new ArrayList<Product>();
        newOffer.setProducts(products);
        return newOffer;
    }


    public static Offer offerCucumber(){
        Offer newOffer = new Offer();
        newOffer.setActive(true);
        newOffer.setDescricao("descricao");
        newOffer.setFim(LocalDate.ofYearDay(2035, 30));
        newOffer.setInicio(LocalDate.of(2021, 12, 12));
        newOffer.setDesconto(BigDecimal.valueOf(1L));
        List<Product> products = new ArrayList<Product>();
        newOffer.setProducts(products);
        return newOffer;
    }
}
