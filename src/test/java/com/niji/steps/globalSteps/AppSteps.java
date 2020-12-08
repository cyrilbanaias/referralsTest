package com.niji.steps.globalSteps;

import com.google.common.io.Files;
import com.niji.data.DataManager;
import com.niji.data.ProjectData;
import com.niji.factory.DriverManager;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class AppSteps {

    @When("je lance l'appli \"(.+)\"$")
    public void ouvrirApp(String app) throws InterruptedException{
        stopApp(app);
        if (DriverManager.isAndroidPlatform()) {
            ((AndroidDriver) DriverManager.getDriver().driverMobile).startActivity(new Activity(DataManager.getData().returnParam(app), DataManager.getData().returnParam("Activity")));
        }
        else {
            DriverManager.getDriver().driverMobile.executeScript("seetest:client.launch(\""+DataManager.getData().returnParam(app)+"\", \"true\",\"true\")");
        }
    }

    @When("je quitte l'appli \"(.+)\"$")
    public void stopApp(String app) throws InterruptedException{
        DriverManager.getDriver().driverMobile.executeScript("seetest:client.applicationClose(\""+DataManager.getData().returnParam(app)+"\")");
        Thread.sleep(2000);
    }

    @When("j'installe l'appli \"(.+)\"$")
    public void installApp(String appPath) {
        String path = ProjectData.input+"\\"+DataManager.getData().returnParam(appPath);
        if (DriverManager.isiOSPlatform()) {
            DriverManager.getDriver().driverMobile.executeScript("seetest:client.install(\""+path.replace("\\", "\\\\")+"\", \"true\",\"true\")");
        } else {
            DriverManager.getDriver().driverMobile.executeScript("seetest:client.install(\""+path.replace("\\", "\\\\")+"\", \"false\",\"false\")");
        }
    }

    @When("j'installe la derniere version de l'appli$")
    public void installLastApp() throws IOException {
        String path = "";
        if (getLatestAppFilefromDir() != null) {
            path = getLatestAppFilefromDir().getAbsolutePath();
        }
        if (DriverManager.isiOSPlatform()) {
            DriverManager.getDriver().driverMobile.executeScript("seetest:client.install(\""+path.replace("\\", "\\\\")+"\", \"true\",\"true\")");
        } else {
            DriverManager.getDriver().driverMobile.executeScript("seetest:client.install(\""+path.replace("\\", "\\\\")+"\", \"false\",\"false\")");
        }
        Files.copy(getLatestAppFilefromDir(), new File(ProjectData.inputReseau+"\\Archives\\"+getLatestAppFilefromDir().getName()));
        getLatestAppFilefromDir().delete();
    }

    @When("je passe l'appli \"(.+)\" en arriere plan")
    public void backApp() {
        if (DriverManager.isAndroidPlatform()) {
            ((AndroidDriver<MobileElement>) DriverManager.getDriver().driverMobile).pressKey(new KeyEvent(AndroidKey.HOME));
        }
        else {
            DriverManager.getDriver().driverMobile.executeScript("seetest:client.deviceAction(\"Home\")");
        }
    }

    @When("je désinstalle l'appli \"(.+)\"$")
    public void uninstallApp(String app) {
        DriverManager.getDriver().driverMobile.executeScript("seetest:client.uninstall(\""+DataManager.getData().returnParam(app)+"\")");
    }

    @When("j'efface les données de l'appli \"(.+)\"$")
    public void clearAppData(String app) {
        DriverManager.getDriver().driverMobile.executeScript("seetest:client.applicationClearData(\""+DataManager.getData().returnParam(app)+"\")");
    }

    @When("j'arrete le driver")
    public void endTest() {
        DriverManager.getDriver().driverMobile.quit();
    }

    private File getLatestAppFilefromDir(){
        File dir = new File(ProjectData.inputReseau);

        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (DriverManager.isAndroidPlatform()) {
                    return lowercaseName.endsWith(".apk");
                }
                else if (DriverManager.isiOSPlatform()) {
                    return lowercaseName.endsWith(".ipa");
                }
                else return false;
            }
        };

        File[] files = dir.listFiles(textFilter);
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.isDirectory()) {
                lastModifiedFile = files[i];
            }
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }
}
