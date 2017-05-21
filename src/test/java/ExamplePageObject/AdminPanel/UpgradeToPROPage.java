package ExamplePageObject.AdminPanel;

import ExamplePageObject.BaseCommandsToBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class UpgradeToPROPage extends BaseCommandsToBrowser {
    public static void rejectUpgradetoPRORequest() {
        $(".btn.btn-sm.view_link.btn-danger.btn-reject").click();
    }

    public static void setRequestRejectReasonAndSubmit() {
        $("#reject_upgrade_to_pro_request_reason").setValue("Autotest request reject reason");
        $(".btn.btn-success").click();
    }

    public static void acceptUpgradetoPRORequest(String paidType) throws Exception {
        $(".btn.btn-sm.btn-success.view_link").click();
        if (paidType == "pre-paid"){
            Select dropdown = new Select(driver.findElement(By.xpath(".//select[contains(@id, '_paidType')]")));
            dropdown.selectByValue("1");
        }
        else if (paidType == "post-paid"){
            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) driver)
                        .executeScript("document.querySelector('select.profile-paid-type').setAttribute('style', 'display: block');");
            }
            //$("#select2-chosen-1").click();
            Select dropdown = new Select(driver.findElement(By.cssSelector("select.profile-paid-type")));
            dropdown.selectByValue("2");
            /*if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) driver)
                        //.executeScript("$('#select2-chosen-1').val('2').trigger('change');");
                        .executeScript("var e = jQuery.Event(\"keypress\"); e.which = 37; e.keyCode = 37; $('#select2-chosen-1').trigger(e);");
            }*/

        }
        List<WebElement> elements = driver.findElements(By.xpath("//input[contains(@value, '0')]"));
        elements.get(1).sendKeys("1000");
        $(By.xpath("//input[contains(@value, '1')]")).setValue("10");
        $(".btn.btn-success").click();
    }
}
