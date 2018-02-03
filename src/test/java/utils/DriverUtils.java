package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverUtils {

   public static WebDriver driver;
   public static WebDriverWait wait;
   public static String homePageURL;
    static private String gridUrl;
    static private String browsersUnderTest;

    public static String getBrowsersUnderTest() {
        return browsersUnderTest;
    }

    public static String getGridUrl() {
        return gridUrl;
    }

    static {
            gridUrl = System.getProperty("test.selenium.hub.url", "http://127.0.0.2:4400/wd/hub");
        browsersUnderTest = System.getProperty("test.selenium.browsers", "chrome");
        homePageURL = System.getProperty("test.selenium.homepage", "https://www.propertyfinder.ae/");
    }
    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static String getHomePage() {
        return homePageURL;
    }

    public static void startUp(String browser) throws MalformedURLException {


                if (gridUrl == null) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        } else {

            DesiredCapabilities capabilities = new DesiredCapabilities(new DesiredCapabilities(browser, "", Platform.ANY));
            driver = new RemoteWebDriver(new URL(gridUrl), capabilities);

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        wait = new WebDriverWait(driver, 120);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    public static void tearDown(){
        driver.quit();
    }

    public static void captureScreenshot(String fileName) {
        try {
            new File("target/surefire-reports/").mkdirs();
            String filePath = "target/surefire-reports/screenshot-" + fileName;
            if (driver instanceof RemoteWebDriver) {
                filePath = filePath + "-" + ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
            }
            filePath = filePath + ".png";

            FileOutputStream out = new FileOutputStream(filePath);
            System.out.println("[[ATTACHMENT|" + System.getProperty("user.dir") + "/" + filePath + "]]");
            WebDriver underlyingDriver = driver;

            WebDriver augmentedDriver = new Augmenter().augment(underlyingDriver);

            out.write(((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES));
            out.close();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

}
