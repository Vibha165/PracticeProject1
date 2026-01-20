package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    //a parent constructor which will be invoked or called by all
    // the constructors in other page object classes

     WebDriver driver;
       public BasePage(WebDriver driver)
       {
          this.driver=driver;
          PageFactory.initElements(driver,this);
       }

}
