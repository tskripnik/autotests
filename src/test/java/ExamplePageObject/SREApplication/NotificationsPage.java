package ExamplePageObject.SREApplication;

import ExamplePageObject.BaseCommandsToBrowser;
import org.testng.Assert;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.source;

public class NotificationsPage extends BaseCommandsToBrowser {
    public static void notificationAppearanceCheck(String notificationText) {
        boolean notificationAppearanceCheck = source().contains(notificationText);
        Assert.assertTrue(notificationAppearanceCheck);
    }
    public static void removeNotification() {
        $(".icon.icon-table.icon-round.icon-reject_deal").click();
    }
}
