package gemini.tests;

import gemini.pages.Client_Registration_page;
import gemini.pages.Login_in_to_Gemini_page;
import gemini.utils.Brow_Util;
import gemini.utils.ConfigurationReader;
import gemini.utils.Driver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class LoginToGemini {
    Login_in_to_Gemini_page geminiLog = new Login_in_to_Gemini_page();

    @BeforeTest
    public void openLink(){
        String link = ConfigurationReader.getProperty("gemini_link");
        Driver.getDriver().get(link);
    }


    @Test (priority = 1)
    public void login_positive()  {
       geminiLog.getToClientRegistrationPage();
       Brow_Util.waitUntilVis(geminiLog.getElementClientPage(Client_Registration_page.BUSINESS_NAME_INPUT));
       geminiLog.creat_account_client_Registration_Positive();

       Brow_Util.wait.until(ExpectedConditions.urlContains("thanks"));
       String exp_url = "https://exchange.sandbox.gemini.com/register/institution/thanks";
       String act_url = Driver.getDriver().getCurrentUrl();
       Assert.assertEquals(exp_url,act_url);
    }

    @Test (priority = 2)
    public void login_negative()  {
        geminiLog.getToClientRegistrationPage();
        Brow_Util.waitUntilVis(geminiLog.getElementClientPage(Client_Registration_page.BUSINESS_NAME_INPUT));
        geminiLog.creat_account_client_Registration_Negative();

        Assert.assertTrue(geminiLog.getElementClientPage(Client_Registration_page.ALERT).isDisplayed());
    }



    @AfterTest
    public void tearDown() throws InterruptedException {
        Driver.closeDriver();
    }


}
