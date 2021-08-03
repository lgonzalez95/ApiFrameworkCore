package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(
        features = "src/test/resources/features",
        plugin = "json:target/jsonReports/cucumber-report.json",
        glue = {"stepDefinitions"})
@RunWith(Cucumber.class)
public class TestRunner {
}
