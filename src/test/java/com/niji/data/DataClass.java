package com.niji.data;

import com.niji.factory.CapabilitiesManager;
import com.niji.utils.ErrorCollector;

import java.util.ArrayList;

public class DataClass {

    //Exclusion de l'errorCollector
    public ErrorCollector errorCollector;
    public ArrayList<String> errorCollectorExclusion = new ArrayList();
    public String os = "win";
    public String environnement = "UAT";
    public String initialURL = "";
    public boolean closePopup = true;

    public String UAT = "https://my.uat.arval.com/";

    public String REFERRALS = "https://referrals-uat.sta2.intra.corp/referrals/";
    public String ADMIN_TOOL = "https://referrals-uat-admin.sta2.intra.corp/";

    public String used_account = "";
    public String used_account_name = "";
    public String account_profile = "";

    public String account_random = "";
    public String account_empty_chrome = "test@yopmail.com";
    public String account_empty_firefox = "test@yopmail.com";
    public String account_empty_edge = "test@yopmail.com";
    public String account_empty_ie = "test@yopmail.com";
    public String accountmac_empty_chrome = "test@yopmail.com";
    public String accountmac_empty_firefox = "test@yopmail.com";
    public String account_empty_safari = "test@yopmail.com";

    public String language = "";
    public String account_type = "";
    public String account_settings = "";
    public String existing_client = "";

    public String company_number = "";
    public String company_name = "";
    public String client_informations = "";
    public String client_email = "";

    public String lead_type = "";
    // Project / Project deadline / Funding mode / Fleet size / Vehicle Model / Contract Duration / Trigger Event / Annual mileage / Details
    public ArrayList<String> leadInformations = new ArrayList<>();
    // Marque & modèle / Version / Prix / Duration & mileage
    public ArrayList<String> existingOffer = new ArrayList<>();

    public String newProfile = "";
    public String insertionName = "";
    public ArrayList<String> insertionsOrder = new ArrayList<>();

    public void initialize() {
    }

    /**
     * @param param
     * @return une valeur associ�e au param�tre
     */
    public String returnParam(String param){
        switch (param) {
            case "My Arval":
                if (environnement.equals("UAT")){
                    return UAT;
                }
            case "Referrals":
                return REFERRALS;
            case "Admin Tool":
                return ADMIN_TOOL;
        }
        return param;
    }

    // Retourne le compte d'automatisation à utiliser en fonction de la plateforme et du navigateur (donnée renseignée dans le runner xml)
    public String getAutomationAccountEmail(){
        if (DataManager.getData().os.equals("win")){
            switch (CapabilitiesManager.getCapabilities().capabilities.getCapability("platformName").toString()) {
                case "chrome":
                    return DataManager.getData().account_empty_chrome;
                case "ie":
                    return DataManager.getData().account_empty_ie;
                case "firefox":
                    return DataManager.getData().account_empty_firefox;
                case "edge":
                case "new_edge":
                    return DataManager.getData().account_empty_edge;
            }
        } else if (DataManager.getData().os.equals("mac")){
            switch (CapabilitiesManager.getCapabilities().capabilities.getCapability("platformName").toString()) {
                case "chrome":
                    return DataManager.getData().accountmac_empty_chrome;
                case "firefox":
                    return DataManager.getData().accountmac_empty_firefox;
                case "safari":
                    return DataManager.getData().account_empty_safari;
            }
        }
        return "no_account";
    }
}

