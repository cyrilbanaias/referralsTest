package com.niji.steps.projectSteps;

import com.niji.data.DataManager;
import com.niji.factory.DriverManager;
import com.niji.pageObjects.*;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.steps.globalSteps.GenericSteps;
import com.niji.utils.DateUtils;
import com.niji.utils.NewBy;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.*;

public class LeadCreationSteps {

    @When("I open the lead creation page")
    public void openLeadForm(){
        MenuLinks.getLeadCreationMenuDropdown().click();
        MenuLinks.getLeadCreationMenuLink().click();
        new WebDriverWait(DriverManager.getDriver().driver, 10).until(ExpectedConditions.urlContains("leadForm"));
    }

    @When("the insert is displayed on the lead creation page")
    public void checkInsertOnLeadCreationPage() throws Exception{
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

    @When("the insert is not displayed anymore on the lead creation page")
    public void checkInsertDeletedOnLeadCreationPage() throws Exception{
        // Check title, subtitle, description, link text and link href
        WebElement insertion = null;
        try {
            WebElement block = InsertsElements.getColumnInsertBlock();
            insertion = InsertsElements.getInsertTitle(block, DataManager.getData().insertionName);
            insertion = InsertsElements.getInsertSubtitle(block, DataManager.getData().insertionName);
            insertion = InsertsElements.getInsertDescription(block, DataManager.getData().insertionName);
            insertion = InsertsElements.getInsertImage(block, DataManager.getData().insertionName);
            insertion = InsertsElements.getInsertAction(block, DataManager.getData().insertionName);
            insertion = InsertsElements.getInsertActionURL(block, DataManager.getData().insertionName);
        } catch (Exception e){

        }
        if (insertion != null){
            GenericSteps.screenShot();
            throw new Exception("Freshly created insertion is still present in the list");
        }
    }

    @When("I choose to create the lead as myself")
    public void createLeadasMyself(){
        Actions action = new Actions(DriverManager.getDriver().driver);
        action.sendKeys(LeadCreationPage.getSearchAgentField(), DataManager.getData().used_account).build().perform();
        LeadCreationPage.getSearchAgentButton().click();
    }

    @When("I select a personnal offer for the lead for a (.+) client")
    public void choosePersonnalOffer(String type) throws Exception{
        DataManager.getData().lead_type = type;
        WebElement radioButton;
        if(type.equals("professionnal")){
            radioButton = LeadCreationPage.getProfessionalRadioButton();
        } else radioButton = LeadCreationPage.getParticularRadioButton();
        ElementSteps.makeVisible(radioButton, true);
        radioButton.click();
        Thread.sleep(2000);
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

    @When("I select an existing offer for the lead for a (.+) client")
    public void selectExistingOffer(String type) throws Exception{
        DataManager.getData().lead_type = type;
        WebElement radioButton;
        if(type.equals("professionnal")){
            radioButton = LeadCreationPage.getProfessionalRadioButton();
        } else radioButton = LeadCreationPage.getParticularRadioButton();
        ElementSteps.makeVisible(radioButton, true);
        radioButton.click();
        Thread.sleep(2000);
        WebElement offerBlock = null;
        try {
            offerBlock = LeadCreationPage.getOfferBlockELement();
        } catch (Exception e){

        }
        if (offerBlock != null){
            int randomOffer = (int)(Math.random() * LeadCreationPage.getOctopusOffersLinks().size()-1);
            WebElement offer = LeadCreationPage.getOctopusOffersLinks().get(randomOffer);
            DataManager.getData().leadInformations = new ArrayList<>();
            DataManager.getData().leadInformations.add("existing_offer");
            DataManager.getData().existingOffer = new ArrayList<>();
            DataManager.getData().existingOffer.add(LeadCreationPage.getOctopusOfferTitle(offer).getText());
            DataManager.getData().existingOffer.add(LeadCreationPage.getOctopusOfferModel(offer).getText());
            DataManager.getData().existingOffer.add(LeadCreationPage.getOctopusOfferPrice(offer).getText());
            DataManager.getData().existingOffer.add(LeadCreationPage.getOctopusOfferContractDetails(offer).getText());
            ElementSteps.makeVisible(offer, true);
            offer.click();
        }
    }

    @When("I search for the existing (.+) client \"(.+)\"")
    public void searchClient(String type, String client){
        WebElement searchField;
        if (type.equals("professionnal")){
            searchField = LeadCreationPage.getSearchSIRENClientInputField();
        } else searchField = LeadCreationPage.getSearchEmailClientInputField();
        ElementSteps.makeVisible(searchField, true);
        if (type.equals("professionnal")){
            WebElement sirenYes = null;
            try {
                sirenYes = LeadCreationPage.getSIRENYesRadioButton();
            } catch (Exception e){

            }
            if (sirenYes != null) sirenYes.click();
        }
        (new Actions(DriverManager.getDriver().driver)).sendKeys(searchField, client).build().perform();
        ElementSteps.waitForVisibilityOfElement(NewBy.jhitranslate("lead.alreadyExisting"),5);
        LeadCreationPage.getSearchClientButton().click();
        DataManager.getData().company_number = client;
    }

    @When("I search for an existing (.+) client")
    public void searchClient(String type){
        WebElement searchField;
        if (type.equals("professionnal")){
            searchField = LeadCreationPage.getSearchSIRENClientInputField();
        } else searchField = LeadCreationPage.getSearchEmailClientInputField();
        ElementSteps.makeVisible(searchField, true);
        if (type.equals("professionnal")){
            WebElement sirenYes = null;
            try {
                sirenYes = LeadCreationPage.getSIRENYesRadioButton();
            } catch (Exception e){

            }
            if (sirenYes != null) sirenYes.click();
        }
        (new Actions(DriverManager.getDriver().driver)).sendKeys(searchField, DataManager.getData().existing_client).build().perform();
        ElementSteps.waitForVisibilityOfElement(NewBy.jhitranslate("lead.alreadyExisting"),5);
        LeadCreationPage.getSearchClientButton().click();
        DataManager.getData().company_number = DataManager.getData().existing_client;
    }

    @When("I check the professionnal client informations")
    public void checkClientInformations(){
        ElementSteps.makeVisible(LeadCreationPage.getClientSocialNameField(), true);
        String name = LeadCreationPage.getClientSocialNameField().getAttribute("value");
        DataManager.getData().company_name = name;
        DataManager.getData().errorCollector.checkThat("Comapny name is not the expected one",
                name, not(equalTo("")));
        String newDate = DateUtils.getNewStringDate(10, "yyyy/MM/dd");
        ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click()", DatePicker.getOpenCalendarButton());
        DateUtils.datePickerSelection(newDate);
    }

    @When("I check the particular client informations")
    public void checkPartClientInformations(){
        DataManager.getData().client_informations = "";
        ElementSteps.makeVisible(LeadCreationPage.getClientFirstNameField(), true);
        String info = LeadCreationPage.getClientFirstNameField().getAttribute("value");
        DataManager.getData().client_informations = DataManager.getData().client_informations + info;
        DataManager.getData().errorCollector.checkThat("Client first name is not the expected one",
                info, not(equalTo("")));
        info = LeadCreationPage.getClientLastNameField().getAttribute("value");
        DataManager.getData().client_informations = DataManager.getData().client_informations + info;
        DataManager.getData().errorCollector.checkThat("Client last name is not the expected one",
                info, not(equalTo("")));
        String newDate = DateUtils.getNewStringDate(10, "yyyy/MM/dd");
        ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click()", DatePicker.getOpenCalendarButton());
        DateUtils.datePickerSelection(newDate);
    }

    @When("I check the contact informations")
    public void checkContactInformations(){
        try {
            DataManager.getData().client_informations = "";
            ElementSteps.makeVisible(LeadCreationPage.getClientFirstNameField(), true);
            String info = LeadCreationPage.getClientFirstNameField().getAttribute("value");
            DataManager.getData().client_informations = DataManager.getData().client_informations + info;
            DataManager.getData().errorCollector.checkThat("Client first name is not the expected one",
                    info, not(equalTo("")));
            info = LeadCreationPage.getClientLastNameField().getAttribute("value");
            DataManager.getData().client_informations = DataManager.getData().client_informations + info;
            DataManager.getData().errorCollector.checkThat("Client last name is not the expected one",
                    info, not(equalTo("")));
            info = LeadCreationPage.getClientEmailField().getAttribute("value");
            DataManager.getData().client_email = info;
            DataManager.getData().errorCollector.checkThat("Client email is not the expected one",
                    info, not(equalTo("")));
        } catch (Exception e){

        }
    }

    @When("I fill the client need with:")
    public void fillClientNeed(DataTable dt) throws Exception{
        DataManager.getData().leadInformations = new ArrayList<>();
        Actions action = new Actions(DriverManager.getDriver().driver);
        List<String> list = dt.asList(String.class);
        DriverManager.getDriver().driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        ElementSteps.makeVisible(LeadCreationPage.getClientNeedBlockELement(), true);

        int i = (int) (Math.random());
        try {
            if(i==0) ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click()", LeadCreationPage.getASAPRadioButton());
            else ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click()", LeadCreationPage.getAppointmentRadioButton());
        } catch (Exception e){}

        try {
            Select select = new Select(LeadCreationPage.getProjectSelectList());
            select.selectByIndex(1);
            DataManager.getData().leadInformations.add(select.getOptions().get(1).getText());
        } catch (Exception e){
            DataManager.getData().leadInformations.add("null");
        }

        try {
            if (i == 1){
                String newDate = DateUtils.getNewStringDate(Integer.parseInt(list.get(0).replaceAll("d","")), "yyyy/MM/dd");
                ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click()", DatePicker.getOpenCalendarButton());
                DateUtils.datePickerSelection(newDate);
                ElementSteps.makeVisible(DatePicker.getHourField(), true);
                DatePicker.getHourField().clear();
                action.sendKeys(DatePicker.getHourField(), list.get(1).split(":")[0]).build().perform();
                DatePicker.getMinutesField().clear();
                action.sendKeys(DatePicker.getMinutesField(), list.get(1).split(":")[1]).build().perform();
            }
        } catch (Exception e){}

        try {
            Select select = new Select(LeadCreationPage.getProjectDeadLineSelectList());
            select.selectByIndex(1);
            DataManager.getData().leadInformations.add(select.getOptions().get(1).getText());
        } catch (Exception e){
            DataManager.getData().leadInformations.add("null");
        }

        try {
            Select select = new Select(LeadCreationPage.getFundingModelSelectList());
            select.selectByIndex(1);
            DataManager.getData().leadInformations.add(select.getOptions().get(1).getText());
        } catch (Exception e){
            DataManager.getData().leadInformations.add("null");
        }

        try {
            LeadCreationPage.getFleetSizeInputField().clear();
            action.sendKeys(LeadCreationPage.getFleetSizeInputField(), list.get(2)).build().perform();
            DataManager.getData().leadInformations.add(list.get(2));
        } catch (Exception e){
            DataManager.getData().leadInformations.add("null");
        }

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String comment = formatter.format(new Date());
            LeadCreationPage.getVehicleModelInputField().clear();
            action.sendKeys(LeadCreationPage.getVehicleModelInputField(), comment).build().perform();
            DataManager.getData().leadInformations.add(comment);
        } catch (Exception e){
            DataManager.getData().leadInformations.add("null");
        }

        try {
            Select select = new Select(LeadCreationPage.getDurationSelectList());
            select.selectByIndex(1);
            DataManager.getData().leadInformations.add(select.getOptions().get(1).getText());
        } catch (Exception e){
            DataManager.getData().leadInformations.add("null");
        }

        try {
            Select select = new Select(LeadCreationPage.getTriggerEventSelectList());
            select.selectByIndex(1);
            DataManager.getData().leadInformations.add(select.getOptions().get(1).getText());
        } catch (Exception e){
            DataManager.getData().leadInformations.add("null");
        }

        try {
            LeadCreationPage.getAnnualMileageInputField().clear();
            action.sendKeys(LeadCreationPage.getAnnualMileageInputField(), list.get(3)).build().perform();
            DataManager.getData().leadInformations.add(list.get(3));
        } catch (Exception e){
            DataManager.getData().leadInformations.add("null");
        }

        try {
            if(i==0) ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click()", LeadCreationPage.getMileageAllowanceYesRadioButton());
         else ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click()", LeadCreationPage.getMileageAllowanceNoRadioButton());
        } catch (Exception e){}

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String comment = formatter.format(new Date());
            LeadCreationPage.getMoreDetailsField().clear();
            action.sendKeys(LeadCreationPage.getMoreDetailsField(), comment).build().perform();
            DataManager.getData().leadInformations.add(comment);
        } catch (Exception e){
            DataManager.getData().leadInformations.add("null");
        }

        try {
            LeadCreationPage.getUploadDocumentButton().sendKeys((new File("src/test/resources/input/document.png")).getAbsolutePath());
            Thread.sleep(2000);
        } catch (Exception e){}

        DriverManager.getDriver().driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        GenericSteps.screenShot();
    }

