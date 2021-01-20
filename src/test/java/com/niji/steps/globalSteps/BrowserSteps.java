package com.niji.steps.globalSteps;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.niji.data.DataManager;
import com.niji.factory.CapabilitiesManager;
import com.niji.factory.DriverManager;
import com.niji.pageObjects.LeadCreationPage;
import com.niji.utils.NewBy;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserSteps {

    /**
     * Acces a l'URL
     * @param url
     */
    @Given("^(?:j'accede a|I am on) \"(.+)\"$")
    public static void access(String url) throws Exception {
        DataManager.getData().initialURL = url;
        DriverManager.getDriver().driver.get(DataManager.getData().returnParam(url));
        DriverManager.getDriver().driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        DriverManager.getDriver().driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        if (!CapabilitiesManager.getCapabilities().capabilities.getCapability("platformName").toString().equals("android_web")
                && !CapabilitiesManager.getCapabilities().capabilities.getCapability("platformName").toString().equals("ios_web")){
            DriverManager.getDriver().driver.manage().window().setSize(new Dimension(1920,1080));
        }
        if (CapabilitiesManager.getCapabilities().capabilities.getCapability("platformName").toString().equals("chrome")
                || CapabilitiesManager.getCapabilities().capabilities.getCapability("platformName").toString().equals("new_edge")){
            try {
                DriverManager.getDriver().driver.findElement(By.id("details-button")).click();
                DriverManager.getDriver().driver.findElement(By.id("proceed-link")).click();
            } catch (Exception e){
                
            }
        }
        Thread.sleep(5000);
    }

    @When("I refresh the browser")
    public static void refreshBrowser(){
        DriverManager.getDriver().driver.navigate().refresh();
        waitForLoad(DriverManager.getDriver().driver, 10);
    }

    @Given("^je ferme le navigateur$")
    public void close() {
        DriverManager.getDriver().stopCurrentDriver(CapabilitiesManager.getCapabilities().capabilities);
    }
    
	@When("^je valide le modal")
	public static void validateModal() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver().driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());
        DriverManager.getDriver().driver.switchTo().alert().accept();
	}
	
	@When("^j'annule le modal")
	public static void cancelModal() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver().driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());
        DriverManager.getDriver().driver.switchTo().alert().dismiss();
	}

	@When("I go back with browser button")
    public static void backButton(){
        DriverManager.getDriver().driver.navigate().back();
    }

    public static void waitForLoad(WebDriver driver, int timeOut){
        new WebDriverWait(driver, timeOut).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    @When("I open a new tab")
    public static void openNewTab(){
        DataManager.getData().activeTab = 0;
        ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("window.open()");
        ArrayList<String> tabs2 = new ArrayList<String> (DriverManager.getDriver().driver.getWindowHandles());
        DriverManager.getDriver().driver.switchTo().window(tabs2.get(1));
        DataManager.getData().activeTab = 1;
    }

    @When("I switch active tab on the browser")
    public static void switchTab(){
        ArrayList<String> tabs2 = new ArrayList<String> (DriverManager.getDriver().driver.getWindowHandles());
        if (DataManager.getData().activeTab == 1){
            DriverManager.getDriver().driver.switchTo().window(tabs2.get(0));
            DataManager.getData().activeTab = 0;
        }
        else if (DataManager.getData().activeTab == 0){
            DriverManager.getDriver().driver.switchTo().window(tabs2.get(1));
            DataManager.getData().activeTab = 1;
        }
    }

    @Then("a new tab is opened")
    public static void checkNewTab() throws Exception {
        Thread.sleep(2000);
        ArrayList<String> tabs2 = new ArrayList<String> (DriverManager.getDriver().driver.getWindowHandles());
        if(tabs2.size() == 1){
            DataManager.getData().errorCollector.addError(new Exception("No new tab has been opened."));
        } else {
            DriverManager.getDriver().driver.switchTo().window(tabs2.get(1));
            GenericSteps.screenShot();
            DriverManager.getDriver().driver.close();
            DriverManager.getDriver().driver.switchTo().window(tabs2.get(0));
        }
    }

    public static void checkNewPrintTab() throws Exception {
        Thread.sleep(5000);
        ArrayList<String> tabs2 = new ArrayList<String> (DriverManager.getDriver().driver.getWindowHandles());
        if(tabs2.size() == 1){
            DataManager.getData().errorCollector.addError(new Exception("No new tab has been opened."));
        } else {
            if(CapabilitiesManager.getCapabilities().capabilities.getCapability("platformName").toString().equals("chrome")){
                DriverManager.getDriver().driver.switchTo().window(tabs2.get(1));
                GenericSteps.screenShot();
                JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver().driver;
                executor.executeScript("return document.querySelector('print-preview-app').shadowRoot.querySelector('print-preview-sidebar').shadowRoot.querySelector('print-preview-button-strip').shadowRoot.querySelector('cr-button.cancel-button').click();");
                DriverManager.getDriver().driver.switchTo().window(tabs2.get(0));
            } else {
                GenericSteps.screenShot();
                Robot r = new Robot();
                r.keyPress(KeyEvent.VK_ESCAPE);
                r.keyRelease(KeyEvent.VK_ESCAPE);
            }
        }
    }

    public static void checkNewTab(String newTab) throws Exception {
        Thread.sleep(2000);
        ArrayList<String> tabs2 = new ArrayList<String> (DriverManager.getDriver().driver.getWindowHandles());
        if(tabs2.size() == 1){
            DataManager.getData().errorCollector.addError(new Exception("No new tab has been opened."));
        } else {
            DriverManager.getDriver().driver.switchTo().window(tabs2.get(1));
            try {
                BrowserSteps.waitForLoad(DriverManager.getDriver().driver, 30);
            } catch (Exception e){
                DataManager.getData().errorCollector.addError(new Throwable("Timeout while loading the new tab at URL " + newTab));
                GenericSteps.screenShot();
            }
            try {
                new WebDriverWait(DriverManager.getDriver().driver, 20).until(ExpectedConditions.urlContains(newTab));
            } catch (Exception e){
                DataManager.getData().errorCollector.addError(new Throwable("The actual page is not the expected one (" +DriverManager.getDriver().driver.getCurrentUrl()+ "). Expected "+ newTab + "."));
                GenericSteps.screenShot();
            }
            DriverManager.getDriver().scenario.write(DriverManager.getDriver().driver.getCurrentUrl());
            DriverManager.getDriver().driver.close();
            DriverManager.getDriver().driver.switchTo().window(tabs2.get(0));
        }
    }
}
