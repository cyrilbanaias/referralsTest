package com.niji.pageObjects;

import com.niji.factory.DriverManager;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.utils.NewBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ReferralsPage {

    public static WebElement getLeadCreationMenuDropdown(){
        return DriverManager.getDriver().driver.findElement(By.id("dropdownReferralsLead"));
    }

    public static WebElement getLeadCreationMenuLink(){
        ElementSteps.waitForVisibilityOfElement(NewBy.jhitranslate("lead.title"), 5);
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("lead.title"));
    }

    public static WebElement getSearchAgentField(){
        return DriverManager.getDriver().driver.findElement(By.id("searchInput"));
    }

    public static WebElement getSearchAgentButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("entity.action.validate"));
    }

    public static List<WebElement> getOctopusOffersLinks(){
        return DriverManager.getDriver().driver.findElements(By.name("octopusOffer"));
    }

    public static WebElement getParticularRadioButton() {
        ElementSteps.waitForVisibilityOfElement(NewBy.jhitranslate("thirdPartyType.particular"), 5);
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("thirdPartyType.particular"));
    }

    public static WebElement getProfessionalRadioButton() {
        ElementSteps.waitForVisibilityOfElement(NewBy.jhitranslate("thirdPartyType.professional"), 5);
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("thirdPartyType.professional"));
    }

    public static WebElement getSIRENYesRadioButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("yes"));
    }

    public static WebElement getSIRENNoRadioButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("no"));
    }

    public static WebElement getSIRENInputField(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_registrationNumber"));
    }

    public static WebElement getSearchSIRENButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("lead.button.search"));
    }

    // Client information
    public static WebElement getCompanyNameField(){
        ElementSteps.waitForVisibilityOfElement(By.id("thirdParty_name"), 10);
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_name"));
    }

    public static WebElement getRiskSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_rsfCotationsId"));
    }

    // Client Contact
    public static WebElement getClientLanguageSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_languagesId"));
    }

    public static WebElement getMisterRadioButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.forAttribute("thirdParty_contactCivilityId_1"));
    }

    public static WebElement getMissRadioButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.forAttribute("thirdParty_contactCivilityId_2"));
    }

    public static WebElement getClientFirstNameField(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_contactFirstName"));
    }

    public static WebElement getClientLastNameField(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_contactLastName"));
    }

    public static WebElement getClientFunctionField(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_contactFunction"));
    }

    public static WebElement getClientMobilePhoneNumberField(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_contactMobileNumber"));
    }

    public static WebElement getClientPhoneNumberField(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_contactPhoneNumber"));
    }

    public static WebElement getClientEmailField(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_contactEmail"));
    }

    // Client's need
    public static WebElement getASAPRadioButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.forAttribute("ecallsId_1"));
    }

    public static WebElement getAppointmentRadioButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.forAttribute("recallsId_2"));
    }

    public static WebElement getProjectSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("projectsId"));
    }

    public static WebElement getProjectDeadLineSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("deadlinesId"));
    }

    public static WebElement getFundingModelSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("fundmodesId"));
    }

    public static WebElement getTriggerEventSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("eventsId"));
    }

    public static WebElement getFleetSizeInputField(){
        return DriverManager.getDriver().driver.findElement(By.id("companyFleetSize"));
    }

    public static WebElement getVehicleModelInputField(){
        return DriverManager.getDriver().driver.findElement(By.id("vehicleModel"));
    }

    public static WebElement getDurationSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("contractDurationsId"));
    }

    public static WebElement getAnnualMileageInputField(){
        return DriverManager.getDriver().driver.findElement(By.id("annualMileage"));
    }

    public static WebElement getUploadDocumentButton(){
        return DriverManager.getDriver().driver.findElement(By.id("fileDropRef"));
    }

    public static WebElement getGDPRCheckBox(){
        return DriverManager.getDriver().driver.findElement(NewBy.forAttribute("rgpd"));
    }
}
