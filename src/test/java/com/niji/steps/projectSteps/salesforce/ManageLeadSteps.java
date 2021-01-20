package com.niji.steps.projectSteps.salesforce;

import com.niji.factory.DriverManager;
import com.niji.pageObjects.salesforce.MenuLinks;
import com.niji.steps.globalSteps.ElementSteps;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class ManageLeadSteps {

    @When("I open the lead queue \"(.+)\"")
    public void openLeadQueue(String queueName){
        MenuLinks.getLeadMoreMenuLink().click();
        ElementSteps.waitForVisibilityOfElement(MenuLinks.getLeadQueueSubMenuLink(queueName), 5);
        ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click()", MenuLinks.getLeadQueueSubMenuLink(queueName));
        ElementSteps.waitForVisibilityOfElement(By.xpath("//table"), 10);
    }
}
