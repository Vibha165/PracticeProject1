package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath="//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath="//input[@id='input-password']")
    WebElement password;

    @FindBy(xpath="//input[@type='submit']")
    WebElement login_button;

    @FindBy(xpath = "//div[@id='content']//h2[1]")
    WebElement confirmation_message;

    @FindBy(xpath="//div[@class='list-group']//a[.='Logout']")
    WebElement logout;

    public void setEmail(String email_id)
    {
        email.sendKeys(email_id);
    }
    public void setPassword(String pwd)
    {
        password.sendKeys(pwd);
    }
    public void clickLogin()
    {
        login_button.click();
    }

    public String message()
    {
        return confirmation_message.getText();
    }

    public void clearEmail()
    {
        email.clear();
    }
    public void clearPassword()
    {
        password.clear();
    }

    public void logout_button()
    {
        logout.click();
    }
}
