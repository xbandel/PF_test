import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class VillaSearchTest extends Base {

    public static String homePageURL = "https://www.propertyfinder.qa/";

    String itemTitle;
    String itemPrice;

    /*public VillaSearchTest(String browser) {
        super(browser);
    }
*/
    @Test
    public void villaSearchTest(){
        driver.get(homePageURL);
        SearchProperty searchProperty = new SearchProperty(driver);
        searchProperty.openHomePage();
        searchProperty.selectSearchParameters();
        searchProperty.submitSearch();
        searchProperty.sortByPrice();
        List<SearchProperty.TitlePrice> tpl = searchProperty.searchResults();
        toCsv(tpl);

    }





    public void toCsv(List<SearchProperty.TitlePrice> villas){
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(String.format("%d-villas.csv", System.currentTimeMillis())));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("Title", "Price"));
        ) {
           for(SearchProperty.TitlePrice tp : villas) {
               csvPrinter.printRecord(tp.title, tp.price);
           }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
