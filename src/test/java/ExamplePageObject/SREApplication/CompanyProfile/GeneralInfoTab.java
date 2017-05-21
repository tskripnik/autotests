package ExamplePageObject.SREApplication.CompanyProfile;

import ExamplePageObject.BaseCommandsToBrowser;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class GeneralInfoTab extends BaseCommandsToBrowser {
    public static WebDriverWait waitLoaded = new WebDriverWait(driver, 30);

    public static void openGeneralInfoTab() {
        $(By.xpath("//a[contains(text(),'General Info')]")).click();
    }

    public static void openContactsTab() {
        $(By.xpath("//a[contains(text(),'Contacts')]")).click();
    }

    public static void openBroadcastingDetailsTab() {
        $(By.xpath("//a[contains(text(),'Broadcasting Details')]")).click();
    }

    public static void openTranscodingProfilesTab() {
        $(By.xpath("//a[contains(text(),'Transcoding Profiles')]")).click();
    }

    public static void clickUpgradeToPRO() {
        $(".btn-auth.btn-upgrade.col-md-12").click();
    }

    public static void agreeWithTermsAndConditions() throws Exception {
        Thread.sleep(250);
        $("#update-to-pro-link-hidden").shouldBe(Condition.visible);
        $(By.xpath("//span[contains(@class, 'js-open-ignore')]")).click();
        $("#update-to-pro-link").click();
    }

    public static void closeInfoMessage() throws Exception {
        Thread.sleep(500);
        $(".close").click();
        waitLoaded.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-round-text")));
    }
}
