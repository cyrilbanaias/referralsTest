package com.niji.pageObjects;

import com.niji.factory.DriverManager;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.utils.NewBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.sql.Driver;
import java.util.List;

public class SearchLeadPage {

    public static WebElement getGlobalFilterField(){
        return DriverManager.getDriver().driver.findElement(By.id("globalFilter"));
    }

    public static WebElement getResetFiltersButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("entity.action.reset"));
    }

    public static WebElement getSearchBurron(){
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("entity.action.search"));
    }

    public static WebElement getAllResultsRadioButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("lead-table.radio.all"));
    }

    public static WebElement getProfessionnalResultsRadioButton() {
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("lead-table.radio.professional"));
    }

    public static WebElement getPersonnalResultsRadioButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("lead-table.radio.private"));
    }

    public static WebElement getDownloadButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@data-icon=\"arrow-to-bottom\"]/../.."));
    }

    public static WebElement getResultsNumberLink(){
        return DriverManager.getDriver().driver.findElement(By.id("dropdownMenuLink"));
    }

    public static List<WebElement> getResultsNumbersLinks(){
        return DriverManager.getDriver().driver.findElements(NewBy.classe("py-1 dropdown-item"));
    }

    public static List<WebElement> getResultsLines(){
        ElementSteps.waitForPresenceOfElement(By.xpath("//tbody//tr"), 10);
        return DriverManager.getDriver().driver.findElements(By.xpath("//tbody//tr"));
    }

    public static WebElement getMoreCriteriaLink(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@jhitranslate=\"follow.criterias.more\"]/.."));
    }

    public static WebElement getLessCriteriaLink(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@jhitranslate=\"follow.criterias.less\"]/.."));
    }

    public static WebElement getStatusButton(String status){
        return DriverManager.getDriver().driver.findElement(NewBy.text(status));
    }
}
