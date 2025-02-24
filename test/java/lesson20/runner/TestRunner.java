package lesson20.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/java/features/login.feature",
        glue = "lesson20.steps",
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
@Test
public class TestRunner extends AbstractTestNGCucumberTests {
}
