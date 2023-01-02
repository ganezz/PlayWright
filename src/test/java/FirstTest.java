import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

import javax.sql.rowset.serial.SerialJavaObject;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.util.*;

public class FirstTest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setIgnoreHTTPSErrors(true));

            Page page = browser.newPage();


            page.navigate("https://192.168.1.112:8443/BankAdmin/");
            page.locator("[id=Admin__Login__userId]").fill("admin");
//            page.pause();
            page.locator("[id=Admin__Login__pswd]").fill("Admin@123");
            page.locator("[id=Admin__Login__element_button_1]").click();
            page.locator("[id=Admin__Dashboard__el_txt_1_txtcnt]").waitFor();
            assertThat(page.locator("[id=Admin__Dashboard__el_txt_1_txtcnt]")).containsText("Dashboard");
            // Press Enter
            // page.waitForNavigation(new Page.WaitForNavigationOptions().setUrl("https://accounts.google.com/signin/v2/deniedsigninrejected?hl=en&passive=true&continue=https%3A%2F%2Fwww.google.com%2F%3Fgws_rd%3Dssl&ec=GAZAmgQ&flowName=GlifWebSignIn&flowEntry=ServiceLogin"), () ->
            page.waitForNavigation(() -> {
                page.locator("[id=]").press("Enter");
            });
        }
    }
}

