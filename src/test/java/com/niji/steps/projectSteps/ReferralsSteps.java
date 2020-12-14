package com.niji.steps.projectSteps;

import com.niji.data.DataManager;
import com.niji.factory.DriverManager;
import com.niji.pageObjects.DatePicker;
import com.niji.pageObjects.ReferralsPage;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.steps.globalSteps.GenericSteps;
import com.niji.utils.DateUtils;
import cucumber.api.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.equalTo;

import java.io.File;
import java.util.List;

public class ReferralsSteps {

    @When("I open the lead creation page")
    public void openLeadForm(){
        ReferralsPage.getLeadCreationMenuDropdown().click();
        ReferralsPage.getLeadCreationMenuLink().click();
        new WebDriverWait(DriverManager.getDriver().driver, 10).until(ExpectedConditions.urlContains("leadForm"));
    }

    @When("I can fill the lead form with informations")
    public void fillLeadForm() throws Exception{
        Actions action = new Actions(DriverManager.getDriver().driver);
        if (DataManager.getData().account_type.equals("FORTIS")){
            List<WebElement> offers = ReferralsPage.getOctopusOffersLinks();
            int randomOffer = (int) (Math.random() * (offers.size() - 1));
            offers.get(randomOffer).click();
            ElementSteps.makeVisible(ReferralsPage.getSIRENYesRadioButton(), true);
            ReferralsPage.getSIRENYesRadioButton().click();
            action.sendKeys(ReferralsPage.getSIRENInputField(), "19640721").build().perform();
            ReferralsPage.getSearchSIRENButton().click();
            ElementSteps.makeVisible(ReferralsPage.getCompanyNameField(), true);
            DataManager.getData().errorCollector.checkThat("Comapny name is not the expected one",
                    ReferralsPage.getCompanyNameField().getAttribute("value"), equalTo("CK BNPPFORTIS PROF"));
            ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click()", DatePicker.getOpenCalendarButton());
            DateUtils.datePickerSelection("2021/03/17");
            ElementSteps.makeVisible(ReferralsPage.getClientFirstNameField(), true);
            DataManager.getData().errorCollector.checkThat("Client first name is not the expected one",
                    ReferralsPage.getClientFirstNameField().getAttribute("value"), equalTo("Christoph"));
            DataManager.getData().errorCollector.checkThat("Client last name is not the expected one",
                    ReferralsPage.getClientLastNameField().getAttribute("value"), equalTo("Kacprzak"));
            DataManager.getData().errorCollector.checkThat("Client email is not the expected one",
                    ReferralsPage.getClientEmailField().getAttribute("value"), equalTo("ck.test@arval.de"));
            ReferralsPage.getClientFunctionField().clear();
            action.sendKeys(ReferralsPage.getClientFunctionField(), "Tester").build().perform();
            ReferralsPage.getGDPRCheckBox().click();
            GenericSteps.screenShot();
        }
        if (DataManager.getData().account_type.equals("BNPP")){
            action.sendKeys(ReferralsPage.getSearchAgentField(), DataManager.getData().used_account).build().perform();
            ReferralsPage.getSearchAgentButton().click();
            ElementSteps.makeVisible(ReferralsPage.getProfessionalRadioButton(), true);
            ReferralsPage.getProfessionalRadioButton().click();
            Thread.sleep(1000);
            action.sendKeys(ReferralsPage.getSIRENInputField(), "19640721").build().perform();
            ReferralsPage.getSearchSIRENButton().click();
            ElementSteps.makeVisible(ReferralsPage.getCompanyNameField(), true);
            DataManager.getData().errorCollector.checkThat("Comapny name is not the expected one",
                    ReferralsPage.getCompanyNameField().getAttribute("value"), equalTo("CK BNPPGER PROF"));
            DataManager.getData().errorCollector.checkThat("Client first name is not the expected one",
                    ReferralsPage.getClientFirstNameField().getAttribute("value"), equalTo("Christoph"));
            DataManager.getData().errorCollector.checkThat("Client last name is not the expected one",
                    ReferralsPage.getClientLastNameField().getAttribute("value"), equalTo("test"));
            DataManager.getData().errorCollector.checkThat("Client email is not the expected one",
                    ReferralsPage.getClientEmailField().getAttribute("value"), equalTo("christoph.kacprzak@arval.de"));
            ReferralsPage.getClientFunctionField().clear();
            action.sendKeys(ReferralsPage.getClientFunctionField(), "Tester").build().perform();
            ElementSteps.makeVisible(ReferralsPage.getAppointmentRadioButton(), true);
            ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click()", ReferralsPage.getAppointmentRadioButton());
            ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click()", DatePicker.getOpenCalendarButton());
            DateUtils.datePickerSelection("2021/03/17");
            ElementSteps.makeVisible(DatePicker.getHourField(), true);
            action.sendKeys(DatePicker.getHourField(), "12").build().perform();
            action.sendKeys(DatePicker.getMinutesField(), "13").build().perform();
            Select select = new Select(ReferralsPage.getProjectSelectList());
            select.selectByIndex(1);
            select = new Select(ReferralsPage.getProjectDeadLineSelectList());
            select.selectByIndex(1);
            select = new Select(ReferralsPage.getFundingModelSelectList());
            select.selectByIndex(1);
            select = new Select(ReferralsPage.getTriggerEventSelectList());
            select.selectByIndex(1);
            action.sendKeys(ReferralsPage.getFleetSizeInputField(), "3").build().perform();
            action.sendKeys(ReferralsPage.getVehicleModelInputField(), "BMW").build().perform();
            select = new Select(ReferralsPage.getDurationSelectList());
            select.selectByIndex(1);
            action.sendKeys(ReferralsPage.getAnnualMileageInputField(), "12345").build().perform();
            ReferralsPage.getUploadDocumentButton().sendKeys((new File("src/test/resources/input/document.png")).getAbsolutePath());
            GenericSteps.screenShot();
            ElementSteps.makeVisible(ReferralsPage.getGDPRCheckBox(), true);
            ReferralsPage.getGDPRCheckBox().click();
            GenericSteps.screenShot();
        }
    }
}
