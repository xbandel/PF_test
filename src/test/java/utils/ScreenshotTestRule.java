package utils;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.DriverUtils;


import java.io.File;
import java.io.FileOutputStream;

public class ScreenshotTestRule implements MethodRule {
    public ScreenshotTestRule(WebDriver driver) {
        this.driver = driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Throwable t) {
                    DriverUtils.captureScreenshot(frameworkMethod.getName());
                    throw t;
                } finally {
                   /* if(driver != null) {
                        driver.close();
                        driver.quit();
                    }*/

                }
            }

        };
    }
}
