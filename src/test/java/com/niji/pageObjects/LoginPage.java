package com.niji.pageObjects;

import com.niji.factory.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {

    public static WebElement getUserEmailField(){
        return DriverManager.getDriver().driver.findElement(By.id("username"));
    }

    public static WebElement getPasswordField(){
        return DriverManager.getDriver().driver.findElement(By.id("password"));
    }

    public static WebElement getConnectionButton(){
        return DriverManager.getDriver().driver.findElement(By.id("btnSubmit"));
    }

    public static WebElement getErrorMessage(){
        return DriverManager.getDriver().driver.findElement(By.id("error-msg"));
    }

    public static WebElement getEyeIcon(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[contains(@class, \"password-trigger\")]"));
    }
}
