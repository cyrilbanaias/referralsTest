package com.niji.factory;

import com.niji.data.DataClass;
import cucumber.api.Scenario;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {

    public static DriverClass createInstance(DesiredCapabilities capabilities, Scenario scenario) throws Exception{
        DriverClass driver = new DriverClass();
        driver.initialize(capabilities, scenario);
        return driver;
    }
}

