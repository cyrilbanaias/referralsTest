package com.niji.pageObjects.salesforce;

import com.niji.factory.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {

    public static WebElement getUSernameField(){
        return DriverManager.getDriver().driver.findElement(By.id("username"));
    }

    public static WebElement getPasswordField(){
        return DriverManager.getDriver().driver.findElement(By.id("password"));
    }

    public static WebElement getConnectionButton(){
        return DriverManager.getDriver().driver.findElement(By.id("Login"));
    }
}
