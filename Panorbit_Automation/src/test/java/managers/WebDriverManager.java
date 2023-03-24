package managers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

public class WebDriverManager {
    WebDriver driver;
    private static final Properties properties_env = new Properties();

    static {
        try {
            try {
                FileReader reader=new FileReader("./src/test/resources/configs/Environment.properties");
                properties_env.load(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found.");
        }
    }

    public WebDriver getDriver() {
        if (driver == null) driver = createWebDriver();
        return driver;
    }
    private WebDriver createWebDriver() {
        String browserName = getBrowsername();
        if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("ch")) {

            io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            options.addArguments("--disable-gpu");
            options.addArguments("disable-notifications");
            options.addArguments("--dns-prefetch-disbale");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff")) {
            io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.privacy.trackingprotection.menu", "always");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setProfile(profile);
            firefoxOptions.setAcceptInsecureCerts(true);
            FirefoxOptions fOptions = new FirefoxOptions();
            fOptions.addArguments("-private");
            // WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(fOptions);

        }

        else if (browserName.equalsIgnoreCase("edge") || browserName.equalsIgnoreCase("ee")) {
            io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("-inprivate");
            // edgeOptions.addArguments("headless");
            edgeOptions.addArguments("disable-gpu");
            // WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver(edgeOptions);

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }

    public static String getBrowsername() {

        String browserName = properties_env.getProperty("browsername");
        return browserName;
    }

    public void enterUrl(){
        String url = properties_env.getProperty("url");
        driver.get(url);
    }
}