    @When("I send the lead by email")
    public void sendLeadByEmail(){
        ElementSteps.makeVisible(LeadCreationPage.getGDPRCheckBox(), true);
        ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click()", LeadCreationPage.getGDPRCheckBox());
        ElementSteps.makeVisible(LeadCreationPage.getSendLinkButton(), true);
        LeadCreationPage.getSendLinkButton().click();
        ElementSteps.waitForVisibilityOfElement(Alert.getSuccessAlert(), 5);
    }

    @When("I print the lead")
    public void printLead(){
        if(!DataManager.getData().account_type.equals("BNPP")){
            ElementSteps.makeVisible(LeadCreationPage.getPrintButton(), true);
            ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("window.print = function(){};");
            LeadCreationPage.getPrintButton().click();
        }
    }

    @When("I save the lead")
    public void saveLead() throws Exception{
        ElementSteps.makeVisible(LeadCreationPage.getSaveButton(), true);
        LeadCreationPage.getSaveButton().click();
        Thread.sleep(5000);
        DataManager.getData().errorCollector.checkThat("URL is not the expected one",
                DriverManager.getDriver().driver.getCurrentUrl(),
                containsString("referrals/lead-view"));
    }

    @When("I send the lead to salesforce")
    public void sendToSalesforce() throws Exception{
        ElementSteps.makeVisible(LeadCreationPage.getSendButton(), true);
        LeadCreationPage.getSendButton().click();
        Thread.sleep(5000);
        DataManager.getData().errorCollector.checkThat("URL is not the expected one",
                DriverManager.getDriver().driver.getCurrentUrl(),
                containsString("referrals/lead-view"));
    }
}
