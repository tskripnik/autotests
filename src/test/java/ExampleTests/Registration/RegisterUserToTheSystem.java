package ExampleTests.Registration;

import ExamplePageObject.*;
import ExamplePageObject.SREApplication.GoogleMail;
import ExamplePageObject.SREApplication.LoginForm;
import ExamplePageObject.SREApplication.MainApplicationNavigation;
import ExamplePageObject.SREApplication.RegistrationForm;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Date;

import static ExamplePageObject.BaseCommandsToBrowser.driver;

public class RegisterUserToTheSystem {

    @Test
    public void RegisterWithCreatingNewCompany() throws Exception {
        int uniqueValue = Math.abs((int) new Date().getTime());
        String userEmail = "unique.tester.mail+" + uniqueValue + "@gmail.com";
        String userPassword = "1qaz@WSX";
        String companyName = "testCompany " + uniqueValue;

        //register User
        BaseCommandsToBrowser.openURL("http://autotest.sre.pp.ciklum.com/registration");
        RegistrationForm.fillRegistrationFormAndJoinCompany(userEmail, userPassword, companyName);
        RegistrationForm.clickRegisterButton();

        //copy registration link
        BaseCommandsToBrowser.openURL("http://mail.google.com/mail/h/");
        GoogleMail.loginToGmail();
        GoogleMail.copyRegistrationLinkFromEmail();

        //open registration link and login to application as new User
        BaseCommandsToBrowser.openURL(BaseCommandsToBrowser.url);
        RegistrationForm.loginAfterOpeningConfirmationLink();
        MainApplicationNavigation.loginToSystem(userEmail, userPassword);
        Assert.assertEquals("http://autotest.sre.pp.ciklum.com/content/screeners/shared/table", driver.getCurrentUrl());
        MainApplicationNavigation.logOut();
    }

    @Test
    public void RegisterWithJoinToExistingCompany() throws Exception {
        int uniqueValue = ((int) new Date().getTime()) * (-1);
        String userEmail = "unique.tester.mail+" + uniqueValue + "@gmail.com";
        String userPassword = "1qaz@WSX";
        String companyName = "Ciklum";

        //register User
        BaseCommandsToBrowser.openURL("http://autotest.sre.pp.ciklum.com/registration");
        RegistrationForm.fillRegistrationFormAndJoinCompany(userEmail, userPassword, companyName);
        RegistrationForm.clickRegisterButton();

        //copy registration link
        BaseCommandsToBrowser.openURL("http://mail.google.com/mail/h/");
        GoogleMail.copyRegistrationLinkFromEmail();

        //open registration link and login to application as new User
        BaseCommandsToBrowser.openURL(BaseCommandsToBrowser.url);
        RegistrationForm.loginAfterOpeningConfirmationLink();
        MainApplicationNavigation.loginToSystem(userEmail, userPassword);
        Assert.assertEquals("http://autotest.sre.pp.ciklum.com/content/screeners/shared/table", driver.getCurrentUrl());
        MainApplicationNavigation.logOut();
    }
}

