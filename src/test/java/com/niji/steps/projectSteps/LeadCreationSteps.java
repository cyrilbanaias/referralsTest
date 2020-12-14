package com.niji.steps.projectSteps;

import com.niji.data.DataManager;
import com.niji.factory.DriverManager;
import com.niji.pageObjects.DatePicker;
import com.niji.pageObjects.LeadCreationPage;
import com.niji.pageObjects.MenuLinks;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.steps.globalSteps.GenericSteps;
import com.niji.utils.DateUtils;
import com.niji.utils.NewBy;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;

import java.io.File;
import java.util.List;

public class LeadCreationSteps {

    @When("I open the lead creation page")
    public void openLeadForm(){
        MenuLinks.getLeadCreationMenuDropdown().click();
        MenuLinks.getLeadCreationMenuLink().click();
        new WebDriverWait(DriverManager.getDriver().driver, 10).until(ExpectedConditions.urlContains("leadForm"));
    }

    @When("I choose to create the lead as myself")
    public void createLeadasMyself(){
        Actions action = new Actions(DriverManager.getDriver().driver);
        action.sendKeys(LeadCreationPage.getSearchAgentField(), DataManager.getData().used_account).build().perform();
        LeadCreationPage.getSearchAgentButton().click();
    }

    @When("I select a personnal offer for the lead for a professionnal client")
    public void choosePersonnalOffer(){
        ElementSteps.makeVisible(LeadCreationPage.getProfessionalRadioButton(), true);
        LeadCreationPage.getProfessionalRadioButton().click();
        WebElement offerBlock = null;
        try {
            offerBlock = LeadCreationPage.getOfferBlockELement();
        } catch (Exception e){

        }
        if (offerBlock != null){
            ElementSteps.makeVisible(LeadCreationPage.getPersonnalOfferLink(), true);
            LeadCreationPage.getPersonnalOfferLink().click();
        }
    }

    @When("I search for the existing client \"(.+)\"")
    public void searchClient(String client){
        ElementSteps.makeVisible(LeadCreationPage.getSearchClientInputField(), true);
        WebElement sirenYes = null;
        try {
            sirenYes = LeadCreationPage.getSIRENYesRadioButton();
        } catch (Exception e){

        }
        if (sirenYes != null) sirenYes.click();
        (new Actions(DriverManager.getDriver().driver)).sendKeys(LeadCreationPage.getSearchClientInputField(), client).build().perform();
        ElementSteps.waitForVisibilityOfElement(NewBy.jhitranslate("lead.alreadyExisting"),5);
        LeadCreationPage.getSearchClientButton().click();
    }

    @When("I check the client informations")
    public void checkClientInformations(){
        ElementSteps.makeVisible(LeadCreationPage.getClientSocialNameField(), true);
        DataManager.getData().errorCollector.checkThat("Comapny name is not the expected one",
                LeadCreationPage.getClientSocialNameField().getAttribute("value"), not(equalTo("")));
    }

    @When("I check the contact informations")
    public void checkContactInformations(){
        try {
            ElementSteps.makeVisible(LeadCreationPage.getClientFirstNameField(), true);
            DataManager.getData().errorCollector.checkThat("Client first name is not the expected one",
                    LeadCreationPage.getClientFirstNameField().getAttribute("value"), not(equalTo("")));
            DataManager.getData().errorCollector.checkThat("Client last name is not the expected one",
                    LeadCreationPage.getClientLastNameField().getAttribute("value"), not(equalTo("")));
            DataManager.getData().errorCollector.checkThat("Client email is not the expected one",
                    LeadCreationPage.getClientEmailField().getAttribute("value"), not(equalTo("")));
        } catch (Exception e){

        }
    }

