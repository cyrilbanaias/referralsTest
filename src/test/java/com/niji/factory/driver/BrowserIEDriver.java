package com.niji.factory.driver;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.niji.factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


/**
 * @author gaetan.rageul
 *
 */
public class BrowserIEDriver {

	
    private static InternetExplorerDriverService service;
    
    public static void createAndStartService() throws IOException {
        service = new InternetExplorerDriverService.Builder()
        		//Emplacement du fichier
            .usingDriverExecutable(new File("src/test/resources/selenium/IEDriverServer.exe"))
            .usingAnyFreePort()
            .build();
        service.start();
      }

    /**
     * Démarrage du serveur Sélénuim sur l'URL par défaut
     * @throws IOException 
     *
     * @throws NumberFormatException
     */
    public static WebDriver startLocalSelenium() throws IOException {
        try {
            createAndStartService();
            //DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
            //cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            return new RemoteWebDriver(service.getUrl(),
                    new InternetExplorerOptions().destructivelyEnsureCleanSession());
        } finally {
           // Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
        }
    }

    public static void stop() {
        DriverManager.getDriver().driver.quit();
    	/*BrowserDriver.mDriver.manage().deleteAllCookies();
    	BrowserDriver.mDriver.quit();*/
        service.stop();
    }
    
    /**
     * Chaine de caractère pour forcer le téléchargement des fichiers
     * @return
     */
    @SuppressWarnings("unused")
	private static String mineTypeListAsString() {
        StringBuilder sb = new StringBuilder();
        for (String type : mineTypeList()) {
            sb.append(type).append(";");
        }
        return sb.toString();
    }

    private static Set<String> mineTypeList(){
        Set<String> mineTypeList = new HashSet<String>();
        mineTypeList.add("application/excel");
        mineTypeList.add("application/octet-stream");
        mineTypeList.add("application/pdf");
        mineTypeList.add("application/vnd.ms-excel");
        mineTypeList.add("application/x-bzip");
        mineTypeList.add("application/x-bzip2");
        mineTypeList.add("application/x-excel");
        mineTypeList.add("application/x-gzip");
        mineTypeList.add("application/x-zip-compressed");
        mineTypeList.add("application/zip");
        return mineTypeList;
    }
}
