package com.niji.pageObjects.adminTools;

import com.niji.factory.DriverManager;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.utils.NewBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UserPage {

    public static WebElement getUserMenuLink(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@role=\"tab\"]"), 5);
        return DriverManager.getDriver().driver.findElements(By.xpath("//*[@role=\"tab\"]")).get(3);
    }

    public static WebElement getCreateUserButton(){
        ElementSteps.waitForVisibilityOfElement(NewBy.jhitranslate("config.user.button.create"), 5);
        return DriverManager.getDriver().driver.findElement(NewBy.jhitranslate("config.user.button.create"));
    }

    public static WebElement getRolenOpenListButton(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@id=\"field_roles\"]/div"), 5);
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"field_roles\"]/div"));
    }

    public static WebElement getSpecificRoleElement(String role){
        return DriverManager.getDriver().driver.findElement(By.xpath("//span[text()=\"" + role + "\"]"));
    }

    public static WebElement getSaveUserButton(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//jhi-config-user-update//*[@id=\"save-entity\"]"), 5);
        return DriverManager.getDriver().driver.findElement(By.xpath("//jhi-config-user-update//*[@id=\"save-entity\"]"));
    }

    public static WebElement getUserEmailInputField(){
        return DriverManager.getDriver().driver.findElement(By.id("field_email"));
    }

    public static WebElement getUserFirstnameInputField(){
        return DriverManager.getDriver().driver.findElement(By.id("field_firstName"));
    }

    public static WebElement getUserLastnameInputField(){
        return DriverManager.getDriver().driver.findElement(By.id("field_lastName"));
    }

    public static WebElement getUserAgentcodeInputField(){
        return DriverManager.getDriver().driver.findElement(By.id("field_agentCode"));
    }

    public static WebElement getLangKeySelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("field_langkey"));
    }

    public static WebElement getUserLanguageSelectList(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@id=\"field_langkey\"]/div"), 5);
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"field_langkey\"]/div"));
    }

    public static WebElement getPartnersManagedSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("field_partners"));
    }

    public static WebElement getUserPartnersManagedSelectList(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@id=\"field_partners\"]/div"), 5);
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"field_partners\"]/div"));
    }

    public static WebElement getRolesSelectList(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"field_roles\"]/div"));
    }

    public static WebElement getUserRolesSelectList(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//p-multiselectitem//div[contains(@class,\"ui-chkbox\")]"), 5);
        return DriverManager.getDriver().driver.findElement(By.xpath("//p-multiselectitem//div[contains(@class,\"ui-chkbox\")]"));
    }

//    credit analyser
//    public static WebElement getGDPRCheckBox(){
//        return DriverManager.getDriver().driver.findElement(NewBy.forAttribute("rgpd"));
//    }

    public static WebElement getSegmentationSelectList(){
        return DriverManager.getDriver().driver.findElement(By.id("field_segmentation"));
    }

    public static WebElement getUserSegmentationSelectList(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("//*[@id=\"field_segmentation\"]/div"), 5);
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"field_segmentation\"]/div"));
    }

    public static WebElement getOrganisationSelectList(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"field_organisation\"][1]"));
    }

    public static WebElement getUserOrganisationSelectList(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("(//*[@id=\"field_organisation\"]/div)[1]"), 5);
        return DriverManager.getDriver().driver.findElement(By.xpath("(//*[@id=\"field_organisation\"]/div)[1]"));
    }

    public static WebElement getOrganisationL1SelectList(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//*[@id=\"field_organisation\"][2]"));
    }

    public static WebElement getUserOrganisationL1SelectList(){
        ElementSteps.waitForVisibilityOfElement(By.xpath("(//*[@id=\"field_organisation\"]/div)[2]"), 5);
        return DriverManager.getDriver().driver.findElement(By.xpath("(//*[@id=\"field_organisation\"]/div)[2]"));
    }

    public static WebElement getSelectList(WebElement listElement)   {
        return listElement.findElement(By.xpath(".//p-dropdownitem"));
    }


}
