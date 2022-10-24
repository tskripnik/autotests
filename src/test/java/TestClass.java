import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestClass {
    public static WebDriver driver;

    @BeforeClass
    public static void SetUp() throws Exception {
        /*driver = new RemoteWebDriver(
            new URL("http://selenium:4444/wd/hub"),
            DesiredCapabilities.chrome()
        );*/
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void FirstTest() {
        System.out.println("Test run");
        driver.get("google.com");
        System.out.println("Test finished");
    }

    @AfterClass
    public static void CloseBrowser() {
        driver.quit();
    }
}
