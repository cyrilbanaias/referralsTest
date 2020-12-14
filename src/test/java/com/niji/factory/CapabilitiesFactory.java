package com.niji.factory;

public class CapabilitiesFactory {

    public static CapabilitiesClass createInstance(String platform, String deviceName, String udid, String platformVersion) throws Exception{
        CapabilitiesClass capabilities = new CapabilitiesClass();
        capabilities.initialize(platform, deviceName, udid, platformVersion);
        return capabilities;
    }
}