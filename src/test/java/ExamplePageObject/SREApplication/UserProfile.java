package ExamplePageObject.SREApplication;

import ExamplePageObject.BaseCommandsToBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;

public class UserProfile extends BaseCommandsToBrowser {
    public static WebDriverWait waitLoaded = new WebDriverWait(driver, 30);

    public static void openGeneralInfoTab() {
        waitLoaded.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'General Info')]")));
        $(By.xpath("//a[contains(text(),'General Info')]")).click();
    }

    public static void openEditProfileTab() {
        waitLoaded.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Edit Profile')]")));
        $(By.xpath("//a[contains(text(),'Edit Profile')]")).click();
    }

    public static void openPermissionsTab() {
        waitLoaded.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Permissions')]")));
        $(By.xpath("//a[contains(text(),'Permissions')]")).click();
    }
}
