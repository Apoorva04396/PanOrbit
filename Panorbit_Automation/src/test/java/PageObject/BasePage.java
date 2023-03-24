package PageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver webDriver;
    long noOfSeconds = 30;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void waitUntilElementVisible(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(noOfSeconds));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            // screenshot("waitUntilElementVisible");
        } catch (Exception e) {
            e.printStackTrace();
            // Assert.fail();
            throw e;
        }
    }

    public void waitUntilElementToBeClickable(WebElement webElement) {
        try {

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(noOfSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(webElement));

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void clear(WebElement webElement) {
        try {
            webElement.clear();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void writeText(WebElement webElement, String text) {
        try {
            clear(webElement);
            webElement.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void click(WebElement webElement) {
        try {
            waitUntilElementToBeClickable(webElement);
            webElement.click();
        } catch (Exception e) {
            e.printStackTrace();

            throw e;
        }
    }

    public void waitForPageLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(noOfSeconds));
        wait.until(pageLoadCondition);
    }
}
