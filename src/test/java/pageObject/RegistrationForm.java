package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationForm extends BasePage {

    public RegistrationForm(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(id="input-firstname")
    WebElement first_name;

    @FindBy(id="input-lastname")
    WebElement last_name;

    @FindBy(id="input-email")
    WebElement email;

    @FindBy(id="input-telephone")
    WebElement telephone;

    @FindBy(id="input-password")
    WebElement password;

    @FindBy(id="input-confirm")
    WebElement confirm_password;

    @FindBy(xpath="//*[@name='agree']")
    WebElement tnc_checkbox;

    @FindBy(xpath="//*[@type='submit']")
    WebElement continue_button;

    @FindBy(xpath="//div[@id='content']//h1")
    WebElement confirmation_msg;

    public void setFirstName(String fname)
    {
        first_name.sendKeys(fname);
    }

    public void setLastName(String lname)
    {
        last_name.sendKeys(lname);
    }

    public void setEmail(String emailId)
    {
        email.sendKeys(emailId);
    }

    public void setTelephone(String phone)
    {
        telephone.sendKeys(phone);
    }
    public void setPassword(String pwd)
    {
        password.sendKeys(pwd);
    }
    public void confirmPassword(String confirm_pwd)
    {
        confirm_password.sendKeys(confirm_pwd);
    }
    public void clickTncCheckbox()
    {
        tnc_checkbox.click();
    }
    public void continueButton()
    {
        continue_button.click();
    }

    public String confirmationMessage()
    {
        try
        {
            return (confirmation_msg.getText());
        }
        catch (Exception e)
        {
            return (e.getMessage());
        }
    }

}

