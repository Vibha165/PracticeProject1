package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import testBase.BaseClass;

public class TC2_Login extends BaseClass {

    @Test(groups={"Regression","Master"})
     public void doingLogin() throws InterruptedException {

            logger.info("*** starting test execution***");
            HomePage hp = new HomePage(driver);
            hp.accountDropdown();
            hp.clickLoginButton();

            LoginPage login = new LoginPage(driver);
            login.clearEmail();
            login.setEmail(property.getProperty("email"));
            login.clearPassword();
            login.setPassword(property.getProperty("password"));
            login.clickLogin();
            Thread.sleep(2000);

            if (login.message().equals("My Account")) {
                Assert.assertTrue(true);
            } else {
                logger.error("test failed due to this error message {} :", login.message());
                Assert.fail("Login failed. Actual message: " + login.message());
            }

            //or

           /* Assert.assertEquals(login.message(), "My Account",
                    "Login failed. Actual message: " + login.message());*/

          // use listeners to catch the error or exception instead of catch block

    }
}
