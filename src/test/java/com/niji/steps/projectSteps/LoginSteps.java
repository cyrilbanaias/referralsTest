package com.niji.steps.projectSteps;

import com.niji.data.DataManager;
import com.niji.factory.CapabilitiesManager;
import com.niji.factory.DriverManager;
import com.niji.pageObjects.LoginPage;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.steps.globalSteps.GenericSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.interactions.Actions;

public class LoginSteps {

    @Then("an error login message is displayed")
    public void errorMessageIsDisplayed() throws Exception{
        if(CapabilitiesManager.getCapabilities().capabilities.getCapability("platformName").equals("ios_web")){
            Thread.sleep(10000);
        }
        try {
            ElementSteps.waitForVisibilityOfElement(LoginPage.getErrorMessage(),30);
        } catch (Exception e){
            GenericSteps.screenShot();
            throw new Exception("Error login message is not displayed");
        }
    }

    @When("I click on eye icon")
    public void clickOnEyeIcon(){
        LoginPage.getEyeIcon().click();
    }

    @Then("password must be displayed")
    public void passwordMustBeDisplayed() throws Exception {
        if(!LoginPage.getPasswordField().getAttribute("type").equals("text")){
            GenericSteps.screenShot();
            throw new Exception("Password must be displayed");
        }
    }

    @Then("password must be hidden")
    public void passwordMustBeHidden() throws Exception {
        if(!LoginPage.getPasswordField().getAttribute("type").equals("password")){
            GenericSteps.screenShot();
            throw new Exception("Password must be hidden");
        }
    }

    @When("I open the My profil menu")
    public void clickOnMyProfile(){
    }

    @Then("My profile dropdown is displayed")
    public void profileDropDownIsDisplayed() {
    }
}
