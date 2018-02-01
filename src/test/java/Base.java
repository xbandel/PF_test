import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.DriverUtils;
import utils.ScreenshotTestRule;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)

public class Base {

    public Base() {
    }

    @Rule
    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule(driver);

    protected static WebDriver driver;

    static {
        System.setProperty("webdriver.chrome.driver", "/home/xenia/chrdriver/chromedriver");
         driver = new ChromeDriver();
    }

    private String browser;

    public Base(String browser) {
        this.browser = browser;
    }

    static private String gridUrl;
    static private String browsersUnderTest;
    static private String homePageURL;
    static {
        gridUrl = DriverUtils.getGridUrl();
        browsersUnderTest = System.getProperty("test.selenium.browsers", "chrome");
        homePageURL = DriverUtils.getHomePage();
    }


    @Parameterized.Parameters
    public static Collection data() {

        if (gridUrl == null) {
            return Arrays.asList(new Object[][]{{"chrome"}});
        } else {
            return Arrays.asList(browsersUnderTest.split(","));
        }
    }

    @Before
    public void setUp() throws MalformedURLException {
        DriverUtils.startUp(browser);
        screenshotTestRule.setDriver(driver);
    }

    @After
    public void teardown() {
       DriverUtils.tearDown();
    }

}
