package stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverUtils;
import utils.ScreenshotTestRule;


import java.io.BufferedWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class AgentPage  {



    public static String findAgentButton = "//header//a[@href='/en/find-agent']";
    public static String agentsHeaderOpen = "//h1[text()='Our top agents']";

    public static String languages = "//button[@type='button' and @class='ms-choice']/span[text()='Languages']";
    public static String languageDropDown = "//div[@class='ms-drop multiple']";
    public static String languageSelect = "//div[@class='ms-drop multiple']//li[contains(text(), '%s')]";
    public static String findButton = "//button[@type='submit']";
    public static String searchResults = "//div[@class='tile-block-area']";
    public static String header = "//h1[@class='serp-h1']";
    public static String nationality = "//button[@type='button' and @class='ms-choice']/span[text()='Nationality']";
    public static String natDropDown = "//div[@class='ms-drop ']";
    public static String nationalitySelect = "//div[@class='ms-drop ']//li[contains(text(), '%s')]";
    public static String natButtonNew = "//button[@type='button' and @class='ms-choice']/span[text()='India']";

    public static String agentDet = "//div[@class='tile-block-area']/div[@class='tile-block-container'][%s]";
    public static String agentContact = "//h3[text()='Contact this agent']";

    public static String aboutMeTab = "//button[@data-tab='aboutMe']";
    public static String aboutMeText = "//div[@class='user-tab user-tab-about-me active']";

    public static String agentName = "//h1[@class='user-name']";
    public static String agentNat = "//div[@class='user-nationality']/div[@class='content']";
    public static String agentLang = "//div[@class='user-language']/div[@class='content']";
    public static String agentLicense = "//div[@class='user-rera-no']/div[@class='content']";
    public static String agentExp = "//div[@class='user-experience']/div[@class='content']";
    public static String agentCompany = "//div[@class='company-name']";
    public static String agentPhone = "//div[@class='user-action-btns']/a[@data-lead='phone-button']";
    public static String agentListings = "//div[@class='user-active-listing']/div[@class='content']/a";
    public static String agentLinkedin = "//div[@class='user-linkedin']/div[@class='content']/a";
    public static String lang = "//div[@class='language-wrapper']/a[@class='ar change-language-link']";
    public static String arabic_header = "//h3[text()='تواصل مع الوكيل']";

    public int numberAgents1;
    public int numberAgents2;






    WebDriver driver;
    WebDriverWait wait;
    String homePage;

    @Rule
    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule(driver);

    @Before
    public void setup() throws MalformedURLException {
        String browser = "chrome";
        if (DriverUtils.getGridUrl() != null) {

            browser = DriverUtils.getBrowsersUnderTest().split(",")[0];
        }
        DriverUtils.startUp(browser);
        homePage = DriverUtils.getHomePage();

        driver = DriverUtils.getDriver();
        wait = DriverUtils.getWait();

    }

    @After
    public void tearDown() {
        DriverUtils.tearDown();
    }

    @Given("user is on Home Page")
    public void openHomePage() {
        driver.get(homePage);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(findAgentButton)));
    }

    @When("user click Find Agent button")
    public void clickFindAgent() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(findAgentButton)));
        WebElement clickFindAgentButton = driver.findElement(By.xpath(findAgentButton));
        clickFindAgentButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(agentsHeaderOpen)));
    }

    @When("user click Languages button")
    public void clickLanguagesButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(languages)));
        WebElement languageButton = driver.findElement(By.xpath(languages));
        languageButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(languageDropDown)));
    }

    @When("user select language (.)")
    public void selectLanguages(String language) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(languageDropDown)));
        String fullXpath = String.format(languageSelect, language);
        WebElement langSelect = driver.findElement(By.xpath(fullXpath));
        langSelect.click();
    }

    @When("user click Find button")
    public void clickFindButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(findButton)));
        WebElement find = driver.findElement(By.xpath(findButton));
        find.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResults)));
    }

    @Then("user store number of Agents first search")
    public int numberOfAgents() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(header)));
        WebElement headerText = driver.findElement(By.xpath(header));
        return numberAgents1 = Integer.valueOf(headerText.getText().split(" ")[0]);
    }

    @Then("user store number of Agents second search")
    public int numberOfAgentsSecond() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(header)));
        WebElement headerText = driver.findElement(By.xpath(header));
        return numberAgents2 = Integer.valueOf(headerText.getText().split(" ")[0]);
    }

    @When("^user click nationality$")
    public void clickNationality() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(nationality)));
        WebElement nationalityButton = driver.findElement(By.xpath(nationality));
        nationalityButton.click();
    }

    @When("^user select Nationality$")
    public void selectNationality(String nat) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(natDropDown)));
        String fullXpath = String.format(nationalitySelect, nat);
        WebElement natSelect = driver.findElement(By.xpath(fullXpath));
        natSelect.click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(natButtonNew)));
    }

    @Then("assert number of agents in first search is bigger then in second")
    public void assertAgents() {
        Assert.assertTrue(numberAgents1 > numberAgents2);
    }


    @When("^user click Agent to see Agent details$")
    public void clickAgent(Integer id) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResults)));
        String fullXpath = String.format(agentDet, id);
        WebElement agentSel = driver.findElement(By.xpath(fullXpath));
        agentSel.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(agentContact)));

        WebElement agentAbout = driver.findElement(By.xpath(aboutMeTab));
        agentAbout.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(aboutMeText)));


        Path path = Paths.get(String.format("%d-details.txt", System.currentTimeMillis()));

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(driver.findElement(By.xpath(agentName)).getText());
            writer.newLine();
            writer.write(driver.findElement(By.xpath(agentNat)).getText());
            writer.newLine();
            writer.write(driver.findElement(By.xpath(agentLang)).getText());
            writer.newLine();
            writer.write(driver.findElement(By.xpath(agentLicense)).getText());
            writer.newLine();
            writer.write(driver.findElement(By.xpath(aboutMeText)).getText());
            writer.newLine();
            writer.write(driver.findElement(By.xpath(agentPhone)).getAttribute("data-phone"));
            writer.newLine();
            writer.write(driver.findElement(By.xpath(agentCompany)).getText());
            writer.newLine();
            writer.write(driver.findElement(By.xpath(agentExp)).getText());
            writer.newLine();
            writer.write(driver.findElement(By.xpath(agentListings)).getText());
            writer.newLine();
            if (driver.findElement(By.xpath(agentLinkedin)).getAttribute("href") != null) {
                writer.write(driver.findElement(By.xpath(agentLinkedin)).getAttribute("href"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @When("^user change the language on a page$")
    public void changeLanguage() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lang)));
        WebElement language = driver.findElement(By.xpath(lang));
        language.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(arabic_header)));
    }


}



