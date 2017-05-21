package ExamplePageObject;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import static com.codeborne.selenide.Selenide.open;


public class BaseCommandsToBrowser {
    public static WebDriver driver;
    public static String url;

    public static void setUp(String url) throws Exception {
        //driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), DesiredCapabilities.chrome());
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();
        open(url);
    }

    public static void refreshPage() {
        driver.navigate().refresh();
    }

    public static void openURL(String url) {
        driver.get(url);
    }
}