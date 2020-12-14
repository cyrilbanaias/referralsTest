package com.niji.steps.globalSteps;

import com.niji.factory.CapabilitiesManager;
import com.niji.factory.DriverManager;
import com.niji.pageObjects.UnlockPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.ScreenOrientation;

public class DeviceSteps {

	@Given("je suis sur le device$")
	public void device() {

	}

	@Given("je deverouille le device$")
	public void unlock() throws Exception {
		if (DriverManager.isAndroidPlatform()) {
			((AndroidDriver<MobileElement>) DriverManager.getDriver().driverMobile).pressKey(new KeyEvent(AndroidKey.HOME));
			String DeviceName = CapabilitiesManager.getCapabilities().capabilities.getCapability("deviceName").toString();
			if (DeviceName.equals("SM-G930F") || DeviceName.equals("SM-G960F") || DeviceName.equals("CLT-L29")) {
				ScrollSteps.swipe("TOP");
				Thread.sleep(5000);
			}
			if (/**ElementSteps.elementIsVisible("0") == 1 && */!DeviceName.equals("U FEEL LITE")) {
				UnlockPage.getZeroTouch().click();
				UnlockPage.getZeroTouch().click();
				UnlockPage.getZeroTouch().click();
				UnlockPage.getZeroTouch().click();
				if (DeviceName.equals("CLT-L29")) {
					UnlockPage.getZeroTouch().click();
					UnlockPage.getZeroTouch().click();
				}
				if (DeviceName.equals("GT-I9505")) {
					//DataManager.getData().getWebElement("OK_lock_GT-I9505").click();
				}
				if (!DeviceName.equals("GT-I9505") && !DeviceName.equals("CLT-L29"))
				{
					UnlockPage.getOKTouch().click();
				}
			}
		}
		else {
			DriverManager.getDriver().driverMobile.executeScript("seetest:client.deviceAction(\"Home\")");
			DriverManager.getDriver().driverMobile.executeScript("seetest:client.deviceAction(\"Home\")");
			UnlockPage.getZeroTouch().click();
			UnlockPage.getZeroTouch().click();
			UnlockPage.getZeroTouch().click();
			UnlockPage.getZeroTouch().click();
			UnlockPage.getZeroTouch().click();
			UnlockPage.getZeroTouch().click();
		}
	}
	
	@When("je verouille le device$")
	public void tearDown(){
		DriverManager.getDriver().driverMobile.executeScript("seetest:client.deviceAction(\"Power\")");
	}
	
	@When("je change l'orientation en \"(.+)\"$")
	public static void deviceOrientation(String orientation){
		if (orientation.equals("Paysage")) {
			DriverManager.getDriver().driverMobile.rotate(ScreenOrientation.LANDSCAPE);
		} else {
			DriverManager.getDriver().driverMobile.rotate(ScreenOrientation.PORTRAIT);
		}
	}
	
	@When("je relance le device$")
	public void restartDevice() {
		DriverManager.getDriver().driverMobile.executeScript("seetest:client.reboot(\"120000\")");
	}
	
	@When("je change de contexte pour \"(.+)\"$")
	public static void switchContext(String context) throws InterruptedException {
		Thread.sleep(5000);
		DriverManager.getDriver().driverMobile.context(context);
	}

	public static void hideKeyBoard(){
		DriverManager.getDriver().driverMobile.hideKeyboard();
	}
}
