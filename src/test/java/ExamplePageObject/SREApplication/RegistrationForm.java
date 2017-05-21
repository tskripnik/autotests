package ExamplePageObject.SREApplication;

import ExamplePageObject.BaseCommandsToBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationForm extends BaseCommandsToBrowser {

    public static void clickRegisterButton() throws Exception {
        $(".btn-auth.col-md-2.col-md-offset-5").click();
        Thread.sleep(250);
    }

    public static void enterEmail(String email) {
        $(By.id("user_email")).sendKeys(email);
    }

    public static void enterFirstName(String firstName) {
        $(By.id("user_firstName")).sendKeys(firstName);
    }

    public static void enterLastName(String lastName) {
        $(By.id("user_lastName")).sendKeys(lastName);
    }

    public static void enterPassword(String password) {
        $(By.id("user_plainPassword_password")).sendKeys(password);
    }

    public static void enterConfirmPassword(String confirmPassword) {
        $(By.id("user_plainPassword_confirm")).sendKeys(confirmPassword);
    }

    public static void selectCountry(String country) {
        Select dropdown = new Select(driver.findElement(By.id("user_company_country")));
        dropdown.selectByVisibleText(country);
    }

    public static void selectCompanyName(String companyName) {
        $(By.id("select2-chosen-2")).click();
        $(By.id("s2id_autogen2_search")).sendKeys(companyName);

        WebDriverWait waitLoaded = new WebDriverWait(driver, 30);
        waitLoaded.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".select2-result-label")));

        $(".select2-result-label").click();
    }

    public static void fillRegistrationFormAndJoinCompany(String userEmail, String userPassword, String companyName) {
        enterEmail(userEmail);
        enterFirstName("Test");
        enterLastName("Autotest");
        enterPassword(userPassword);
        enterConfirmPassword(userPassword);
        selectCountry("Afghanistan");
        selectCompanyName(companyName);
    }

    public static Boolean invalidEmailValidationDisplayed() {
        boolean invalidEmailValidation = $(".form-error-user-email").isDisplayed();
        return invalidEmailValidation;
    }

    public static Boolean shortFirstNameValidationDisplayed() {
        boolean shortFirstNameValidation = $(".form-error-user-firstName").isDisplayed();
        return shortFirstNameValidation;
    }

    public static Boolean shortLastNameValidationDisplayed() {
        boolean shortLastNameValidation = $(".form-error-user-lastName").isDisplayed();
        return shortLastNameValidation;
    }

    public static Boolean shortSamePasswordsValidationDisplayed() {
        boolean shortSamePasswordsValidation = $(".form-error-user-plainPassword").isDisplayed();
        return shortSamePasswordsValidation;
    }

    public static Boolean passAndConfirmPassMismatchValidationDiplayed() {
        boolean mismatchValidation = $(".value-to-duplicates-user_plainPassword_password").isDisplayed();
        return mismatchValidation;
    }

    public static Boolean checkAbsenceOfCreatingNewCompany() {
        boolean shortSamePasswordsValidation = driver.getPageSource().contains("(new)");
        return shortSamePasswordsValidation;
    }

    public static void loginAfterOpeningConfirmationLink() {
        WebDriverWait waitLoaded = new WebDriverWait(driver, 30);
        waitLoaded.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'log in')]")));
        $(By.xpath("//a[contains(text(),'log in')]")).click();
    }
}
