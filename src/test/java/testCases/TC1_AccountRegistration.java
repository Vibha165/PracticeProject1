package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.RegistrationForm;
import testBase.BaseClass;

public class TC1_AccountRegistration extends BaseClass {


    @Test(groups={"Sanity","Master"})
    public void accountRegistration() {
        try {
            logger.info("*** starting the test ***");
            HomePage hp = new HomePage(driver);
            hp.accountDropdown();
            logger.info("*** clicked on account dropdown ***");
            hp.clickRegisterButton();
            Thread.sleep(2000);
            logger.info("*** clicked on register button ***");

            RegistrationForm form = new RegistrationForm(driver);
            logger.info("*** starting to fill the register form ***");

            form.setFirstName(randomString());
            form.setLastName(randomString());
            form.setEmail(randomString() + "@gmail.com");
            form.setTelephone(randomNumber());

            String password = randomAlphanumeric();
            form.setPassword(password);
            form.confirmPassword(password);
            form.clickTncCheckbox();
            form.continueButton();
            Thread.sleep(2000);
            if (form.confirmationMessage().equals("Your Account Has Been Created!"))
            {
                Assert.assertTrue(true);
            } else
            {
                logger.error("** test failed **");
                logger.debug("** debugging started **");
                logger.debug("Message appearing after the test failed - " +form.confirmationMessage());
                Thread.sleep(2000);
                Assert.assertTrue(false);
            }


        } catch (Exception e) {
            logger.error("Test failed due to exception", e);
            Assert.fail();
        }

        logger.info("*** finished account registration ***");


    }
}
