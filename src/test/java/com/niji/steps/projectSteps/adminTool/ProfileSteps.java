package com.niji.steps.projectSteps.adminTool;

import com.niji.pageObjects.adminTools.ProfilePage;
import com.niji.steps.globalSteps.ElementSteps;
import cucumber.api.java.en.When;

public class ProfileSteps {

    @When("I open the profiles page")
    public void openProfilesPage(){
        ElementSteps.makeVisible(ProfilePage.getProfileMenuLink(), false);
        ProfilePage.getProfileMenuLink().click();
    }
}
