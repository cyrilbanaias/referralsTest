package com.niji.steps.globalSteps;

import com.niji.factory.DriverManager;
import com.niji.utils.NewBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ClientCardPage {

    // Client card
    public static WebElement getCompanyNameArea(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"thirdPartyName\"]/span"));
    }

    public static WebElement getComppanyNumberField(){
        return DriverManager.getDriver().driver.findElement(By.id("thirdPartyRegistrationNumber"));
    }

    // Leads
    public static WebElement getOngoingLeadTab(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@data-icon=\"comments\"]/../.."));
    }

    public static List<WebElement> getLeadsLinks(){
        return DriverManager.getDriver().driver.findElements(By.xpath("//*[@aria-controls=\"needBlock\"]"));
    }

    public static WebElement getLastLeadLink(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@aria-controls=\"needBlock\"]"));
    }

    // Lead details
    public static WebElement getOpenedLeadDetailsBlock(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//a[@aria-expanded=\"true\"]/../div[@id=\"needBlock\"]"));
    }

    public static WebElement getProjectField(){
        return getOpenedLeadDetailsBlock().findElement(By.xpath(".//*[@name=\"leadDetailProject\"]"));
    }

    public static WebElement getProjectDeadlineField(){
        return getOpenedLeadDetailsBlock().findElement(By.xpath(".//*[@name=\"leadDetailDeadLine\"]"));
    }

    public static WebElement getFundingModelField(){
        return getOpenedLeadDetailsBlock().findElement(By.xpath(".//*[@name=\"leadDetailFundMode\"]"));
    }

    public static WebElement getTriggerEventField(){
        return getOpenedLeadDetailsBlock().findElement(By.xpath(".//*[@name=\"leadDetailEvent\"]"));
    }

    public static WebElement getFleetSizeField(){
        return getOpenedLeadDetailsBlock().findElement(By.xpath(".//*[@name=\"leadDetailCompanyFleetSize\"]"));
    }

    public static WebElement getVehicleModelField(){
        return getOpenedLeadDetailsBlock().findElement(By.xpath(".//*[@name=\"leadDetailVehicleModel\"]"));
    }

    public static WebElement getContractDurationField(){
        return getOpenedLeadDetailsBlock().findElement(By.xpath(".//*[@name=\"leadDetailContractDuration\"]"));
    }

    public static WebElement getMileageField(){
        return getOpenedLeadDetailsBlock().findElement(By.xpath(".//*[@name=\"leadDetailAnnualMileage\"]"));
    }

    public static WebElement getLeadDetailsField(){
        return getOpenedLeadDetailsBlock().findElement(By.xpath(".//*[@name=\"leadDetailDescription\"]"));
    }

    public static WebElement getDocumentsListField(){
        return getOpenedLeadDetailsBlock().findElement(By.xpath(".//*[@jhitranslate=\"client-card.clientNeedsBlock.formulatedLead.attachedDocument\"]/..//*[@class=\"list-group list-group-flush\"]"));
    }

    public static WebElement getBankAdviserEmailField(){
        return getOpenedLeadDetailsBlock().findElement(By.xpath(".//*[@jhitranslate=\"client-card.clientNeedsBlock.formulatedLead.bankAdvisor\"]/../span/span"));
    }

    public static WebElement getEditLeadLink(){
        return getOpenedLeadDetailsBlock().findElement(NewBy.jhitranslate("client-card.clientNeedsBlock.formulatedLead.edit"));
    }
}
