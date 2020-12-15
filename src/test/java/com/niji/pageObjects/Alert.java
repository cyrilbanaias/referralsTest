package com.niji.pageObjects;

import com.niji.factory.DriverManager;
import com.niji.utils.NewBy;
import org.openqa.selenium.WebElement;

public class Alert {

    public static WebElement getSuccessAlert(){
        return DriverManager.getDriver().driver.findElement(NewBy.partialClass("alert-success"));
    }
}
