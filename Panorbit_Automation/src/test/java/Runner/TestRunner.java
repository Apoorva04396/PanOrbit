package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"StepDefinition"},
        tags = "@login",

        plugin = {
                "pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:test-output-thread",
                "rerun:target/failedrerun.txt"},

        monochrome = true, publish = true

)

public class TestRunner {

}
