package com.niji.factory.driver;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import com.niji.data.DataManager;
import com.niji.factory.DriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


/**
 * @author gaetan.rageul
 *
 */
public class BrowserChromeDriver {

    private static ChromeDriverService service;
    
    public static void createAndStartService() throws IOException {
        if (DataManager.getData().os.equals("win")){
            service = new ChromeDriverService.Builder()
                    //Emplacement du fichier
                    .usingDriverExecutable(new File("src/test/resources/selenium/chromedriver.exe"))
                    .usingAnyFreePort()
                    .build();
        } else if (DataManager.getData().os.equals("linux")){
            service = new ChromeDriverService.Builder()
                    //Emplacement du fichier
                    .usingDriverExecutable(new File("src/test/resources/selenium/chromedriver_linux"))
                    .usingAnyFreePort()
                    .build();
        } else {
            service = new ChromeDriverService.Builder()
                    //Emplacement du fichier
                    .usingDriverExecutable(new File("src/test/resources/selenium/chromedriver"))
                    .usingAnyFreePort()
                    .build();
        }
        service.start();
      }
    
    /**
     * Démarrage du serveur Selenuim sur l'URL par d�faut
     * @throws IOException 
     *
     * @throws NumberFormatException
     */
    public static WebDriver startLocalSelenium() throws IOException {
        try {
            String downloadFilepath = (new File("src/test/resources/download/chrome/")).getAbsolutePath();
            Map<String, Object> preferences = new Hashtable<>();
            preferences.put("profile.default_content_settings.popups", 0);
            preferences.put("download.default_directory", downloadFilepath);
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            if (DataManager.getData().os.equals("linux")) {
                options.setBinary("/usr/bin/google-chrome-stable");
                Proxy proxy = new Proxy();
                proxy.setHttpProxy("proxy.intra.corp:8080");
                proxy.setSslProxy("proxy.intra.corp:8080");
                options.setProxy(proxy);
            }
            options.setExperimentalOption("prefs", preferences);
        	createAndStartService();
            return new RemoteWebDriver(service.getUrl(),
                    options);
        } finally {
        }
    }
    
    public static void stop() {
        DriverManager.getDriver().driver.quit();
    	/*BrowserDriver.mDriver.manage().deleteAllCookies();
    	BrowserDriver.mDriver.quit();*/
        service.stop();
    }
    
    /**
     * Chaine de caractère pour forcer le chargement des fichiers
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
