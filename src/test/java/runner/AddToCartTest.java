package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/FeatureFile/addToCart.feature",
        glue = {"stepDef"},
        plugin = {"pretty", "html:target/report.html"}
)

public class AddToCartTest {
}
