package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {


        public static WebDriver driver;
        public Logger logger;
        public Properties property;
        @BeforeClass(groups={"Sanity","Master"})
        @Parameters({"os","browser"})
        public void setup(String operating_system, String br) throws IOException {
            logger= LogManager.getLogger(this.getClass());
            FileInputStream file= new FileInputStream("src/test/resources/config.properties");
            property = new Properties();
            property.load(file);
            String app_url= property.getProperty("url");
            if(property.getProperty("execution_env").equalsIgnoreCase("local"))
            {
                switch (br.toLowerCase()) {
                    case "chrome":
                        driver = new ChromeDriver();
                        break;
                    case "safari":
                        driver = new SafariDriver();
                        break;
                    case "firefox":
                        driver=new FirefoxDriver();
                        break;
                    case "edge":
                        driver= new EdgeDriver();
                        break;
                    default:
                        System.out.println("invalid browser");
                        return;
                }
            }
            if(property.getProperty("execution_env").equalsIgnoreCase("remote"))
            {
                DesiredCapabilities capabilities=new DesiredCapabilities();
                //for operating system
                switch(operating_system.toLowerCase())
                {
                    case "mac":
                        capabilities.setPlatform(Platform.MAC);
                        break;
                    case "windows":
                        capabilities.setPlatform(Platform.WINDOWS);
                        break;
                    case "linux":
                        capabilities.setPlatform(Platform.LINUX);
                        break;
                    default:
                        System.out.println("No matching OS");
                        return;
                }
                switch(br.toLowerCase())
                {
                    case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                    case "safari":
                    capabilities.setBrowserName("safari");
                    break;
                    case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                    case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                    default:
                    /*System.out.println("No matching browser");
                        return;*/
                        throw new SkipException("Browser not supported on Grid");  //specifically saying testng to skip the test
                }
                driver= new RemoteWebDriver(new URL("http://localhost:4444"),capabilities);

            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(app_url);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
        }

        @AfterClass(groups={"Sanity","Regression","Master"})
        public void tearDown()
        {
            driver.quit();
        }

        public String randomString()
        {
            String randomstring = RandomStringUtils.randomAlphabetic(6);
            return randomstring;
        }

        public String randomNumber()
        {
            String randomnum = RandomStringUtils.randomNumeric(10);
            return randomnum;
        }
        public String randomAlphanumeric()
        {
            String randomstring = RandomStringUtils.randomAlphabetic(6);
            String randomnum = RandomStringUtils.randomNumeric(10);
            return(randomstring+randomnum);
        }
    public void resetToHomePageForDdtTests()
    {
        driver.get(property.getProperty("url"));
    }

    public static String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "/screenshots/"
                + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }

}


