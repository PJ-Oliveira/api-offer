package cucumber;

import com.pj.offer.OfferApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = OfferApplication.class)
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber.json"},
        features = "src/test/java/resources/cucumber.feature")
public class CucumberRunnerTest {

}
