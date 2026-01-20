package testCases;

import Utilities.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import testBase.BaseClass;

public class Login_DDT extends BaseClass
{
    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class)
    public void DDT_Login(String email, String pwd, String exp) throws InterruptedException
    {
        resetToHomePageForDdtTests();
        logger.info("*** starting test execution ***");
        HomePage hp= new HomePage(driver);
        hp.accountDropdown();
        hp.clickLoginButton();

        LoginPage lp= new LoginPage(driver);
        lp.setEmail(email);
        lp.setPassword(pwd);
        lp.clickLogin();

        /* valid data - login successful - test passed - logout
                      - login unsuccessful - test failed

           invalid data - login successful - test failed - logout
                        - login unsuccessful - test passed
         */

        if(exp.equalsIgnoreCase("Valid"))
        {
            Thread.sleep(2000);
            if (lp.message().equals("My Account"))
            {
                lp.logout_button();
                Thread.sleep(2000);
                logger.info("Login successful for valid data");
                Assert.assertTrue(true);
            }
            else
            {
                logger.info("Login unsuccessful for valid data");
                Assert.fail("Login unsuccessful for valid data");
            }
        }
        else
        {
            if (lp.message().equals("My Account"))
            {
                lp.logout_button();
                Assert.fail("Login successful for invalid data");
            }
            else
            {
                logger.info("Login unsuccessful for invalid data");
                Assert.assertTrue(true);

            }
        }

    }
}
