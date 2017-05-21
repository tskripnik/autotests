package ExamplePageObject.SREApplication;

import ExamplePageObject.BaseCommandsToBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static com.codeborne.selenide.Selenide.$;

public class MainApplicationNavigation extends BaseCommandsToBrowser {
    public static WebDriverWait waitLoaded = new WebDriverWait(driver, 30);

    public static void openCompanyProfile() throws Exception{
        //waitLoaded.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".logo-img")));
        $(".logo-img").click();
        //waitLoaded.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'General Info')]")));
    }








    public static void openUserProfile() throws Exception {
        $(".icon.icon-lg.icon-arrow_menu-btn.menu-icon").click();
        List<WebElement> elements= driver.findElements(By.cssSelector(".dropdown__item"));
        elements.get(elements.size()-4).click();
        waitLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".user-header-title")));
    }

    public static void openJoinCompanyPage() throws Exception {
        $(".icon.icon-lg.icon-arrow_menu-btn.menu-icon").click();
        List<WebElement> elements= driver.findElements(By.cssSelector(".dropdown__item"));
        elements.get(elements.size()-3).click();
        Thread.sleep(200);
    }

    public static void openNotificationsPage() {
        driver.get("http://autotest.sre.pp.ciklum.com/notifications");
    }

    public static void logOut() {
        driver.get("http://autotest.sre.pp.ciklum.com/logout");
    }

    public static void loginToSystem(String email, String password) throws Exception {
        $(By.id("_username")).sendKeys(email);
        $(By.id("_password")).sendKeys(password);
        $(".submit-text").click();
        Thread.sleep(250);
    }
}
