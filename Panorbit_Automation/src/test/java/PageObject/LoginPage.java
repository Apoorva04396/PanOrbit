package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(how = How.ID, using = "email")
    private WebElement userNameTextBox;

    @FindBy(how = How.XPATH, using = "//input[@id='pass']")
    private WebElement passwordTextBox;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Log in')]")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'entered is incorrect')]")
    private WebElement verifyErrorMessage;

    public void enterUserName(String userName) {
        try {
            waitForPageLoad();
            waitUntilElementVisible(userNameTextBox);
            writeText(userNameTextBox,userName);
        } catch (Exception e) {
            throw e;
        }
    }


    public void enterPassword(String password) {
        try {
            waitUntilElementToBeClickable(passwordTextBox);
            writeText(passwordTextBox,password);
        } catch (Exception e) {
            throw e;
        }
    }

    public void clickLogin() {
        try {
            waitUntilElementToBeClickable(loginButton);
            click(loginButton);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean verifyErrorMessage() {
        try {
            boolean status = false;
            waitForPageLoad();
            waitUntilElementVisible(verifyErrorMessage);
            if(verifyErrorMessage.isDisplayed()){
              status = true;
            }
            return status;
        } catch (Exception e) {
            throw e;
        }
    }
}
