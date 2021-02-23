package com.niji.factory;

import com.niji.factory.driver.*;
import cucumber.api.Scenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverClass {

    public WebDriver driver;
    public AppiumDriver<MobileElement> driverMobile;
    public Scenario scenario;

    public void initialize(DesiredCapabilities capabilities, Scenario scenario_) throws Exception{
        scenario = scenario_;
        scenario.write(capabilities.getCapability("platformName").toString());
        switch (capabilities.getCapability("platformName").toString()) {
            case "chrome":
                driver = BrowserChromeDriver.startLocalSelenium();
                driver.manage().deleteAllCookies();
                break;
            case "ie":
                driver = BrowserIEDriver.startLocalSelenium();
                driver.manage().deleteAllCookies();
                break;
            case "firefox":
                driver = BrowserFFDriver.startLocalSelenium();
                driver.manage().deleteAllCookies();
                break;
            case "edge":
            case "new_edge":
                driver = BrowserEdgeDriver.startLocalSelenium();
                driver.manage().deleteAllCookies();
                break;
            case "ios":
                driverMobile = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                scenario.write(capabilities.getCapability("deviceName").toString());
                break;
            case "ios_web":
                driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                scenario.write(capabilities.getCapability("deviceName").toString());
                break;
            case "android":
                driverMobile = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                scenario.write(capabilities.getCapability("deviceName").toString());
                break;
            case "android_web":
                driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                scenario.write(capabilities.getCapability("deviceName").toString());
                break;
            default:
                throw new Exception();
        }
        if (driver != null) driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (driverMobile != null) driverMobile.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void stopCurrentDriver(DesiredCapabilities capabilities) {
        try {
            if(capabilities.getCapability("platformName").equals("android_web")
                    || capabilities.getCapability("platformName").equals("ios_web")){
                driver.quit();
            }
            if (capabilities.getCapability("platformName").equals("firefox")) {
                BrowserFFDriver.stop();
            }
            if (capabilities.getCapability("platformName").equals("edge") || capabilities.getCapability("platformName").equals("new_edge")) {
                BrowserEdgeDriver.stop();
            }
            if (capabilities.getCapability("platformName").equals("chrome")) {
                BrowserChromeDriver.stop();
            }
            if (capabilities.getCapability("platformName").equals("ie")) {
                BrowserIEDriver.stop();
            }
            if (DriverManager.isAndroidPlatform() || DriverManager.isiOSPlatform()){
                driverMobile.quit();
            }
        } catch (final UnreachableBrowserException e) {
        }
    }
}

