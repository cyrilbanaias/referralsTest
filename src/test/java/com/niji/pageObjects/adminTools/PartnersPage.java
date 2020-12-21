package com.niji.pageObjects.adminTools;

import com.niji.factory.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.dnd.DragGestureEvent;
import java.util.List;

public class PartnersPage {

    public static List<WebElement> getPartnersTableLines(){
        return DriverManager.getDriver().driver.findElements(By.xpath("//tbody//tr"));
    }

    public static WebElement getPartnerTableLine(String partnerCode){
        return DriverManager.getDriver().driver.findElement(By.xpath("//td[text()=\"" + partnerCode + "\"]/.."));
    }

    public static WebElement getPartnerEditButton(WebElement tableLine){
        return tableLine.findElement(By.xpath(".//button"));
    }
}
