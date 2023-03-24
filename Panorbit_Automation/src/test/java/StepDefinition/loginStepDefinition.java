package StepDefinition;

import PageObject.HomePage;
import PageObject.LoginPage;
import config.ExcelFileReader;
import io.cucumber.java8.En;
import managers.WebDriverManager;
import util.TestContext;

public class loginStepDefinition implements En {
    TestContext testContext;
    WebDriverManager webDriverManager;
    LoginPage loginPage;
    HomePage homePage;

    boolean checkStatus;

    public loginStepDefinition(TestContext testContext){
        this.testContext = testContext;
        this.webDriverManager =  testContext.getWebDriverManager();
        this.loginPage = testContext.getPageObjectManager().getLoginPage();
        this.homePage = testContext.getPageObjectManager().getHomePage();

        Given("enter the url", () -> {
            webDriverManager.enterUrl();
        });

        When("user enters valid username and password", () -> {
            loginPage.enterUserName(ExcelFileReader.getCellValue("LoginDetails.xlsx","LoginData",2,"A"));
            loginPage.enterPassword(ExcelFileReader.getCellValue("LoginDetails.xlsx","LoginData",2,"B"));
            loginPage.clickLogin();
        });

        Then("home page should be displayed", () -> {
           checkStatus = homePage.verifyLiveVideoIconInHomePage();
        });

        Then("update in the sheet", () -> {
            if(checkStatus){
                ExcelFileReader.writeToCell("LoginDetails.xlsx","LoginData",1,3,"Pass");
            }else{
                ExcelFileReader.writeToCell("LoginDetails.xlsx","LoginData",1,3,"Fail");
            }
        });

        When("user enters invalid username and password", () -> {
            loginPage.enterUserName(ExcelFileReader.getCellValue("LoginDetails.xlsx","LoginData",3,"A"));
            loginPage.enterPassword(ExcelFileReader.getCellValue("LoginDetails.xlsx","LoginData",3,"B"));
            loginPage.clickLogin();
        });

        Then("proper error message should be displayed", () -> {
            checkStatus = loginPage.verifyErrorMessage();
        });

        Then("update the status in the sheet", () -> {
            if(checkStatus){
                ExcelFileReader.writeToCell("LoginDetails.xlsx","LoginData",2,3,"Fail");
            }
        });
    }
}