    @When("I can fill the lead form with informations")
    public void fillLeadForm() throws Exception{
        Actions action = new Actions(DriverManager.getDriver().driver);
        if (DataManager.getData().account_type.equals("FORTIS")){
            List<WebElement> offers = LeadCreationPage.getOctopusOffersLinks();
            int randomOffer = (int) (Math.random() * (offers.size() - 1));
            offers.get(randomOffer).click();
            ElementSteps.makeVisible(LeadCreationPage.getSIRENYesRadioButton(), true);
            LeadCreationPage.getSIRENYesRadioButton().click();
            action.sendKeys(LeadCreationPage.getSearchClientInputField(), "19640721").build().perform();
            LeadCreationPage.getSearchClientButton().click();
            ElementSteps.makeVisible(LeadCreationPage.getClientSocialNameField(), true);
            DataManager.getData().errorCollector.checkThat("Comapny name is not the expected one",
                    LeadCreationPage.getClientSocialNameField().getAttribute("value"), equalTo("CK BNPPFORTIS PROF"));
            ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click()", DatePicker.getOpenCalendarButton());
            DateUtils.datePickerSelection("2021/03/17");
            ElementSteps.makeVisible(LeadCreationPage.getClientFirstNameField(), true);
            DataManager.getData().errorCollector.checkThat("Client first name is not the expected one",
                    LeadCreationPage.getClientFirstNameField().getAttribute("value"), equalTo("Christoph"));
            DataManager.getData().errorCollector.checkThat("Client last name is not the expected one",
                    LeadCreationPage.getClientLastNameField().getAttribute("value"), equalTo("Kacprzak"));
            DataManager.getData().errorCollector.checkThat("Client email is not the expected one",
                    LeadCreationPage.getClientEmailField().getAttribute("value"), equalTo("ck.test@arval.de"));
            LeadCreationPage.getClientFunctionField().clear();
            action.sendKeys(LeadCreationPage.getClientFunctionField(), "Tester").build().perform();
            LeadCreationPage.getGDPRCheckBox().click();
            GenericSteps.screenShot();
        }
        if (DataManager.getData().account_type.equals("BNPP")){
            action.sendKeys(LeadCreationPage.getSearchAgentField(), DataManager.getData().used_account).build().perform();
            LeadCreationPage.getSearchAgentButton().click();
            ElementSteps.makeVisible(LeadCreationPage.getProfessionalRadioButton(), true);
            LeadCreationPage.getProfessionalRadioButton().click();
            Thread.sleep(1000);
            action.sendKeys(LeadCreationPage.getSearchClientInputField(), "19640721").build().perform();
            LeadCreationPage.getSearchClientButton().click();
            ElementSteps.makeVisible(LeadCreationPage.getClientSocialNameField(), true);
            DataManager.getData().errorCollector.checkThat("Comapny name is not the expected one",
                    LeadCreationPage.getClientSocialNameField().getAttribute("value"), equalTo("CK BNPPGER PROF"));
            DataManager.getData().errorCollector.checkThat("Client first name is not the expected one",
                    LeadCreationPage.getClientFirstNameField().getAttribute("value"), equalTo("Christoph"));
            DataManager.getData().errorCollector.checkThat("Client last name is not the expected one",
                    LeadCreationPage.getClientLastNameField().getAttribute("value"), equalTo("test"));
            DataManager.getData().errorCollector.checkThat("Client email is not the expected one",
                    LeadCreationPage.getClientEmailField().getAttribute("value"), equalTo("christoph.kacprzak@arval.de"));
            LeadCreationPage.getClientFunctionField().clear();
            action.sendKeys(LeadCreationPage.getClientFunctionField(), "Tester").build().perform();
            ElementSteps.makeVisible(LeadCreationPage.getAppointmentRadioButton(), true);
            ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click()", LeadCreationPage.getAppointmentRadioButton());
            ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click()", DatePicker.getOpenCalendarButton());
            DateUtils.datePickerSelection("2021/03/17");
            ElementSteps.makeVisible(DatePicker.getHourField(), true);
            action.sendKeys(DatePicker.getHourField(), "12").build().perform();
            action.sendKeys(DatePicker.getMinutesField(), "13").build().perform();
            Select select = new Select(LeadCreationPage.getProjectSelectList());
            select.selectByIndex(1);
            select = new Select(LeadCreationPage.getProjectDeadLineSelectList());
            select.selectByIndex(1);
            select = new Select(LeadCreationPage.getFundingModelSelectList());
            select.selectByIndex(1);
            select = new Select(LeadCreationPage.getTriggerEventSelectList());
            select.selectByIndex(1);
            action.sendKeys(LeadCreationPage.getFleetSizeInputField(), "3").build().perform();
            action.sendKeys(LeadCreationPage.getVehicleModelInputField(), "BMW").build().perform();
            select = new Select(LeadCreationPage.getDurationSelectList());
            select.selectByIndex(1);
            action.sendKeys(LeadCreationPage.getAnnualMileageInputField(), "12345").build().perform();
            LeadCreationPage.getUploadDocumentButton().sendKeys((new File("src/test/resources/input/document.png")).getAbsolutePath());
            GenericSteps.screenShot();
            ElementSteps.makeVisible(LeadCreationPage.getGDPRCheckBox(), true);
            LeadCreationPage.getGDPRCheckBox().click();
            GenericSteps.screenShot();
        }
    }
}
