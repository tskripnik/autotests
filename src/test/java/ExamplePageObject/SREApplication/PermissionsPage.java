package ExamplePageObject.SREApplication;

import ExamplePageObject.BaseCommandsToBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.codeborne.selenide.Selenide.$;

public class PermissionsPage extends BaseCommandsToBrowser {
    public static WebDriverWait waitLoaded = new WebDriverWait(driver, 30);

    public static Boolean checkIfNoPermissionRoleSelected() {
        WebElement noPesrmissionsActive = $(".js-role-button.btn.btn-info.js-role-default.active");
        if (noPesrmissionsActive != null){
            return true;
        }
        else {
            return false;
        }
    }
}
