package com.niji.factory;

import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesClass {

    public DesiredCapabilities capabilities;

    public void initialize(String platform, String deviceName, String udid, String platformVersion) throws Exception{

        capabilities = new DesiredCapabilities();
        switch (platform.toLowerCase()) {
            case "chrome":
            case "firefox":
            case "edge":
            case "new_edge":
                capabilities.setCapability("platformName", platform);
                break;
            case "ie":
                capabilities.setCapability("ignoreZoomSetting", true);
                capabilities.setCapability("platformName", platform);
                break;
            case "android_web":
                capabilities.setCapability("platformName", platform);
                capabilities.setCapability("platformVersion", platformVersion); //Replace this with your Android version
                capabilities.setCapability("deviceName", deviceName); //Replace this with your simulator/device
                capabilities.setCapability("autoLaunch", false);
                capabilities.setBrowserName(MobileBrowserType.CHROME);
                break;
            case "android":
                capabilities.setCapability("platformName", platform);
                capabilities.setCapability("platformVersion", platformVersion); //Replace this with your Android version
                capabilities.setCapability("deviceName", deviceName); //Replace this with your simulator/device
                capabilities.setCapability("autoLaunch", false);
                break;
            case "ios_web":
                capabilities.setCapability("browserstack.appium_version", "1.17.0");
                capabilities.setCapability("browserstack.use_w3c", true);
                capabilities.setCapability("nativeWebTap", true);
                capabilities.setCapability("nativeInstrumentsLib", true);
                capabilities.setCapability("platformName", platform);
                capabilities.setCapability("platformVersion", platformVersion); //Replace this with your Android version
                capabilities.setCapability("deviceName", deviceName); //Replace this with your simulator/device
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setBrowserName(MobileBrowserType.SAFARI);
                break;
            case "ios":
                capabilities.setCapability("platformName", platform);
                capabilities.setCapability("platformVersion", platformVersion); //Replace this with your Android version
                capabilities.setCapability("deviceName", deviceName); //Replace this with your simulator/device
                capabilities.setCapability("xcodeOrgId", "B564TVEPFR");
                capabilities.setCapability("xcodeSigningId", "iPhone Developer");
                capabilities.setCapability("udid", udid);
                capabilities.setCapability("autoLaunch", false);
                capabilities.setCapability("autoAcceptAlerts", true);
                capabilities.setCapability("instrumentApp", true);
                break;
            default:
                throw new Exception();
        }

        //For HTTP
        /*System.getProperties().put("http.proxyHost", "proxy.intra.corp");
        System.getProperties().put("http.proxyPort", 8080);

        //For HTTPS
        System.getProperties().put("https.proxyHost", "proxy.intra.corp");
        System.getProperties().put("https.proxyPort", 8080);*/
    }
}

