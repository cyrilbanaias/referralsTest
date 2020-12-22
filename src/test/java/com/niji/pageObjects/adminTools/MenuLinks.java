package com.niji.pageObjects.adminTools;

import com.niji.factory.DriverManager;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.utils.NewBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MenuLinks {

    public static WebElement getPartnersMenuLink(){
        ElementSteps.waitForVisibilityOfElement(NewBy.jhitranslate("global.menu.config.partners"), 5);
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("global.menu.config.partners"));
    }
}
