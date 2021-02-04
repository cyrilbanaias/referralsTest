package com.niji.pageObjects.adminTools;

import com.niji.factory.DriverManager;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.utils.NewBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InsertionPage {

    public static WebElement getInsertionMenuLink(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@role=\"tab\"]"), 5);
        return DriverManager.getDriver().driver.findElements(By.xpath("//*[@role=\"tab\"]")).get(8);
    }

    public static WebElement getActivateNewsPageButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"activate\"]//*[@class=\"ui-inputswitch-slider\"]"));
    }

    public static WebElement getCreateInsertionButton(){
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("frontendadminApp.backendadminAddInsert.home.createLabel"));
    }

    public static WebElement getCustomRadioButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@value=\"CUSTOM\"]//*[contains(@class,\"ui-radiobutton-icon\")]"));
    }

    public static WebElement getPopupSwitchButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"field_popup\"]//*[@class=\"ui-inputswitch-slider\"]"));
    }

    public static WebElement getRecommandSwitchButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"field_recommanded\"]//*[@class=\"ui-inputswitch-slider\"]"));
    }

    public static WebElement getTitleField(){
        return DriverManager.getDriver().driver.findElement(By.id("field_title"));
    }

    public static WebElement getSubTitleField(){
        return DriverManager.getDriver().driver.findElement(By.id("field_subTitle"));
    }

    public static WebElement getUploadDescriptiveImageButton(){
        return DriverManager.getDriver().driver.findElement(By.id("id-input-file"));
    }

    public static WebElement getUploadEmailImageButton(){
        return DriverManager.getDriver().driver.findElement(By.id("id-input-extra-file"));
    }

    public static WebElement getDescriptionField(){
        return DriverManager.getDriver().driver.findElement(By.id("field_description"));
    }

    public static WebElement getActionNameField(){
        return DriverManager.getDriver().driver.findElement(By.id("field_actionName"));
    }

    public static WebElement getActionURLField(){
        return DriverManager.getDriver().driver.findElement(By.id("field_actionUrl"));
    }

    public static WebElement getHomePageSwitchButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"field_homePage\"]//*[@class=\"ui-inputswitch-slider\"]"));
    }

    public static WebElement getNewsBodyPageSwitchButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"field_newsInfoPageBody\"]//*[@class=\"ui-inputswitch-slider\"]"));
    }

    public static WebElement getNewsColumnPageSwitchButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"field_newsInfoPageColumn\"]//*[@class=\"ui-inputswitch-slider\"]"));
    }

    public static WebElement getEnterLeadPageSwtichButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"field_enterLeadPage\"]//*[@class=\"ui-inputswitch-slider\"]"));
    }

    public static WebElement getActivateSwitchButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"field_active\"]//*[@class=\"ui-inputswitch-slider\"]"));
    }

    public static WebElement getSaveButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@jhitranslate=\"frontendadminApp.backendadminAddInsert.home.createOrEditLabel\"]/..//*[@id=\"save-entity\"]"));
    }

    public static WebElement getConfirmPopopButton(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[contains(@class,\"dialog-footer\")]/button"));
    }

    public static WebElement getInsertionTableLine(String insertion){
        return DriverManager.getDriver().driver.findElement(By.xpath("//div[text()=\"title_" + insertion + "\"]/.."));
    }

    public static List<WebElement> getInsertionTableTitles(){
        return DriverManager.getDriver().driver.findElements(By.xpath("//p-orderlist[not(@header=\"Arval Contacts\")]//*[contains(@class,\"ui-orderlist-item\")]/div/div[3]"));
    }

    public static List<WebElement> getModifyButtons(){
        return DriverManager.getDriver().driver.findElements(By.xpath("//jhi-config-add-insert//*[@jhitranslate=\"entity.action.edit\"]/.."));
    }
}
