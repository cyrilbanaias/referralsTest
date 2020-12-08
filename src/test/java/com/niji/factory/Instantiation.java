package com.niji.factory;

import com.niji.data.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

public class Instantiation {

    public WebDriver driver = null;

    public DesiredCapabilities capabilities;

    @BeforeTest
    @Parameters({"platform", "deviceName", "udid", "platformVersion","os"})
    public void beforeMethod(String platform, String deviceName, String udid, String platformVersion, String os) throws Exception {
        CapabilitiesClass capabilities = CapabilitiesFactory.createInstance(platform, deviceName, udid, platformVersion);
        CapabilitiesManager.setCapabilities(capabilities);
        DataClass data = DataFactory.createInstance();
        DataManager.setData(data);
        DataManager.getData().os = os;
        TraductionClass trad = TraductionFactory.createInstance();
        TraductionManager.setTraduction(trad);
        if (System.getProperty("environnement") != null){
            DataManager.getData().environnement = System.getProperty("environnement");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (DriverManager.getDriver().scenario.isFailed()) {
            try {
                byte[] screenshot;
                if (DriverManager.isAndroidPlatform() || DriverManager.isiOSPlatform()){
                    screenshot = ((TakesScreenshot) DriverManager.getDriver().driverMobile).getScreenshotAs(OutputType.BYTES);
                } else {
                    screenshot = ((TakesScreenshot) DriverManager.getDriver().driver).getScreenshotAs(OutputType.BYTES);
                }
                DriverManager.getDriver().scenario.embed(screenshot, "image/png");
            } catch (WebDriverException wde) {
                System.err.println(wde.getMessage());
            }
        }
        DriverManager.getDriver().stopCurrentDriver(CapabilitiesManager.getCapabilities().capabilities);
    }
}
