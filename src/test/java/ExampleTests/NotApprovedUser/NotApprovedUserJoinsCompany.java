package ExampleTests.NotApprovedUser;

import ExamplePageObject.*;
import ExamplePageObject.SREApplication.*;
import ExamplePageObject.SREApplication.CompanyProfile.GeneralInfoTab;
import ExamplePageObject.SREApplication.CompanyProfile.JoinRequestsTab;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

import static com.codeborne.selenide.WebDriverRunner.source;

public class NotApprovedUserJoinsCompany {
    private static String companyAdminEmail = "unique.tester.mail@gmail.com";
    private static String password = "1qaz@WSX";
    private static int uniqueValue = Math.abs((int) new Date().getTime());
    private static String userEmail = "unique.tester.mail+" + uniqueValue + "@gmail.com";

    @Test
    public void CompanyAdminAcceptsJoinRequest() throws Exception {
        BaseCommandsToBrowser.openURL("http://autotest.sre.pp.ciklum.com/login");
        MainApplicationNavigation.loginToSystem(companyAdminEmail, password);
        MainApplicationNavigation.openCompanyProfile();
        JoinRequestsTab.openJoinRequestsTab();
        JoinRequestsTab.acceptJoinRequest();
        Thread.sleep(100);
        Assert.assertTrue(PermissionsPage.checkIfNoPermissionRoleSelected());
        MainApplicationNavigation.logOut();
    }

    @Test
    public void JoinCompanyPageUserReSendsJoinRequest() throws Exception {
        String companyName = "Ciklum";

        //register new User
        BaseCommandsToBrowser.openURL("http://autotest.sre.pp.ciklum.com/registration");
        RegistrationForm.fillRegistrationFormAndJoinCompany(userEmail, password, companyName);
        RegistrationForm.clickRegisterButton();
        BaseCommandsToBrowser.openURL("http://mail.google.com/mail/h/");
        GoogleMail.copyRegistrationLinkFromEmail();
        BaseCommandsToBrowser.openURL(BaseCommandsToBrowser.url);
        RegistrationForm.loginAfterOpeningConfirmationLink();

        //as Company Admin reject join request
        MainApplicationNavigation.loginToSystem(companyAdminEmail, password);
        MainApplicationNavigation.openCompanyProfile();
        JoinRequestsTab.openJoinRequestsTab();
        JoinRequestsTab.rejectJoinRequest();
        MainApplicationNavigation.logOut();

        //as User Resend Join Request
        MainApplicationNavigation.loginToSystem(userEmail, password);
        //check if Join Company Page is present for Not Approved User
        boolean joinCompanyPagePresent = source().contains("Join Company");
        Assert.assertEquals(joinCompanyPagePresent, true);

        MainApplicationNavigation.openJoinCompanyPage();
        Assert.assertTrue(JoinCompanyPage.checkIfStatusIsRejected());
        JoinCompanyPage.resendJoinRequest();
        Thread.sleep(100);
        Assert.assertTrue(JoinCompanyPage.checkIfStatusIsOpened());
        MainApplicationNavigation.logOut();
    }

    @Test
    public void UserReSentJoinRequestIsAcceptedByCompanyAdmin() throws Exception {
        //as Company Admin accept join request
        BaseCommandsToBrowser.openURL("http://autotest.sre.pp.ciklum.com/login");
        MainApplicationNavigation.loginToSystem(companyAdminEmail, password);
        MainApplicationNavigation.openCompanyProfile();
        JoinRequestsTab.openJoinRequestsTab();
        JoinRequestsTab.acceptJoinRequest();
        MainApplicationNavigation.logOut();

        //as User check joining Company
        MainApplicationNavigation.loginToSystem(userEmail, password);
        //check if Join Company Page is absent for Approved User
        boolean joinCompanyPagePresent = source().contains("Join Company");
        Assert.assertEquals(joinCompanyPagePresent, false);
        MainApplicationNavigation.logOut();
    }
}
