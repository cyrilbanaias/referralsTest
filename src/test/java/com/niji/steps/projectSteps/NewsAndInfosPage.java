package com.niji.steps.projectSteps;

import com.niji.data.DataManager;
import com.niji.factory.DriverManager;
import com.niji.pageObjects.InsertsElements;
import com.niji.pageObjects.MenuLinks;
import com.niji.steps.globalSteps.GenericSteps;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewsAndInfosPage {

    @When("news and infos page is available")
    public void checkPagePresence(){
        try {
            MenuLinks.getNewsAndInfosMenuLink();
        } catch (Exception e){
            DataManager.getData().errorCollector.addError(new Throwable("News & infos menu link is not present"));
        }
    }

    @When("news and infos page is not available")
    public void checkPageAbsence(){
        try {
            MenuLinks.getNewsAndInfosMenuLink();
            DataManager.getData().errorCollector.addError(new Throwable("News & infos menu link is present"));
        } catch (Exception e){
        }
    }

    @When("I open the news and infos page")
    public void openLeadForm(){
        MenuLinks.getNewsAndInfosMenuLink().click();
        new WebDriverWait(DriverManager.getDriver().driver, 10).until(ExpectedConditions.urlContains("news-and-information"));
    }

    @When("the insert is displayed on the news and infos body page")
    public void checkInsertOnBodyPage() throws Exception{
        // Check title, subtitle, description, link text and link href
        try {
            WebElement block = InsertsElements.getBodyInsertBlock();
            InsertsElements.getInsertTitle(block, DataManager.getData().insertionName);
            InsertsElements.getInsertSubtitle(block, DataManager.getData().insertionName);
            InsertsElements.getInsertDescription(block, DataManager.getData().insertionName);
            InsertsElements.getInsertImage(block, DataManager.getData().insertionName);
            InsertsElements.getInsertAction(block, DataManager.getData().insertionName);
            InsertsElements.getInsertActionURL(block, DataManager.getData().insertionName);
        } catch (Exception e){
            GenericSteps.screenShot();
            throw new Exception("One or more elements of the insert is not displayed/present");
        }
    }

    @When("the insert is displayed on the news and infos column")
    public void checkInsertOnColumn() throws Exception{
        // Check title, subtitle, description, link text and link href
        try {
            WebElement block = InsertsElements.getColumnInsertBlock();
            InsertsElements.getInsertTitle(block, DataManager.getData().insertionName);
            InsertsElements.getInsertSubtitle(block, DataManager.getData().insertionName);
            InsertsElements.getInsertDescription(block, DataManager.getData().insertionName);
            InsertsElements.getInsertImage(block, DataManager.getData().insertionName);
            InsertsElements.getInsertAction(block, DataManager.getData().insertionName);
            InsertsElements.getInsertActionURL(block, DataManager.getData().insertionName);
        } catch (Exception e){
            GenericSteps.screenShot();
            throw new Exception("One or more elements of the insert is not displayed/present");
        }
    }
}
