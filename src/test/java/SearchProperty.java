import com.google.common.collect.Lists;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedList;
import java.util.List;

public class SearchProperty {


    public SearchProperty(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public static String countrySelect = "//div[@class='desktop-country-language-select']";
    public static String propType = "//button[@type='button' and @class='ms-choice']/span[text()='Rent']";
    public static String propTypeDropdownBuy = "//li[text()='Buy']";

    public static String minBed = "//button[@type='button' and @class='ms-choice']/span[text()='Min. bed']";
    public static String bedDropdown3 = "//li[@data-value='3' and text()='3 Beds']";
    public static String maxBed = "//button[@type='button' and @class='ms-choice']/span[text()='Max. bed']";
    public static String bedDropdown7 = "//ul/li[text()='7 Beds']";


    public static String searchField = "//input[@type='search']";
    public static String searchArea = "THE PEARL";
    public static String searchBut = "//button[@type='submit']/div[@class='text']";

    public static String propertyType = "//button[@type='button' and @class='ms-choice']/span[text()='Property type']";
    public static String propertytypeVilla = "//li[@data-value='35' and text()='Villa']";
    public static String searchResults = "//section[@id='serp']";

    public static String sortField = "//button[@type='button' and @class='ms-choice']/span[text()='Featured']";
    public static String sortPriceHigh = "//li[contains(text(), 'Price (high)')]";

    public static String resultsNode = "//ul[@class='serp-result ts-property-list-serp-view']/li";


    public static String propertyTitle = "//li//a[@title]";
    public static String propertyPrice = "//li//div[@class='info-area']//span[@class='val']";

    private  WebDriver driver;
    WebDriverWait wait;

    private void selectAndClick(String placeholder, String selection) {
        WebElement propTypeClick = driver.findElement(By.xpath(placeholder));
        propTypeClick.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(selection)));
        WebElement propTypeRent = driver.findElement(By.xpath(selection));
        Actions actions = new Actions(driver);
        actions.moveToElement(propTypeRent).click().build().perform();

    }

    @Given("^user is on Home Page$")
    public void openHomePage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(countrySelect)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(propType)));
    }

    public void selectPropertyType(String propType){



    }

    @When("^user enter Villa Search Parameters$")
    public void selectSearchParameters(){
        selectAndClick(propType, propTypeDropdownBuy);
        selectAndClick(minBed, bedDropdown3);
        selectAndClick(maxBed, bedDropdown7);
        selectAndClick(propertyType, propertytypeVilla);
        WebElement search = driver.findElement(By.xpath(searchField));
        search.sendKeys(searchArea);
    }

    @When("^user submit Search$")
    public void submitSearch(){
        WebElement searchButton = driver.findElement(By.xpath(searchBut));
        searchButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResults)));
    }

    @When("^sort by price High to Low$")
    public void sortByPrice(){
        WebElement sortButton = driver.findElement(By.xpath(sortField));
        sortButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sortPriceHigh)));
        WebElement sortPrice = driver.findElement(By.xpath(sortPriceHigh));
        sortPrice.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResults)));
    }


    public static class TitlePrice{
        String title;
        String price;

        public TitlePrice(String title, String price) {
            this.title = title;
            this.price = price;
        }
    }
    public List<TitlePrice> searchResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResults)));

        List<WebElement> tl =driver.findElements(By.xpath(propertyTitle));
        List<WebElement> pl =driver.findElements(By.xpath(propertyPrice));

        List<TitlePrice> ret = Lists.newLinkedList();
        for(int i = 0; i < tl.size(); ++i){
            TitlePrice tp = new TitlePrice(
                    tl.get(i).getAttribute("title"),
                    pl.get(i).getText()
            );
            ret.add(tp);
        }
        return ret;
    }



}
