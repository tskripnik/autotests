package ExamplePageObject.AdminPanel;

import ExamplePageObject.BaseCommandsToBrowser;
import static com.codeborne.selenide.Selenide.$;

public class MainAdminPanelNavigation extends BaseCommandsToBrowser{
    public static void loginToAdminPanel() {
        $("#_username").setValue("test@example.com");
        $("#_password").setValue("test");
        $(".btn.btn-sm.btn-success").click();
    }

    public static void openCompanyList() {
        driver.get("http://autotest.sre.pp.ciklum.com/admin/sre/company/company-company/list");
    }

    public static void openBankTransferRequests() {
        driver.get("http://autotest.sre.pp.ciklum.com/admin/sre/companybalance/transaction/list");
    }

    public static void openUpgradeToPRORequests() {
        driver.get("http://autotest.sre.pp.ciklum.com/admin/sre/userrequest/upgrade_to_pro/list");
    }
}
