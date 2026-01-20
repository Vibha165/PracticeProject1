package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath="//*[@title='My Account']")
    WebElement account_dropdown;

    @FindBy(xpath="//*[.='Register']")
    WebElement register_button;

    @FindBy(xpath="//a[.='Login']")
    WebElement login_button;

    public void accountDropdown()
    {
        account_dropdown.click();
    }

    public void clickRegisterButton()
    {
        register_button.click();
    }
    public void clickLoginButton()
    {
        login_button.click();
    }
}
