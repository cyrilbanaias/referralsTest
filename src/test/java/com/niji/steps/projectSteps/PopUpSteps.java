package com.niji.steps.projectSteps;

import com.niji.factory.DriverManager;
import com.niji.steps.globalSteps.ElementSteps;
import org.openqa.selenium.By;

public class PopUpSteps {

    public static void closePoUp(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@class=\"modal-content\"]//*[@class=\"close\"]"), 5);
        DriverManager.getDriver().driver.findElement(By.xpath("//*[@class=\"modal-content\"]//*[@class=\"close\"]")).click();
    }
}
