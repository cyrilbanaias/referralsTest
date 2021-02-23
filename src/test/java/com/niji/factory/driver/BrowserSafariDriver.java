package com.niji.factory.driver;

import com.niji.factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriverService;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author grageul
 */
public class BrowserSafariDriver {

    private static SafariDriverService service;
    
    public static void createAndStartService() throws IOException {
        service = new SafariDriverService.Builder()
                //Emplacement du fichier
                .usingDriverExecutable(new File("/usr/bin/safaridriver"))
                .usingAnyFreePort()
                .build();
        service.start();
      }
    
    /**
     * Démarrage du serveur Selenuim sur l'URL par d�faut
     * @throws IOException
     */
    public static WebDriver startLocalSelenium() throws IOException {
        try {
        	createAndStartService();
            return new RemoteWebDriver(service.getUrl(),
                    new SafariOptions());
        } finally {
        }
    }
    
    public static void stop() {
        DriverManager.getDriver().driver.quit();
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
