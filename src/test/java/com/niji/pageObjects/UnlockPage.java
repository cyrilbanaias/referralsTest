package com.niji.pageObjects;

import com.niji.factory.DriverManager;
import org.openqa.selenium.WebElement;

public class UnlockPage {

    public static WebElement getZeroTouch(){
        return DriverManager.getDriver().driverMobile.findElementByXPath("//*[@text='0']");
    }

    public static WebElement getOKTouch(){
        return DriverManager.getDriver().driverMobile.findElementByXPath("//*[@text='OK']");
    }
}
