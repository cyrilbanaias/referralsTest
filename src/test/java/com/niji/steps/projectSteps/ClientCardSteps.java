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
    public void checkLeadOnClientPage() throws Exception{
        DataManager.getData().errorCollector.checkThat("Company number is not the expected one",
                ClientCardPage.getCompanyNameArea().getAttribute("innerHTML"),
                equalTo(DataManager.getData().company_name));
        DataManager.getData().errorCollector.checkThat("Company number is not the expected one",
                ClientCardPage.getComppanyNumberField().getText(),
                equalTo(DataManager.getData().company_number));

        ElementSteps.makeVisible(ClientCardPage.getOpenedLeadDetailsBlock(), true);
        GenericSteps.screenShot();
        DriverManager.getDriver().driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        // TODO si info lead = existing_account alors faire un check sur le champ description
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
        DriverManager.getDriver().driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
