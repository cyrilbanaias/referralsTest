package com.niji.steps.globalSteps;

import com.niji.factory.CapabilitiesManager;
import com.niji.factory.DriverManager;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementSteps {

	public static void fillField(WebElement element, String value){
		if (CapabilitiesManager.getCapabilities().capabilities.getCapability("platformName").equals("ie")){
			int i = 0;
			while(!element.getAttribute("value").equals(value) && i < 20){
				element.clear();
				element.sendKeys(value);
				element.click();
				i++;
			}
		} else {
			element.sendKeys(value);
		}
	}

	/**
	 * WaitFor WebElement or MobileElement
	 */
	public static void waitForVisibilityOfElement(WebElement element, int timeoutInSeconds){
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver().driver, timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForInVisibilityOfElement(WebElement element, int timeoutInSeconds){
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver().driver, timeoutInSeconds);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public static void waitForElementToBeClickable(WebElement element, int timeoutInSeconds){
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver().driver, timeoutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForElementToBeClickable(By locator, int timeoutInSeconds){
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver().driver, timeoutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}


	public static void waitForVisibilityOfElement(MobileElement element, int timeoutInSeconds){
		WebDriverWait wait;
		if (DriverManager.isiOSPlatform()) {
			wait = new WebDriverWait(DriverManager.getDriver().driverMobile, timeoutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
		} else {
			wait = new WebDriverWait(DriverManager.getDriver().driverMobile, timeoutInSeconds);
			wait.until(ExpectedConditions.attributeContains(element, "hidden", "false"));
		}
	}

	public static void waitForVisibilityOfElement(By locator, int timeoutInSeconds){
		WebDriverWait wait;
		if (DriverManager.isAndroidPlatform() || DriverManager.isiOSPlatform()){
			if (DriverManager.isiOSPlatform()) {
				wait = new WebDriverWait(DriverManager.getDriver().driverMobile, timeoutInSeconds);
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			} else {
				wait = new WebDriverWait(DriverManager.getDriver().driverMobile, timeoutInSeconds);
				wait.until(ExpectedConditions.attributeContains(locator, "hidden", "false"));
			}
		} else {
			wait = new WebDriverWait(DriverManager.getDriver().driver, timeoutInSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
	}

	public static void waitForInVisibilityOfElement(By locator, int timeoutInSeconds){
		WebDriverWait wait;
		if (DriverManager.isAndroidPlatform() || DriverManager.isiOSPlatform()){
			if (DriverManager.isiOSPlatform()) {
				wait = new WebDriverWait(DriverManager.getDriver().driverMobile, timeoutInSeconds);
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			} else {
				wait = new WebDriverWait(DriverManager.getDriver().driverMobile, timeoutInSeconds);
				wait.until(ExpectedConditions.attributeContains(locator, "hidden", "false"));
			}
		} else {
			wait = new WebDriverWait(DriverManager.getDriver().driver, timeoutInSeconds);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		}
	}

	public static void waitForPresenceOfElement(By locator, int timeoutInSeconds){
		WebDriverWait wait;
		if (DriverManager.isAndroidPlatform() || DriverManager.isiOSPlatform()){
			wait = new WebDriverWait(DriverManager.getDriver().driverMobile, timeoutInSeconds);
		} else {
			wait = new WebDriverWait(DriverManager.getDriver().driver, timeoutInSeconds);
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public static void waitForPresenceOfChildElement(WebElement parent, By locator, int timeoutInSeconds){
		WebDriverWait wait;
		if (DriverManager.isAndroidPlatform() || DriverManager.isiOSPlatform()){
			wait = new WebDriverWait(DriverManager.getDriver().driverMobile, timeoutInSeconds);
		} else {
			wait = new WebDriverWait(DriverManager.getDriver().driver, timeoutInSeconds);
		}
		wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, locator));
	}

	public static void waitForPresenceOfChildElement(By parent, By locator, int timeoutInSeconds){
		WebDriverWait wait;
		if (DriverManager.isAndroidPlatform() || DriverManager.isiOSPlatform()){
			wait = new WebDriverWait(DriverManager.getDriver().driverMobile, timeoutInSeconds);
		} else {
			wait = new WebDriverWait(DriverManager.getDriver().driver, timeoutInSeconds);
		}
		wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, locator));
	}

	/**
	 * Get Element presence or Visiblity
	 */
	public static boolean getElementVisiblity(MobileElement element, int timeOut){
		try {
			if (DriverManager.isiOSPlatform()) {
				new WebDriverWait(DriverManager.getDriver().driverMobile, timeOut).until(ExpectedConditions.visibilityOf(element));
			} else {
				new WebDriverWait(DriverManager.getDriver().driverMobile, timeOut).until(ExpectedConditions.attributeContains(element, "hidden", "false"));
			}
			return true;
		} catch (Exception e){
			return false;
		}
	}

	public static boolean getElementVisiblity(WebElement element, int timeOut){
		try {
			new WebDriverWait(DriverManager.getDriver().driver, timeOut).until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e){
			return false;
		}
	}

	public static boolean getElementVisiblity(By locator, int timeOut){
		try {
			new WebDriverWait(DriverManager.getDriver().driver, timeOut).until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e){
			return false;
		}
	}

	public static boolean getElementPresence(By locator, int timeOut){
		try {
			new WebDriverWait(DriverManager.getDriver().driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (Exception e){
			return false;
		}
	}

	/**
	 * scroll until Element is visible
	 */
	public static void makeVisible(WebElement element, Boolean onTop){
		((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].scrollIntoView("+onTop+");", element);
	}

	public static void makeVisible(MobileElement element){
		if (DriverManager.isiOSPlatform()) {
			int tries = 0;
			while (!element.getAttribute("visible").equals("true") && tries++ < 10) {
				ScrollSteps.scroll("DOWN");
			}
		} else {
			int tries = 0;
			while (!element.getAttribute("visible").equals("true") && tries++ < 10) {
				ScrollSteps.scroll("DOWN");
			}
		}
	}
}
