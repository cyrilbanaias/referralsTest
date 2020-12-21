package com.niji.pageObjects.adminTools;

import com.niji.factory.DriverManager;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.utils.NewBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProfilePage {

    public static WebElement getProfileMenuLink(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@role=\"tab\"]"), 5);
        return DriverManager.getDriver().driver.findElements(By.xpath("//*[@role=\"tab\"]")).get(1);
    }

    public static List<WebElement> getProfilesTableLines(){
        return DriverManager.getDriver().driver.findElements(By.xpath("//tbody//tr"));
    }

    public static WebElement getProfileTableLine(String profilCode){
        return DriverManager.getDriver().driver.findElement(By.xpath("//td[text()=\"" + profilCode + "\"]/.."));
    }

    public static WebElement getProfileEditButton(WebElement tableLine){
        return tableLine.findElement(NewBy.classe("btn btn-primary"));
    }

    public static WebElement getProfileDeleteButton(WebElement tableLine){
        return tableLine.findElement(NewBy.classe("btn btn-danger"));
    }

    public static WebElement getCreateProfileButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@jhitranslate=\"frontendadminApp.profiles.createButton\"]/.."));
    }

    public static WebElement getNewProfileCodeField(){
        return DriverManager.getDriver().driver.findElement(By.id("field_code"));
    }

    public static WebElement getNewProfilTitleField(){
        return DriverManager.getDriver().driver.findElement(By.id("field_name"));
    }

    public static List<WebElement> getAccessSwitchButtons(){
        return DriverManager.getDriver().driver.findElements(By.xpath("//*[@name=\"profileDetailsForm\"]//*[@class=\"ui-inputswitch-slider\"]"));
    }

    public static WebElement getSaveButton(){
        return DriverManager.getDriver().driver.findElement(By.id("save-entity"));
    }
}
