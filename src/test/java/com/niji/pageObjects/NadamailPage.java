package com.niji.pageObjects;

import com.niji.factory.DriverManager;
import com.niji.utils.NewBy;
import org.openqa.selenium.WebElement;

public class NadamailPage {

    public static WebElement getAddAcountButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.classe("icon-plus"));
    }

    public static WebElement getEmailField(){
        return DriverManager.getDriver().driver.findElement(NewBy.classe("user_name"));
    }

    public static WebElement getAcceptButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.classe("button success"));
    }

    public static WebElement getConfirmLink(){
        return DriverManager.getDriver().driver.findElement(NewBy.partialText("ACTIVEZ VOTRE COMPTE"));
    }

    public static WebElement getConfirmEmailModificationLink(){
        return DriverManager.getDriver().driver.findElement(NewBy.partialText("Je confirme la modification de mon adresse e-mail"));
    }
}
