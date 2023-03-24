package StepDefinition;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import util.TestContext;

public class serviceHooks {
    TestContext testContext;

    public serviceHooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            testContext.getWebDriverManager().getDriver().quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
