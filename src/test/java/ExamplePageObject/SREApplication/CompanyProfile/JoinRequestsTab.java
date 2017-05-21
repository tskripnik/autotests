package ExamplePageObject.SREApplication.CompanyProfile;

import ExamplePageObject.BaseCommandsToBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class JoinRequestsTab extends BaseCommandsToBrowser{
    public static void openJoinRequestsTab() {
        $(By.xpath("//a[contains(text(),'Join Requests')]")).click();
    }

    public static void acceptJoinRequest() throws Exception {

        $(".btn.btn-success").click();
        Thread.sleep(500);
        List<WebElement> elements = driver.findElements(By.cssSelector(".btn.btn-warning.remove-entity-href"));
        elements.get(1).click();
    }

    public static void rejectJoinRequest() throws Exception {
        $(".btn.btn-danger").click();
        Thread.sleep(500);
        $(".btn.btn-warning.remove-entity-href").click();
        Thread.sleep(500);
    }
}
