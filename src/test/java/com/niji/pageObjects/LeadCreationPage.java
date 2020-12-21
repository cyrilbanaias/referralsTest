package com.niji.pageObjects;

import com.niji.factory.DriverManager;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.utils.NewBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LeadCreationPage {

    // Agent/Organisation block
    public static WebElement getSearchAgentField(){
        return DriverManager.getDriver().driver.findElement(By.id("searchInput"));
    }

    public static WebElement getSearchAgentButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("entity.action.validate"));
    }

    // Offer Block
    public static WebElement getOfferBlockELement(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@data-icon=\"bullseye-pointer\"]/../../.."));
    }

    public static List<WebElement> getOctopusOffersLinks(){
        return DriverManager.getDriver().driver.findElements(By.name("octopusOffer"));
    }

    public static WebElement getOctopusOfferTitle(WebElement octopusOffer){
        return octopusOffer.findElement(By.xpath("./div/h4"));
    }

    public static WebElement getOctopusOfferModel(WebElement octopusOffer){
        return octopusOffer.findElements(By.xpath("./div/p")).get(0);
    }

    public static WebElement getOctopusOfferPrice(WebElement octopusOffer){
        return octopusOffer.findElement(By.xpath("./div/p/span"));
    }

    public static WebElement getOctopusOfferContractDetails(WebElement octopusOffer){
        return octopusOffer.findElements(By.xpath("./div/p")).get(2);
    }

    public static WebElement getPersonnalOfferLink(){
        return getOfferBlockELement().findElement(By.xpath(".//a[not(@name=\"octopusOffer\")]"));
    }

    // Search Client Block
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

    public static WebElement getSearchSIRENClientInputField(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_registrationNumber"));
    }

    public static WebElement getSearchEmailClientInputField(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_contactEmail"));
    }

    public static WebElement getSearchClientButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("lead.button.search"));
    }

    // Client information
    public static WebElement getClientSocialNameField(){
        ElementSteps.waitForVisibilityOfElement(By.id("thirdParty_name"), 10);
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_name"));
    }

    public static WebElement getClientPostalCodeField(){
        ElementSteps.waitForVisibilityOfElement(By.id("thirdParty_postalCode"), 10);
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_postalCode"));
    }

    public static WebElement getClientCityField(){
        ElementSteps.waitForVisibilityOfElement(By.id("thirdParty_thirdPartyCity"), 10);
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_thirdPartyCity"));
    }

    public static WebElement getBNPClientStatusField(){
        ElementSteps.waitForVisibilityOfElement(NewBy.forAttribute("thirdParty_bnpStatusesId_1"), 10);
        return DriverManager.getDriver().driver.findElement(NewBy.forAttribute("thirdParty_bnpStatusesId_1"));
    }

    public static WebElement getBNPProspectStatusField(){
        ElementSteps.waitForVisibilityOfElement(NewBy.forAttribute("thirdParty_bnpStatusesId_2"), 10);
        return DriverManager.getDriver().driver.findElement(NewBy.forAttribute("thirdParty_bnpStatusesId_2"));
    }

    public static WebElement getCotationRSFSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_rsfCotationsId"));
    }

    public static WebElement getLastCertificationDateButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"thirdParty_rsfCotationLastReviewDate\"]/..//*[@data-icon=\"calendar-alt\"]/../.."));
    }

    public static WebElement getLastCertificationDateField(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"thirdParty_rsfCotationLastReviewDate\"]"));
    }

    public static WebElement getNextCertificationButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"thirdParty_rsfCotationLastReviewDate\"]/..//*[@data-icon=\"calendar-alt\"]/../.."));
    }

    public static WebElement getNextCertificationField(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"thirdParty_rsfCotationLastReviewDate\"]"));
    }

    public static WebElement getCotationsReasonField(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_rsfCotationsReason"));
    }

    public static WebElement getFiscalCodeField(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_thirdPartyFiscalCode"));
    }

    public static WebElement getStreetField(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_thirdPartyStreet"));
    }

    public static WebElement getBUCAgencyNumberField(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_thirdPartyAgence"));
    }

    public static WebElement getNotationSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_riskRatingsId"));
    }

    public static WebElement getMngmntNoteSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdParty_mngmntRatingsId"));
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
    public static WebElement getClientNeedBlockELement(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@data-icon=\"comment-alt-smile\"]/../../.."));
    }

    public static WebElement getASAPRadioButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.forAttribute("recallsId_1"));
    }

    public static WebElement getAppointmentRadioButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.forAttribute("recallsId_2"));
    }

    public static WebElement getProjectDeadLineSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("deadlinesId"));
    }

    public static WebElement getAppointmentDateButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"recallDate\"]/..//*[@data-icon=\"calendar-alt\"]/../.."));
    }

    public static WebElement getAppointmentDateField(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"recallDate\"]"));
    }

    public static WebElement getFundingModelSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("fundmodesId"));
    }

    public static WebElement getProjectSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("projectsId"));
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

    public static WebElement getTriggerEventSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("eventsId"));
    }

    public static WebElement getAnnualMileageInputField(){
        return DriverManager.getDriver().driver.findElement(By.id("annualMileage"));
    }

    public static WebElement getMileageAllowanceYesRadioButton(){
        return DriverManager.getDriver().driver.findElement(By.id("kilometricIndemnitiesId_1"));
    }

    public static WebElement getMileageAllowanceNoRadioButton(){
        return DriverManager.getDriver().driver.findElement(By.id("kilometricIndemnitiesId_2"));
    }

    public static WebElement getMoreDetailsField(){
        return DriverManager.getDriver().driver.findElement(By.id("description"));
    }

    public static WebElement getUploadDocumentButton(){
        return DriverManager.getDriver().driver.findElement(By.id("fileDropRef"));
    }

    // GDPR, Prind, Send
    public static WebElement getGDPRCheckBox(){
        return DriverManager.getDriver().driver.findElement(NewBy.forAttribute("rgpd"));
    }

    public static WebElement getSendLinkButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("lead.send.privacypolicy.title"));
    }

    public static WebElement getConfidentialiteCheckbox(){
        return DriverManager.getDriver().driver.findElement(NewBy.forAttribute("confidentialite"));
    }

    public static WebElement getPrintButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("lead.send.print"));
    }

    public static WebElement getSaveButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[contains(@id,\"saveButton\")]"));
    }

    public static WebElement getSendButton(){
        return DriverManager.getDriver().driver.findElement(By.id("completeButton"));
    }
}
