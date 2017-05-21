package ExampleTests.Registration;

import ExamplePageObject.BaseCommandsToBrowser;
import ExamplePageObject.SREApplication.RegistrationForm;
import org.testng.Assert;
import org.testng.annotations.*;

public class FieldsValidationCheck {

    @BeforeSuite
    public void openBrowserForTests() throws Exception {
        BaseCommandsToBrowser.setUp("http://autotest.sre.pp.ciklum.com/registration/");
    }

    @Test
    public void CheckAbsenceOfCreatingNonUniqueCompany() {
        RegistrationForm.fillRegistrationFormAndJoinCompany("unique.tester.mail@gmail.com", "1qaz@WSX", "Ciklum");

        boolean checkAbsenceOfCreatingNewCompany = RegistrationForm.checkAbsenceOfCreatingNewCompany();
        Assert.assertEquals(false, checkAbsenceOfCreatingNewCompany);
    }

    @Test
    public void InvalidEmailCheck() throws Exception {
        RegistrationForm.refreshPage();
        RegistrationForm.enterEmail("unique.tester.mail+2@gmail");
        RegistrationForm.enterFirstName("Test");
        RegistrationForm.enterLastName("Autotest");
        RegistrationForm.enterPassword("1qaz@WSX");
        RegistrationForm.enterConfirmPassword("1qaz@WSX");
        RegistrationForm.selectCountry("Afghanistan");
        RegistrationForm.selectCompanyName("Ciklum");
        RegistrationForm.clickRegisterButton();

        boolean invalidEmailValidation = RegistrationForm.invalidEmailValidationDisplayed();
        Assert.assertEquals(true, invalidEmailValidation);
    }

    @Test
    public void ShortFirstNameCheck() throws Exception {
        RegistrationForm.refreshPage();
        RegistrationForm.enterEmail("unique.tester.mail+2@gmail.com");
        RegistrationForm.enterFirstName("T");
        RegistrationForm.enterLastName("Autotest");
        RegistrationForm.enterPassword("1qaz@WSX");
        RegistrationForm.enterConfirmPassword("1qaz@WSX");
        RegistrationForm.selectCountry("Afghanistan");
        RegistrationForm.selectCompanyName("Ciklum");
        RegistrationForm.clickRegisterButton();

        boolean shortFirstNameValidation = RegistrationForm.shortFirstNameValidationDisplayed();
        Assert.assertEquals(true, shortFirstNameValidation);
    }

    @Test
    public void ShortLastNameCheck() throws Exception {
        RegistrationForm.refreshPage();
        RegistrationForm.enterEmail("unique.tester.mail+2@gmail.com");
        RegistrationForm.enterFirstName("Test");
        RegistrationForm.enterLastName("A");
        RegistrationForm.enterPassword("1qaz@WSX");
        RegistrationForm.enterConfirmPassword("1qaz@WSX");
        RegistrationForm.selectCountry("Afghanistan");
        RegistrationForm.selectCompanyName("Ciklum");
        RegistrationForm.clickRegisterButton();

        boolean shortLastNameValidation = RegistrationForm.shortLastNameValidationDisplayed();
        Assert.assertEquals(true, shortLastNameValidation);
    }

    @Test
    public void ShortSamePasswordsCheck() throws Exception {
        RegistrationForm.refreshPage();
        RegistrationForm.enterEmail("unique.tester.mail+2@gmail.com");
        RegistrationForm.enterFirstName("Test");
        RegistrationForm.enterLastName("Autotest");
        RegistrationForm.enterPassword("1");
        RegistrationForm.enterConfirmPassword("1");
        RegistrationForm.selectCountry("Afghanistan");
        RegistrationForm.selectCompanyName("Ciklum");
        RegistrationForm.clickRegisterButton();

        boolean shortSamePasswordsValidation = RegistrationForm.shortSamePasswordsValidationDisplayed();
        Assert.assertEquals(true, shortSamePasswordsValidation);
    }

    @Test
    public void PasswordsMismatchCheck() throws Exception {
        RegistrationForm.refreshPage();
        RegistrationForm.enterEmail("unique.tester.mail+2@gmail.com");
        RegistrationForm.enterFirstName("Test");
        RegistrationForm.enterLastName("Autotest");
        RegistrationForm.enterPassword("@WSX1qaz");
        RegistrationForm.enterConfirmPassword("1qaz@WSX");
        RegistrationForm.selectCountry("Afghanistan");
        RegistrationForm.selectCompanyName("Ciklum");
        RegistrationForm.clickRegisterButton();

        boolean mismatchValidation = RegistrationForm.passAndConfirmPassMismatchValidationDiplayed();
        Assert.assertEquals(true, mismatchValidation);
    }

    @Test
    public void ShortMismatchPasswordsCheck() throws Exception {
        RegistrationForm.refreshPage();
        RegistrationForm.enterEmail("unique.tester.mail+2@gmail.com");
        RegistrationForm.enterFirstName("Test");
        RegistrationForm.enterLastName("Autotest");
        RegistrationForm.enterPassword("1");
        RegistrationForm.enterConfirmPassword("1qaz@WSX");
        RegistrationForm.selectCountry("Afghanistan");
        RegistrationForm.selectCompanyName("Ciklum");
        RegistrationForm.clickRegisterButton();

        boolean shortSamePasswordsValidation = RegistrationForm.shortSamePasswordsValidationDisplayed();
        Assert.assertEquals(true, shortSamePasswordsValidation);

        boolean mismatchValidation = RegistrationForm.passAndConfirmPassMismatchValidationDiplayed();
        Assert.assertEquals(true, mismatchValidation);
    }
}