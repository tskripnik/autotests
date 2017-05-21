package ExampleTests.UpgradeCompanyToPRO;

import ExamplePageObject.AdminPanel.MainAdminPanelNavigation;
import ExamplePageObject.AdminPanel.UpgradeToPROPage;
import ExamplePageObject.BaseCommandsToBrowser;
import ExamplePageObject.SREApplication.*;
import ExamplePageObject.SREApplication.CompanyProfile.GeneralInfoTab;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Date;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.source;

public class UpgradeToPROActionsBeforePaying {
    private static String userEmail = "unique.tester.mail+basiccompany@gmail.com";
    private static String userPassword = "1qaz@WSX";

    /*@BeforeSuite
    public void openBrowserForTests() throws Exception {
        BaseCommandsToBrowser.setUp("http://autotest.sre.pp.ciklum.com/login");
    }*/

    @Test
    public void RequestUpgradeToPRO() throws Exception{
        BaseCommandsToBrowser.openURL("http://autotest.sre.pp.ciklum.com/login");
        MainApplicationNavigation.loginToSystem(userEmail, userPassword);
        MainApplicationNavigation.openCompanyProfile();
        GeneralInfoTab.clickUpgradeToPRO();
        GeneralInfoTab.agreeWithTermsAndConditions();
        GeneralInfoTab.closeInfoMessage();

        Assert.assertTrue(source().contains("The upgrading to PRO request is currently under review by SRE back office"));
        MainApplicationNavigation.logOut();
    }

    @Test
    public void SREAdminRejectsUpgradeToPRORequest() throws Exception{
        //login to Admin Panel and reject Upgrade to PRO request
        BaseCommandsToBrowser.openURL("http://autotest.sre.pp.ciklum.com/admin/");
        MainAdminPanelNavigation.loginToAdminPanel();
        MainAdminPanelNavigation.openUpgradeToPRORequests();
        UpgradeToPROPage.rejectUpgradetoPRORequest();
        UpgradeToPROPage.setRequestRejectReasonAndSubmit();

        //login to app and check company is Basic again
        BaseCommandsToBrowser.openURL("http://autotest.sre.pp.ciklum.com/login");
        MainApplicationNavigation.loginToSystem(userEmail, userPassword);
        MainApplicationNavigation.openNotificationsPage();
        NotificationsPage.notificationAppearanceCheck("Request to upgrade company was rejected. Reason: Autotest request reject reason");
        NotificationsPage.removeNotification();
        MainApplicationNavigation.logOut();
    }

    @Test
    public void UpgradeToPROWithPrePaidMethod() throws Exception{
        int uniqueValue = Math.abs((int) new Date().getTime());
        String userEmail = "unique.tester.mail+" + uniqueValue + "@gmail.com";
        String companyName = "testCompany " + uniqueValue;

        //register User with creating new Company
        BaseCommandsToBrowser.openURL("http://autotest.sre.pp.ciklum.com/registration");
        RegistrationForm.fillRegistrationFormAndJoinCompany(userEmail, userPassword, companyName);
        RegistrationForm.clickRegisterButton();
        BaseCommandsToBrowser.openURL("http://mail.google.com/mail/h/");
        //GoogleMail.loginToGmail();
        GoogleMail.copyRegistrationLinkFromEmail();
        BaseCommandsToBrowser.openURL(BaseCommandsToBrowser.url);
        RegistrationForm.loginAfterOpeningConfirmationLink();
        MainApplicationNavigation.loginToSystem(userEmail, userPassword);

        //send Upgrade to PRO request
        MainApplicationNavigation.openCompanyProfile();
        GeneralInfoTab.clickUpgradeToPRO();
        GeneralInfoTab.agreeWithTermsAndConditions();
        GeneralInfoTab.closeInfoMessage();
        MainApplicationNavigation.logOut();

        //login to Admin Panel and approve Upgrade to PRO request with Pre-Paid type
        BaseCommandsToBrowser.openURL("http://autotest.sre.pp.ciklum.com/admin");
        MainAdminPanelNavigation.loginToAdminPanel();
        MainAdminPanelNavigation.openUpgradeToPRORequests();
        UpgradeToPROPage.acceptUpgradetoPRORequest("pre-paid");

        //login to app and check company is Pre-Paid PRO
        BaseCommandsToBrowser.openURL("http://autotest.sre.pp.ciklum.com/login");
        MainApplicationNavigation.loginToSystem(userEmail, userPassword);
        MainApplicationNavigation.openCompanyProfile();
        Assert.assertTrue(source().contains("Upgrade to PRO Request was approved"));
        Assert.assertTrue(source().contains("Complete Upgrading"));
        MainApplicationNavigation.logOut();
    }

    @Test
    public void UpgradeToPROWithPostPaidMethod() throws Exception{
        int uniqueValue = Math.abs((int) new Date().getTime());
        String userEmail = "unique.tester.mail+" + uniqueValue + "@gmail.com";
        String companyName = "testCompany " + uniqueValue;

        //register User with creating new Company
        BaseCommandsToBrowser.openURL("http://autotest.sre.pp.ciklum.com/registration");
        RegistrationForm.fillRegistrationFormAndJoinCompany(userEmail, userPassword, companyName);
        RegistrationForm.clickRegisterButton();
        BaseCommandsToBrowser.openURL("http://mail.google.com/mail/h/");
        //GoogleMail.loginToGmail();
        GoogleMail.copyRegistrationLinkFromEmail();
        BaseCommandsToBrowser.openURL(BaseCommandsToBrowser.url);
        RegistrationForm.loginAfterOpeningConfirmationLink();
        MainApplicationNavigation.loginToSystem(userEmail, userPassword);

        //send Upgrade to PRO request
        MainApplicationNavigation.openCompanyProfile();
        GeneralInfoTab.clickUpgradeToPRO();
        GeneralInfoTab.agreeWithTermsAndConditions();
        GeneralInfoTab.closeInfoMessage();
        MainApplicationNavigation.logOut();

        //login to Admin Panel and approve Upgrade to PRO request with Pre-Paid type
        BaseCommandsToBrowser.openURL("http://autotest.sre.pp.ciklum.com/admin");
        MainAdminPanelNavigation.loginToAdminPanel();
        MainAdminPanelNavigation.openUpgradeToPRORequests();
        UpgradeToPROPage.acceptUpgradetoPRORequest("post-paid");

        //login to app and check company is Pre-Paid PRO
        BaseCommandsToBrowser.openURL("http://autotest.sre.pp.ciklum.com/login");
        MainApplicationNavigation.loginToSystem(userEmail, userPassword);
        MainApplicationNavigation.openCompanyProfile();
        Assert.assertTrue(source().contains("Advanced account"));
        MainApplicationNavigation.logOut();
    }

    @AfterSuite
    public void closeBrowser() {
        WebDriverRunner.getWebDriver().quit();
    }
}
