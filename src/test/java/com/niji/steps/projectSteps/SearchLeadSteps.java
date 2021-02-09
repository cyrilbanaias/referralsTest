package com.niji.steps.projectSteps;

import com.niji.data.DataManager;
import com.niji.factory.DriverManager;
import com.niji.pageObjects.MenuLinks;
import com.niji.pageObjects.SearchLeadPage;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.steps.globalSteps.GenericSteps;
import com.niji.utils.Traduction;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;


public class SearchLeadSteps {

    @When("I open the search lead page")
    public void openSearchLeadPage(){
        MenuLinks.getLeadCreationMenuDropdown().click();
        MenuLinks.getSearchLeadMenuLink().click();
        new WebDriverWait(DriverManager.getDriver().driver, 10).until(ExpectedConditions.urlContains("lead-follow"));
    }

    @When("I search for an existing company number")
    public void searchForExistingCompany() throws Exception{
        DriverManager.getDriver().scenario.write(DataManager.getData().existing_client);
        SearchLeadPage.getGlobalFilterField().sendKeys(DataManager.getData().existing_client);
        SearchLeadPage.getSearchBurron().click();
        Thread.sleep(5000);
    }

    @When("I search for (.+) status")
    public void searchForStatus(String status) throws Exception{
        DataManager.getData().searchStatus = status;
        SearchLeadPage.getMoreCriteriaLink().click();
        SearchLeadPage.getStatusButton(Traduction.getLeadStatusTraduction(status)).click();
        SearchLeadPage.getSearchBurron().click();
        Thread.sleep(5000);
    }

    @When("all the results are from this company")
    public void checkResultsCompany() throws Exception{
        List<WebElement> resultsLines = SearchLeadPage.getResultsLines();
        ElementSteps.makeVisible(resultsLines.get(0), true);
        GenericSteps.screenShot();
        for (WebElement resultLine:resultsLines){
            List<WebElement> tds = resultLine.findElements(By.xpath(".//td"));
            String lineValues = "";
            for (WebElement td:tds){
                lineValues = lineValues + "|" + td.getText();
            }
            DataManager.getData().errorCollector.checkThat("One line does not contains the existing client number used.",
                    lineValues, containsString(DataManager.getData().existing_client));
        }
    }

    @When("all the results have the searched status")
    public void checkResultsStatus() throws Exception{
        List<WebElement> resultsLines = SearchLeadPage.getResultsLines();
        ElementSteps.makeVisible(resultsLines.get(0), true);
        GenericSteps.screenShot();
        for (WebElement resultLine:resultsLines){
            List<WebElement> tds = resultLine.findElements(By.xpath(".//td"));
            String lineValues = "";
            for (WebElement td:tds){
                lineValues = lineValues + "|" + td.getText();
            }
            DataManager.getData().errorCollector.checkThat("One line does not contains the searched status.",
                    lineValues, containsString(Traduction.getLeadStatusTraduction(DataManager.getData().searchStatus).toUpperCase()));
        }
    }
}