package com.niji.pageObjects.adminTools;

import com.niji.factory.DriverManager;
import com.niji.steps.globalSteps.ElementSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UserPage {

    public static WebElement getUserMenuLink(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@role=\"tab\"]"), 5);
        return DriverManager.getDriver().driver.findElements(By.xpath("//*[@role=\"tab\"]")).get(3);
    }

    public static WebElement getCreateUserButton(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@jhitranslate=\"frontendadminApp.backendadminUser.home.createLabel\"]/.."), 5);
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@jhitranslate=\"frontendadminApp.backendadminUser.home.createLabel\"]/.."));
    }

    public static WebElement getRolenOpenListButton(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@id=\"field_roles\"]/div"), 5);
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"field_roles\"]/div"));
    }

    public static WebElement getSpecificRoleElement(String role){
        return DriverManager.getDriver().driver.findElement(By.xpath("//span[text()=\"" + role + "\"]"));
    }
}
