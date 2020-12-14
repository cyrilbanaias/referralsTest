package com.niji.factory.driver;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.niji.data.DataManager;
import com.niji.factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;


/**
 * @author gaetan.rageul
 *
 */
public class BrowserFFDriver {
    
    private static GeckoDriverService service;
    
    public static void createAndStartService() throws IOException {
        if (DataManager.getData().os.equals("win")){
            service = new GeckoDriverService.Builder()
                    //Emplacement du fichier
                    .usingDriverExecutable(new File("src/test/resources/selenium/geckodriver.exe"))
                    .usingAnyFreePort()
                    .build();
        } else if (DataManager.getData().os.equals("linux")){
            service = new GeckoDriverService.Builder()
                    //Emplacement du fichier
                    .usingDriverExecutable(new File("src/test/resources/selenium/geckodriver_linux"))
                    .usingAnyFreePort()
                    .build();
        } else {
            service = new GeckoDriverService.Builder()
                    //Emplacement du fichier
                    .usingDriverExecutable(new File("src/test/resources/selenium/geckodriver"))
                    .usingAnyFreePort()
                    .build();
        }
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
            String downloadFilepath = (new File("src/test/resources/download/firefox/")).getAbsolutePath();
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.download.dir", downloadFilepath);
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", mineTypeListAsString());
            profile.setPreference("pdfjs.disabled", true);
            FirefoxOptions option = new FirefoxOptions();
            option.setProfile(profile);
            //option.setHeadless(true);
            if (DataManager.getData().os.equals("linux")){
                option.setBinary("/usr/bin/firefox");
                profile.setPreference("network.proxy.type", 1);
                profile.setPreference("network.proxy.http", "proxy.intra.corp");
                profile.setPreference("network.proxy.http_port", 8080);
                profile.setPreference("network.proxy.ssl", "proxy.intra.corp");
                profile.setPreference("network.proxy.ssl_port", 8080);
            }
            option.addArguments("--no-sandbox");
            option.addArguments("--disable-gpu");
            option.addArguments("enable-automation");
            option.addArguments("--disable-infobars");
            option.addArguments("--disable-dev-shm-usage");
            option.addArguments("--disable-browser-side-navigation");
        	createAndStartService();
            return new RemoteWebDriver(service.getUrl(),
                    option);
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
            sb.append(type).append(",");
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
        mineTypeList.add("image/jpeg");
        mineTypeList.add("image/png");
        mineTypeList.add("application/msword");
        mineTypeList.add("application/vnd.ms-powerpoint");
        mineTypeList.add("text/html");
        mineTypeList.add("text/plain");
        mineTypeList.add("text/csv");
        mineTypeList.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        mineTypeList.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        return mineTypeList;
    }

}
