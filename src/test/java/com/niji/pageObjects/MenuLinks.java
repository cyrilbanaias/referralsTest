package com.niji.pageObjects;

import com.niji.factory.DriverManager;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.utils.NewBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MenuLinks {
    public static WebElement getLeadCreationMenuDropdown(){
        return DriverManager.getDriver().driver.findElement(By.id("dropdownReferralsLead"));
    }

    public static WebElement getLeadCreationMenuLink(){
        ElementSteps.waitForVisibilityOfElement(NewBy.jhitranslate("lead.title"), 5);
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("lead.title"));
    }

    public static WebElement getSearchLeadMenuLink(){
        ElementSteps.waitForVisibilityOfElement(NewBy.jhitranslate("follow.title"), 5);
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("follow.title"));
    }

    public static WebElement getAccountMenuLink(){
        return DriverManager.getDriver().driver.findElement(By.id("dropdownUserSelfcare"));
    }

    public static WebElement getLogoutMenuLink(){
        return DriverManager.getDriver().driver.findElement(By.id("logout"));
    }

    public static WebElement getNewsAndInfosMenuLink(){
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("global.menu.news"));
    }

    public static WebElement getLanguageMenuLink(){
        return DriverManager.getDriver().driver.findElement(By.id("dropdownLanguage"));
    }
}
