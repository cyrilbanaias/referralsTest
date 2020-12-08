package com.niji.factory;

public class CapabilitiesManager {
    private static ThreadLocal<CapabilitiesClass> capabilities = new ThreadLocal();

    public static CapabilitiesClass getCapabilities() {
        return capabilities.get();
    }

    public static void setCapabilities(CapabilitiesClass capabilities_) {
        capabilities.set(capabilities_);
    }
}

