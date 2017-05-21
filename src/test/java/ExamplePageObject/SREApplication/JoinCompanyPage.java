package ExamplePageObject.SREApplication;

import ExamplePageObject.BaseCommandsToBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.codeborne.selenide.Selenide.$;

public class JoinCompanyPage extends BaseCommandsToBrowser {
    public static WebDriverWait waitLoaded = new WebDriverWait(driver, 30);

    public static Boolean checkIfStatusIsRejected() {
        waitLoaded.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".join-request-status")));
        return driver.getPageSource().contains("Rejected");
    }

    public static Boolean checkIfStatusIsOpened() {
        return driver.getPageSource().contains("Opened");
    }

    public static void resendJoinRequest() {
        $(".btn.btn-lg.btn-info.remove-entity.btn-resend").click();
        waitLoaded.until(ExpectedConditions.elementToBeClickable($(".btn.btn-warning.remove-entity-href")));
        $(".btn.btn-warning.remove-entity-href").click();
    }

}
