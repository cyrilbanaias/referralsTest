package com.niji.steps.globalSteps;

import com.niji.data.DataManager;
import com.niji.factory.CapabilitiesManager;
import com.niji.factory.DriverClass;
import com.niji.factory.DriverFactory;
import com.niji.factory.DriverManager;
import com.niji.utils.ScreenShot;
import com.niji.utils.ErrorCollector;
import cucumber.api.java.Before;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;

/**
 * @author gaetan.rageul
 */
public class GenericSteps {
	
	public GenericSteps(ErrorCollector errorCollector) {
        DataManager.getData().errorCollector = errorCollector;
    }
	
	/**
     * @Before : Récupération du scénario, log de son nom et de l'heure début du scénario
     * @After : Récupération et log du statut du scénario
     */
    @Before
    public void setUp(cucumber.api.Scenario scenario) throws Exception{
        try {
            DriverManager.getDriver().driver.quit();
        } catch (NullPointerException e){

        }
        DriverClass driver = DriverFactory.createInstance(CapabilitiesManager.getCapabilities().capabilities, scenario);
        DriverManager.setDriver(driver);
    }
    
    /**
     * Fonctions générales : screenshot et log
     */
    public static void screenShot() throws Exception{
        if (DriverManager.isAndroidPlatform() || DriverManager.isiOSPlatform()){
            ScreenShot.addScreenShot(DriverManager.getDriver().driverMobile);
        } else {
            ScreenShot.addScreenShot(DriverManager.getDriver().driver);
        }
    }

    public static void screenShot(WebElement element){
        ScreenShot.addScreenShot(DriverManager.getDriver().driver, element);
    }

    public static void screenShot(MobileElement element){
        ScreenShot.addScreenShot(DriverManager.getDriver().driverMobile, element);
    }
}
