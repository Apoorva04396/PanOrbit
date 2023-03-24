package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{
    public HomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Welcome to Facebook')]")
    private WebElement welcomeMessage;

    public boolean verifyLiveVideoIconInHomePage() {
        try {
            boolean status = false;
            waitForPageLoad();
            waitUntilElementVisible(welcomeMessage);
            if(welcomeMessage.isDisplayed()){
                status = true;
            }
            return status;
        } catch (Exception e) {
            throw e;
        }
    }
}
