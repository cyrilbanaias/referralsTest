package com.niji.factory;

public class DriverManager {
    private static ThreadLocal<DriverClass> driver = new ThreadLocal<DriverClass>();

    public static DriverClass getDriver() {
        return driver.get();
    }

    public static void setDriver(DriverClass drive) {
        driver.set(drive);
    }

    public static boolean isAndroidPlatform(){
        return CapabilitiesManager.getCapabilities().capabilities.getCapability("platformName").equals("android");
    }

    public static boolean isiOSPlatform(){
        return CapabilitiesManager.getCapabilities().capabilities.getCapability("platformName").equals("ios");
    }
}
