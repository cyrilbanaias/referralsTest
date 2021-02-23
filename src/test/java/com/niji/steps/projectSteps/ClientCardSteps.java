package com.niji.steps.projectSteps;

import com.niji.data.DataManager;
import com.niji.factory.DriverManager;
import com.niji.steps.globalSteps.ClientCardPage;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.steps.globalSteps.GenericSteps;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.*;

public class ClientCardSteps {

    @When("I can see the lead on the client page")
    public static void checkLeadOnClientPage() throws Exception{
        if (DataManager.getData().lead_type.equals("professionnal")){
            DataManager.getData().errorCollector.checkThat("Company name is not the expected one",
                    ClientCardPage.getCompanyNameArea().getAttribute("innerHTML"),
                    equalTo(DataManager.getData().company_name));
            DataManager.getData().errorCollector.checkThat("Company number is not the expected one",
                    ClientCardPage.getComppanyNumberField().getText(),
                    equalTo(DataManager.getData().company_number));
        } else {
            DataManager.getData().errorCollector.checkThat("Client name is not the expected one",
                    ClientCardPage.getCompanyNameArea().getAttribute("innerHTML").replaceAll(" ", ""),
                    equalTo(DataManager.getData().client_informations));
        }

        ElementSteps.makeVisible(ClientCardPage.getOpenedLeadDetailsBlock(), true);
        GenericSteps.screenShot();
        DriverManager.getDriver().driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        // TODO si info lead = existing_account alors faire un check sur le champ description
        if(DataManager.getData().leadInformations.contains("existing_offer")){
            String[] details = ClientCardPage.getLeadDetailsField().getAttribute("innerHTML").toLowerCase().split(", ");
            String mark = details[1].substring(details[1].indexOf(": '")+3,details[1].length()-1);
            String modele = details[2].substring(details[2].indexOf(": '")+3,details[2].length()-1);
            String version = details[3].substring(details[3].indexOf(": '")+3,details[3].length()-1);
            String price = details[4].substring(details[4].indexOf(": '")+3,details[4].length()-1);
            String mileage = details[5].substring(details[5].indexOf(": '")+3,details[5].length()-1);
            String duration = details[6].substring(details[6].indexOf(": '")+3,details[6].length()-1);
            DataManager.getData().errorCollector.checkThat("Vehicle mark is not the expected one",
                    DataManager.getData().existingOffer.get(0).toLowerCase(),
                    containsString(mark));
            DataManager.getData().errorCollector.checkThat("Vehicle model is not the expected one",
                    DataManager.getData().existingOffer.get(0).toLowerCase(),
                    containsString(modele));
            DataManager.getData().errorCollector.checkThat("Vehicle version is not the expected one",
                    DataManager.getData().existingOffer.get(1).toLowerCase(),
                    containsString(version.replaceAll("amp;", "")));
            DataManager.getData().errorCollector.checkThat("Vehicle price is not the expected one",
                    DataManager.getData().existingOffer.get(2).toLowerCase(),
                    containsString(price));
            DataManager.getData().errorCollector.checkThat("Vehicle milage is not the expected one",
                    DataManager.getData().existingOffer.get(3).toLowerCase(),
                    containsString(mileage));
            DataManager.getData().errorCollector.checkThat("Contract duration is not the expected one",
                    DataManager.getData().existingOffer.get(3).toLowerCase(),
                    containsString(duration));
        } else {
            try {
                DataManager.getData().errorCollector.checkThat("Project is not the expected one",
                        ClientCardPage.getProjectField().getAttribute("innerHTML"),
                        equalTo(DataManager.getData().leadInformations.get(0)));
            } catch (Exception e){}

            try {
                DataManager.getData().errorCollector.checkThat("Project deadline is not the expected one",
                        ClientCardPage.getProjectDeadlineField().getAttribute("innerHTML"),
                        equalTo(DataManager.getData().leadInformations.get(1)));
            } catch (Exception e){}

            try {
                DataManager.getData().errorCollector.checkThat("Funding model is not the expected one",
                        ClientCardPage.getFundingModelField().getAttribute("innerHTML"),
                        equalTo(DataManager.getData().leadInformations.get(2)));
            } catch (Exception e){}

            try {
                DataManager.getData().errorCollector.checkThat("Fleet size is not the expected one",
                        ClientCardPage.getFleetSizeField().getAttribute("innerHTML"),
                        equalTo(DataManager.getData().leadInformations.get(3)));
            } catch (Exception e){}

            try {
                DataManager.getData().errorCollector.checkThat("Vehicle model is not the expected one",
                        ClientCardPage.getVehicleModelField().getAttribute("innerHTML"),
                        equalTo(DataManager.getData().leadInformations.get(4)));
            } catch (Exception e){}

            try {
                DataManager.getData().errorCollector.checkThat("Contract Duration is not the expected one",
                        ClientCardPage.getContractDurationField().getAttribute("innerHTML"),
                        equalTo(DataManager.getData().leadInformations.get(5)));
            } catch (Exception e){}

            try {
                DataManager.getData().errorCollector.checkThat("Trigger event is not the expected one",
                        ClientCardPage.getTriggerEventField().getAttribute("innerHTML"),
                        equalTo(DataManager.getData().leadInformations.get(6)));
            } catch (Exception e){}

            try {
                DataManager.getData().errorCollector.checkThat("Annual mileage is not the expected one",
                        ClientCardPage.getMileageField().getAttribute("innerHTML"),
                        equalTo(DataManager.getData().leadInformations.get(7)));
            } catch (Exception e){}

            try {
                DataManager.getData().errorCollector.checkThat("Lead detail is not the expected one",
                        ClientCardPage.getLeadDetailsField().getAttribute("innerHTML"),
                        equalTo(DataManager.getData().leadInformations.get(8)));
            } catch (Exception e){}

            try {
                DataManager.getData().errorCollector.checkThat("Files does not contain the expected one",
                        ClientCardPage.getDocumentsListField().getAttribute("innerHTML"),
                        containsString("document.png"));
            } catch (Exception e){}

            try {
                DataManager.getData().errorCollector.checkThat("Lead detail is not the expected one",
                        ClientCardPage.getBankAdviserEmailField().getAttribute("innerHTML"),
                        equalTo(DataManager.getData().used_account));
            } catch (Exception e){}
        }
        DriverManager.getDriver().driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("I can see the saved lead on the client page")
    public static void checkSavedLeadOnClientPage() throws Exception{
        ElementSteps.makeVisible(ClientCardPage.getOpenedLeadDetailsBlock(), true);
        GenericSteps.screenShot();
        DataManager.getData().errorCollector.checkThat("Edit link is not visible",
                ClientCardPage.getEditLeadLink().isDisplayed(),
                equalTo(true));
        checkLeadOnClientPage();
    }

    @When("I modify the lead")
    public void modifySavedLead() throws Exception{
        ElementSteps.makeVisible(ClientCardPage.getEditLeadLink(), false);
        ClientCardPage.getEditLeadLink().click();
        Thread.sleep(5000);
    }
}
