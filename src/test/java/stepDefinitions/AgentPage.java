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



    public static String findAgentButton = "//nav[@class='header-nav']//a[contains(text(), 'Find agent')]";
    public static String agentsHeaderOpen = "//h1[text()='Our top agents']";

    public static String languages = "//div[text()='Languages']";
    public static String languageSelect = "//div[text()='%s']";
    public static String arLang = "div[text()='Arabic']";
    public static String findButton = "//button[text()='Find']";
    public static String searchResults = "//div[@data-qs='agent-list']";
    public static String header = "//h1[@class='title']";
    public static String nationality = "//div[text()='Nationality']";
    public static String nationalitySelect = "//div[text()='%s']";
    public static String indNat = "div[text()='India']";
    public static String natButtonNew = "//div[@class='searchform_column searchform_column-serp']//div[text()='India']";

    public static String agentDet = "(//div[@class='tile_content'])[%s]";
    public static String agentContact = "//h3[text()='Contact this agent']";

    public static String aboutMeTab = "//a[@href='#tab-about']";
    public static String aboutMeText = "//div[@data-qs-content='tab-about']";

    public static String agentName = "//h1[@class='title title-size1 bioinfo_name']";
    public static String agentNat = "//span[text()='Nationality:']//following-sibling::span[@class='table_column']";
    public static String agentLang = "//span[text()='Languages:']//following-sibling::span[@class='table_column']";
    public static String agentLicense = "//span[text()='License No.:']//following-sibling::span[@class='table_column']";
    public static String agentExp = "//span[text()='Experience since:']//following-sibling::span[@class='table_column']";
    public static String agentCompany = "//div[@class='brokerthumbnail_text']/p[1]";
    public static String agentPhone = "//a[@data-qs='phone-button']";
    public static String agentListings = "//a[@href='#tab-properties']";
    public static String agentLinkedin = "//a[@class='link' and contains(text(), 'View profile')]";
    public static String lang = "//header//div[@class='globalswitch_language']/a";
    public static String arabic_header = "//h3[text()='تواصل مع الوكيل']";

    public int numberAgents1;
    public int numberAgents2;






    WebDriver driver;
    WebDriverWait wait;
    String homePage;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

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
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(arLang)));
    }

    @When("user select language (.)")
    public void selectLanguages(String language) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(arLang)));
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
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(indNat)));
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
            writer.write(driver.findElement(By.xpath(agentPhone)).getAttribute("href"));
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



