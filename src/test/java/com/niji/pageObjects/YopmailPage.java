package com.niji.pageObjects;

import com.niji.factory.DriverManager;
import com.niji.utils.NewBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class YopmailPage {

    public static WebElement getLoginField(){
        return DriverManager.getDriver().driver.findElement(By.id("login"));
    }

    public static WebElement getVerifyEmailButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.value("Vérifier les mails"));
    }

    public static WebElement getRefreshButton(){
        return DriverManager.getDriver().driver.findElement(By.cssSelector("span.slientext"));
    }
}
