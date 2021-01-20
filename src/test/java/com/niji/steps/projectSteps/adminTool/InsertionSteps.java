package com.niji.steps.projectSteps.adminTool;

import com.niji.data.DataManager;
import com.niji.factory.DriverManager;
import com.niji.pageObjects.LeadCreationPage;
import com.niji.pageObjects.adminTools.InsertionPage;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.steps.globalSteps.GenericSteps;
import cucumber.api.java.en.When;
import cucumber.api.java.sl.In;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.reporters.TestHTMLReporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsertionSteps {

    @When("I open the insertion page")
    public void openInsertionPage(){
        ElementSteps.makeVisible(InsertionPage.getInsertionMenuLink(), false);
        InsertionPage.getInsertionMenuLink().click();
    }

    @When("I remove all the existing inserts")
    public void deleteAllInserts() throws Exception{
        List<WebElement> modifyButtons = InsertionPage.getModifyButtons();
        while(modifyButtons.size() > 0){
            ElementSteps.makeVisible(modifyButtons.get(0), false);
            modifyButtons.get(0).click();
            ElementSteps.makeVisible(InsertionPage.getSaveButton(), false);
            //Unselect all pages
            if (InsertionPage.getHomePageSwitchButton().findElement(By.xpath("./..")).getAttribute("aria-checked").equals("true")){
                InsertionPage.getHomePageSwitchButton().click();
                Thread.sleep(500);
            }
            if (InsertionPage.getNewsBodyPageSwitchButton().findElement(By.xpath("./..")).getAttribute("aria-checked").equals("true")){
                InsertionPage.getNewsBodyPageSwitchButton().click();
                Thread.sleep(500);
            }
            if (InsertionPage.getNewsColumnPageSwitchButton().findElement(By.xpath("./..")).getAttribute("aria-checked").equals("true")){
                InsertionPage.getNewsColumnPageSwitchButton().click();
                Thread.sleep(500);
            }
            if (InsertionPage.getEnterLeadPageSwtichButton().findElement(By.xpath("./..")).getAttribute("aria-checked").equals("true")){
                InsertionPage.getEnterLeadPageSwtichButton().click();
                Thread.sleep(500);
            }
            Thread.sleep(1000);
            InsertionPage.getSaveButton().click();
            try {
                InsertionPage.getConfirmPopopButton().click();
            } catch (Exception e){

            }
            Thread.sleep(1000);
            modifyButtons = InsertionPage.getModifyButtons();
        }
    }

    @When("I activate the news and infos page")
    public void activateNewsInfosPage() throws Exception{
        WebElement switchButton = InsertionPage.getActivateNewsPageButton();
        if (switchButton.findElement(By.xpath("./..")).getAttribute("aria-checked").equals("false")){
            ElementSteps.makeVisible(switchButton, false);
            switchButton.click();
            Thread.sleep(500);
        }
    }

    @When("I deactivate the news and infos page")
    public void deactivateNewsInfosPage() throws Exception{
        WebElement switchButton = InsertionPage.getActivateNewsPageButton();
        if (switchButton.findElement(By.xpath("./..")).getAttribute("aria-checked").equals("true")){
            ElementSteps.makeVisible(switchButton, false);
            switchButton.click();
            Thread.sleep(500);
        }
    }

    public static void fillInsertForm(List<String> list) throws Exception{
        DataManager.getData().insertionName = "";
        Actions action = new Actions(DriverManager.getDriver().driver);

        ElementSteps.makeVisible(InsertionPage.getCreateInsertionButton(), false);
        InsertionPage.getCreateInsertionButton().click();

        if (list.get(0).equals("Custom")){
            ElementSteps.waitForVisibilityOfElement(InsertionPage.getCustomRadioButton(), 5);
            InsertionPage.getCustomRadioButton();
        }

        if(list.get(1).equals("No popup") && InsertionPage.getPopupSwitchButton().findElement(By.xpath("./..")).getAttribute("aria-checked").equals("true")) {
            InsertionPage.getPopupSwitchButton().click();
        } else if (list.get(1).equals("Popup") && InsertionPage.getPopupSwitchButton().findElement(By.xpath("./..")).getAttribute("aria-checked").equals("false")){
            DataManager.getData().closePopup = false;
            InsertionPage.getPopupSwitchButton().click();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        DataManager.getData().insertionName = formatter.format(new Date());
        DriverManager.getDriver().scenario.write(DataManager.getData().insertionName);

        action.sendKeys(InsertionPage.getTitleField(), "title_" + DataManager.getData().insertionName).build().perform();
        action.sendKeys(InsertionPage.getSubTitleField(), "subtitle_" + DataManager.getData().insertionName).build().perform();

        InsertionPage.getUploadDescriptiveImageButton().sendKeys(new File("src/test/resources/input/document.png").getAbsolutePath());
        InsertionPage.getUploadEmailImageButton().sendKeys(new File("src/test/resources/input/document.png").getAbsolutePath());

        ElementSteps.makeVisible(InsertionPage.getDescriptionField(), true);
        action.sendKeys(InsertionPage.getDescriptionField(), "description_" + DataManager.getData().insertionName).build().perform();
        action.sendKeys(InsertionPage.getActionNameField(), "action_" + DataManager.getData().insertionName).build().perform();
        action.sendKeys(InsertionPage.getActionURLField(), "actionURL_" + DataManager.getData().insertionName).build().perform();

        if (!list.get(2).equals("Yes")){
            InsertionPage.getActivateSwitchButton().click();
            Thread.sleep(500);
        }
    }

    @When("I create a new insertion:")
    public void createInsertionHomePage(DataTable dt) throws Exception{
        List<String> list = dt.asList(String.class);
        fillInsertForm(list);

        //Select the correct page
        if ((InsertionPage.getHomePageSwitchButton().findElement(By.xpath("./..")).getAttribute("aria-checked").equals("false")
                && (list.get(3).contains("Homepage") || list.get(3).contains("All pages"))) ||
            (InsertionPage.getHomePageSwitchButton().findElement(By.xpath("./..")).getAttribute("aria-checked").equals("true")
                && (!list.get(3).contains("Homepage") && !list.get(3).contains("All pages")))){
            InsertionPage.getHomePageSwitchButton().click();
            Thread.sleep(500);
        }
        if ((InsertionPage.getNewsBodyPageSwitchButton().findElement(By.xpath("./..")).getAttribute("aria-checked").equals("false")
                && (list.get(3).contains("Body") || list.get(3).contains("All pages"))) ||
                (InsertionPage.getNewsBodyPageSwitchButton().findElement(By.xpath("./..")).getAttribute("aria-checked").equals("true")
                        && (!list.get(3).contains("Body") && !list.get(3).contains("All pages")))){
            InsertionPage.getNewsBodyPageSwitchButton().click();
            Thread.sleep(500);
        }
        if ((InsertionPage.getNewsColumnPageSwitchButton().findElement(By.xpath("./..")).getAttribute("aria-checked").equals("false")
                && (list.get(3).contains("Column") || list.get(3).contains("All pages"))) ||
                (InsertionPage.getNewsColumnPageSwitchButton().findElement(By.xpath("./..")).getAttribute("aria-checked").equals("true")
                        && (!list.get(3).contains("Column") && !list.get(3).contains("All pages")))){
            InsertionPage.getNewsColumnPageSwitchButton().click();
            Thread.sleep(500);
        }
        if ((InsertionPage.getEnterLeadPageSwtichButton().findElement(By.xpath("./..")).getAttribute("aria-checked").equals("false")
                && (list.get(3).contains("Enter a lead") || list.get(3).contains("All pages"))) ||
                (InsertionPage.getEnterLeadPageSwtichButton().findElement(By.xpath("./..")).getAttribute("aria-checked").equals("true")
                        && (!list.get(3).contains("Enter a lead") && !list.get(3).contains("All pages")))){
            InsertionPage.getEnterLeadPageSwtichButton().click();
            Thread.sleep(500);
        }
        Thread.sleep(1000);

        InsertionPage.getSaveButton().click();
        try {
            DriverManager.getDriver().driver.findElement(By.xpath("//*[contains(@class,\"dialog-footer\")]/button")).click();
        } catch (Exception e){

        }
    }

    @When("insertion is displayed in the list")
    public void checkInsertion() throws Exception{
        try {
            InsertionPage.getInsertionTableLine(DataManager.getData().insertionName);
        } catch (Exception e){
            GenericSteps.screenShot();
            throw new Exception("Freshly created insertion is not present in the list");
        }
    }

    @When("insertion is not displayed anymore in the list")
    public void checkInsertionDeleted() throws Exception{
        WebElement insertion = null;
        try {
            insertion = InsertionPage.getInsertionTableLine(DataManager.getData().insertionName);
        } catch (Exception e){

        }
        if (insertion != null){
            GenericSteps.screenShot();
            throw new Exception("Freshly created insertion is still present in the list");
        }
    }

    @When("I delete the insertion")
    public void deleteInsertion(){
        WebElement insertion = InsertionPage.getInsertionTableLine(DataManager.getData().insertionName);
        insertion.findElement(By.xpath(".//*[contains(@class,\"btn-danger\")]")).click();
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[text()=\"Yes\"]/.."), 5);
        DriverManager.getDriver().driver.findElement(By.xpath("//*[text()=\"Yes\"]/..")).click();
    }

    @When("I save inserts order")
    public void saveOrder(){
        List<WebElement> lines = InsertionPage.getInsertionTableTitles();
        for(WebElement line:lines){
            DataManager.getData().insertionsOrder.add(line.getAttribute("innerHTML"));
        }
    }

    @When("I change the order of inserts")
    public void changeOrder(){
        InsertionPage.getInsertionTableTitles().get(0).click();
        DriverManager.getDriver().driver.findElement(By.xpath("//jhi-config-add-insert//button[contains(@icon,\"angle-down\")]")).click();
    }
}
