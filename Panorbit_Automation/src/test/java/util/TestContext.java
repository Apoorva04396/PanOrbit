package util;

import managers.PageObjectManager;
import managers.WebDriverManager;

public class TestContext {
    WebDriverManager webDriverManager;
    PageObjectManager pageObjectManager;

    public TestContext()  {

        webDriverManager = new WebDriverManager();
        try {
            pageObjectManager = new PageObjectManager(webDriverManager.getDriver());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public WebDriverManager getWebDriverManager()
    {
        return webDriverManager;
    }

    public PageObjectManager getPageObjectManager()
    {
        return pageObjectManager;
    }
}
