package com.niji.steps.projectSteps.adminTool;

import com.niji.data.DataManager;
import com.niji.factory.DriverManager;
import com.niji.pageObjects.adminTools.ProfilePage;
import com.niji.pageObjects.adminTools.UserPage;
import com.niji.steps.globalSteps.BrowserSteps;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.steps.globalSteps.GenericSteps;
import com.niji.utils.GenerateData;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProfileSteps {

    @When("I open the profiles page")
    public void openProfilesPage(){
        ElementSteps.makeVisible(ProfilePage.getProfileMenuLink(), false);
        ProfilePage.getProfileMenuLink().click();
    }

    @When("I create a new profile")
    public void createNewProfil() throws Exception{
        DataManager.getData().newProfile = "";

        ProfilePage.getCreateProfileButton().click();

        Actions action = new Actions(DriverManager.getDriver().driver);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        DataManager.getData().newProfile = DataManager.getData().newProfile + formatter.format(new Date());
        action.sendKeys(ProfilePage.getNewProfileCodeField(), DataManager.getData().newProfile).build().perform();
        action.sendKeys(ProfilePage.getNewProfilTitleField(), DataManager.getData().newProfile).build().perform();

        // Choose random access
        List<WebElement> accessSwitches = ProfilePage.getAccessSwitchButtons();
        // Switch off all the access
        for (WebElement access:accessSwitches){
            ElementSteps.makeVisible(access,false);
            access.click();
            Thread.sleep(500);
        }
        // Choose a random number of access
        int randomAccess = (int)(Math.random() * accessSwitches.size()-1);
        // Click on random access until define number
        for (int i = 0; i < randomAccess; i++){
            int index = (int)(Math.random() * accessSwitches.size()-1);
            WebElement access = accessSwitches.get(index);
            ElementSteps.makeVisible(access,false);
            access.click();
            accessSwitches.remove(access);
            Thread.sleep(500);
        }
        // Save profile
        ProfilePage.getSaveButton().click();
    }

    @When("the new profile is displayed in the list")
    public void checkNewProfile() throws Exception{
        try {
            ProfilePage.getProfileTableLine(DataManager.getData().newProfile);
        } catch (Exception e){
            GenericSteps.screenShot();
            throw new Exception("The new profil is not displayed in the list");
        }
    }

    @When("the new profile is available in the user creation page")
    public void checkNewProfilInUserCreation() throws Exception{
        ElementSteps.makeVisible(UserPage.getUserMenuLink(), false);
        UserPage.getUserMenuLink().click();
        UserPage.getCreateUserButton().click();
        UserPage.getRolenOpenListButton().click();
        try {
            UserPage.getSpecificRoleElement(DataManager.getData().newProfile);
        } catch (Exception e){
            GenericSteps.screenShot();
            DataManager.getData().errorCollector.addError(new Throwable("The new profiles is not available in the role list of user creation"));
        }
    }

    @When("I delete this profile")
    public void deleteNewProfile() throws Exception{
        WebElement tableLine = ProfilePage.getProfileTableLine(DataManager.getData().newProfile);
        ProfilePage.getProfileDeleteButton(tableLine).click();
        Thread.sleep(500);
        BrowserSteps.validateModal();
    }

    @When("the new profile is not displayed anymore in the list")
    public void checkDeletionNewProfile(){
        try {
            ProfilePage.getProfileTableLine(DataManager.getData().newProfile);
            GenericSteps.screenShot();
            throw new Exception("The new profil is still displayed in the list");
        } catch (Exception e){
            // DO NOTHIN IF THE PROFILE IS NOT FOUND
        }
    }
}
