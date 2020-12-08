package com.niji.pageObjects;

import com.niji.factory.DriverManager;
import com.niji.steps.globalSteps.ElementSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DatePicker {

    public static WebElement getMonthSelectList(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@title=\"Select month\"]"),5);
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@title=\"Select month\"]"));
    }

    public static WebElement getYearSelectList(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@title=\"Select year\"]"),5);
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@title=\"Select year\"]"));
    }

    public static List<WebElement> getActiveDaysButtons(){
        return DriverManager.getDriver().driver.findElements(By.xpath("//*[contains(@class,\"btn-light\") and not(contains(@class,\"text-muted outside\"))]"));
    }

    public static WebElement getActiveDayButton(String day){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[contains(@class,\"btn-light\") and not(contains(@class,\"text-muted outside\")) and text()=\""+day+"\"]"));
    }

    public static WebElement getOpenCalendarButton(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@data-icon=\"calendar-alt\"]/../.."),10);
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@data-icon=\"calendar-alt\"]/../.."));
    }

    public static WebElement getHourField(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@aria-label=\"Hours\"]"),10);
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@aria-label=\"Hours\"]"));
    }

    public static WebElement getMinutesField(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@aria-label=\"Minutes\"]"),10);
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@aria-label=\"Minutes\"]"));
    }
}
