package ExamplePageObject.SREApplication;

import static com.codeborne.selenide.Selenide.*;

import ExamplePageObject.BaseCommandsToBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleMail extends BaseCommandsToBrowser {
    public static void loginToGmail() throws Exception{
        Thread.sleep(500);
        $(By.id("Email")).setValue("unique.tester.mail@gmail.com");
        $(By.id("next")).click();
        Thread.sleep(500);
        $(By.id("Passwd")).setValue("4rfv3edc2wsx!QAZ");
        $(By.id("signIn")).click();
        Thread.sleep(250);
    }

    public static void copyRegistrationLinkFromEmail() throws InterruptedException {
        for (int i = 0; i < 10; i++){
            Boolean registrationEmailAccepted = driver.getPageSource().contains("Registration on SRE");
            if (registrationEmailAccepted == true){
                break;
            }
            else if (registrationEmailAccepted == false){
                BaseCommandsToBrowser.refreshPage();
                Thread.sleep(1000);
            }
        }
        $(By.xpath("//b[contains(text(),'Registration on SRE')]")).click();
        BaseCommandsToBrowser.url = $(By.xpath("//a[contains(text(),'Confirm registration')]")).getAttribute("href");
        $(By.xpath(".//input[@value='Delete']")).click();
    }
}
