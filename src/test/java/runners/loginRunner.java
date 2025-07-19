package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/Login.feature",
        glue = {"stepdefinitions"},
        plugin = {"pretty"},
        tags = "@login"
)
public class loginRunner extends AbstractTestNGCucumberTests {
}