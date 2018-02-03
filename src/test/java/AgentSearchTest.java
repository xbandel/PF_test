import org.junit.Assert;
import org.junit.Test;
import stepDefinitions.AgentPage;
import utils.DriverUtils;

public class AgentSearchTest extends Base{

    public static String homePageURL = "https://www.propertyfinder.ae/";

    public AgentSearchTest(String browser) {
        super(browser);
    }

    @Test
    public void selectAgentLanguagesNationality() throws InterruptedException {
        driver.get(homePageURL);
        AgentPage agentPage = new AgentPage();
        agentPage.setDriver(DriverUtils.getDriver());
        agentPage.setWait(DriverUtils.getWait());
        agentPage.clickFindAgent();
        agentPage.clickLanguagesButton();
        agentPage.selectLanguages("Hindi");
        agentPage.selectLanguages("English");
        agentPage.selectLanguages("Arabic");
        agentPage.clickFindButton();
        int agents1 = agentPage.numberOfAgents();
        System.out.println(agents1);
        agentPage.clickNationality();
        agentPage.selectNationality("India");
        int agents2 = agentPage.numberOfAgents();
        System.out.println(agents2);
        Assert.assertTrue(agents1 > agents2);

    }

    @Test
    public void storeAgentDetails(){
        driver.get(homePageURL);
        AgentPage agentPage = new AgentPage();
        agentPage.clickFindAgent();
        agentPage.clickAgent(1);
        DriverUtils.captureScreenshot("english");
        agentPage.changeLanguage();
        DriverUtils.captureScreenshot("arabic");
    }


}
