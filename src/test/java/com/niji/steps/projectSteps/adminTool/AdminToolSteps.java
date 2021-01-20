package com.niji.steps.projectSteps.adminTool;

import com.niji.pageObjects.LoginPage;
import com.niji.pageObjects.adminTools.MenuLinks;
import com.niji.pageObjects.adminTools.PartnersPage;
import com.niji.steps.globalSteps.ElementSteps;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

public class AdminToolSteps {

    @When("I open the partners configuration page")
    public void openPartnersConfigPage(){
        MenuLinks.getPartnersMenuLink().click();
    }

    @When("I edit the partner \"(.+)\"")
    public void editPartner(String partnerCode){
        WebElement partnerLine = PartnersPage.getPartnerTableLine(partnerCode);
        WebElement editButton = PartnersPage.getPartnerEditButton(partnerLine);
        ElementSteps.makeVisible(editButton, true);
        editButton.click();
    }

    @When("I disconnect from admin tool")
    public void disconnect(){
        ElementSteps.makeVisible(MenuLinks.getAccountMenuLink(), true);
        MenuLinks.getAccountMenuLink().click();
        ElementSteps.waitForVisibilityOfElement(MenuLinks.getLogoutLink(), 5);
        MenuLinks.getLogoutLink().click();
        ElementSteps.waitForVisibilityOfElement(LoginPage.getUserEmailField(), 20);
    }
}
