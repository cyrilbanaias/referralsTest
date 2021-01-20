package com.niji.pageObjects.salesforce;

import com.niji.factory.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MenuLinks {

    public static WebElement getLeadMenuLink(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@data-id=\"Lead\"]/a"));
    }

    public static WebElement getLeadMoreMenuLink(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@data-id=\"Lead\"]//a[@one-appnavbarmenubutton_appnavbarmenubutton]"));
    }

    public static WebElement getLeadQueueSubMenuLink(String queueName){
        return DriverManager.getDriver().driver.findElement(By.xpath("//one-app-nav-bar-menu-item//*[contains(text(),\"" + queueName + "\")]"));
    }
}
