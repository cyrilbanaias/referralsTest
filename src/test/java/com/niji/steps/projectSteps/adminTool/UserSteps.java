package com.niji.steps.projectSteps.adminTool;

import com.niji.data.DataManager;
import com.niji.factory.DriverManager;
import com.niji.pageObjects.LeadCreationPage;
import com.niji.pageObjects.adminTools.ProfilePage;
import com.niji.pageObjects.adminTools.UserPage;
import com.niji.pageObjects.adminTools.UserPage;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.steps.globalSteps.GenericSteps;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserSteps {

    @When("I open the user page")
    public void openUsersPage() throws Exception {
        ElementSteps.makeVisible(UserPage.getUserMenuLink(), false);
        UserPage.getUserMenuLink().click();
        GenericSteps.screenShot();
    }

    @When("I create a new user")
    public void createNewUser() throws Exception{
        DataManager.getData().newUser = "";

        UserPage.getCreateUserButton().click();

        DataManager.getData().userInformations = new ArrayList<>();

        Actions action = new Actions(DriverManager.getDriver().driver);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        action.sendKeys(UserPage.getUserEmailInputField(), formatter.format(new Date()) + "@testautomation.com").build().perform();
        action.sendKeys(UserPage.getUserFirstnameInputField(), "Firstname-" + formatter.format(new Date())).build().perform();
        action.sendKeys(UserPage.getUserLastnameInputField(), "Lastname-" + formatter.format(new Date())).build().perform();
        action.sendKeys(UserPage.getUserAgentcodeInputField(), "Agentcode-" + formatter.format(new Date())).build().perform();

        try {
            UserPage.getUserLanguageSelectList().click();
            UserPage.getSelectList(UserPage.getLangKeySelectList()).click();
        } catch (Exception e){
            DataManager.getData().userInformations.add("null");
        }

        try {
            UserPage.getUserPartnersManagedSelectList().click();
            UserPage.getSelectList(UserPage.getPartnersManagedSelectList()).click();
        } catch (Exception e){
            DataManager.getData().userInformations.add("null");
        }

//        try {
//            UserPage.getRolesSelectList().click();
//            UserPage.getSelectList(UserPage.getUserRolesSelectList()).click();
//        } catch (Exception e){
//            DataManager.getData().userInformations.add("null");
//        }

        try {
            UserPage.getUserSegmentationSelectList().click();
            UserPage.getSelectList(UserPage.getSegmentationSelectList()).click();
        } catch (Exception e){
            DataManager.getData().userInformations.add("null");
        }

        try {
            UserPage.getUserOrganisationSelectList().click();
            UserPage.getSelectList(UserPage.getOrganisationSelectList()).click();
        } catch (Exception e){
            DataManager.getData().userInformations.add("null");
        }

//        try {
//            UserPage.getUserOrganisationL1SelectList().click();
//            UserPage.getSelectList(UserPage.getOrganisationL1SelectList()).click();
//        } catch (Exception e){
//            DataManager.getData().userInformations.add("null");
//        }
        GenericSteps.screenShot();

        // Save profile
        UserPage.getSaveUserButton().click();
        GenericSteps.screenShot();
    }

}
