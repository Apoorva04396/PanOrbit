package managers;

import PageObject.HomePage;
import PageObject.LoginPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    public PageObjectManager(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public LoginPage getLoginPage() {
        return (this.loginPage == null) ? new LoginPage(driver) : this.loginPage;
    }

    public HomePage getHomePage() {
        return (this.homePage == null) ? new HomePage(driver) : this.homePage;
    }
}
