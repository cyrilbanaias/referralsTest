package com.niji.steps.globalSteps;

import com.niji.factory.DriverManager;
import com.niji.pageObjects.YopmailPage;
import com.niji.utils.NewBy;
import org.openqa.selenium.By;

public class YopmailSteps {

    public static void waitForEmail(int nombreAttendu) throws Exception{
        DriverManager.getDriver().driver.switchTo().frame("ifinbox");
        int nombreActuel = DriverManager.getDriver().driver.findElements(NewBy.classe("lm")).size();
        int i = 0;
        while (nombreActuel < nombreAttendu && i < 20){
            Thread.sleep(10000);
            DriverManager.getDriver().driver.switchTo().defaultContent();
            YopmailPage.getRefreshButton().click();
            DriverManager.getDriver().driver.switchTo().frame("ifinbox");
            nombreActuel = DriverManager.getDriver().driver.findElements(By.xpath("//a[@class=\"lm\"]")).size();
            i++;
        }
        DriverManager.getDriver().driver.switchTo().defaultContent();
    }
}
