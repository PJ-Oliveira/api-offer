package cucumber;
import com.pj.offer.domain.model.Offer;
import com.pj.offer.domain.model.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ScenarioContextOfTheTests {




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
