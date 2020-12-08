package com.niji.utils;

import com.niji.data.DataManager;
import com.niji.data.TraductionManager;

import javax.xml.crypto.Data;
import java.util.*;

public class Traduction {

    public static String getCountryFromLanguage(){
        return getCountryFromLanguage(DataManager.getData().language);
    }

    public static String getCountryFromLanguage(String language){
        switch(language){
            case "IT" : return "Italy";
            case "BR" : return "Brazil";
            case "FR" : return "France";
            case "SP" : return "Spain";
            case "GR" : return "Greece";
            case "PE" : return "Peru";
            case "CL" : return "Chile";
            case "MA" : return "Morocco";
            case "RO" : return "Romania";
            case "RU" : return "Russia";
            case "TR" : return "Turkey";
            case "UK" : return "UK";
        }
        return "No country";
    }

    public static String getAccidentTypeTraduction(String type){
        return TraductionManager.getTraduction().accidentTypeTraduction.get(type).get(DataManager.getData().language);
    }

    public static String getAccidentTypeFromTraduction(String traduction){
        Iterator<Map.Entry<String, HashMap<String, String>>> it = TraductionManager.getTraduction().accidentTypeTraduction.entrySet().iterator();
        String result = "";
        while (it.hasNext()) {
            Map.Entry<String, HashMap<String, String>> e = it.next();
            if (e.getValue().containsValue(traduction)){
                result = e.getKey();
                break;
            }
        }
        return result;
    }

    public static String getServiceBookingTypeTraduction(String type){
        return TraductionManager.getTraduction().serviceBookingTypeTraduction.get(type).get(DataManager.getData().language);
    }

    public static String getConcernedDriverTraduction(String driver){
        return TraductionManager.getTraduction().driverTraduction.get(driver).get(DataManager.getData().language);
    }

    public static String getDriverFromTraduction(String traduction){
        Iterator<Map.Entry<String, HashMap<String, String>>> it = TraductionManager.getTraduction().driverTraduction.entrySet().iterator();
        String result = "";
        while (it.hasNext()) {
            Map.Entry<String, HashMap<String, String>> e = it.next();
            if (e.getValue().containsValue(traduction)){
                result = e.getKey();
                break;
            }
        }
        return result;
    }

    public static String getSMEClaimAccidentTabTraduction(String tab){
        return TraductionManager.getTraduction().smeClaimAccidentTabTraduction.get(tab).get(DataManager.getData().language);
    }

    public static String getEventTypeTraduction(String type){
        return TraductionManager.getTraduction().myEventsTraduction.get(type).get(DataManager.getData().language);
    }

    public static String getEventStatusTraduction(String status){
        return TraductionManager.getTraduction().myEventsStatusTraduction.get(status).get(DataManager.getData().language);
    }

    public static ArrayList<String> getDocumentColumnTraduction(){
        return TraductionManager.getTraduction().myDocumentsColumnTraduction.get(DataManager.getData().language);
    }

    public static String getDownloadAllDocumentsTraduction(){
        return TraductionManager.getTraduction().documentsDownloadAllTraduction.get(DataManager.getData().language);
    }

    public static String getRecentDocumentsTitleTraduction(){
        return TraductionManager.getTraduction().recentDocumentsTabTitleTraduction.get(DataManager.getData().language);
    }

    public static String getNoDocumentsTraduction(){
        return TraductionManager.getTraduction().noDocumentsAvailableTraduction.get(DataManager.getData().language);
    }

    public static ArrayList<String> getDocManV2FoldersTraduction(){
        return TraductionManager.getTraduction().docManv2subFoldersListTraduction.get(DataManager.getData().language+"-"+DataManager.getData().account_type);
    }

    public static String getOrderStepTraduction(String step){
        return TraductionManager.getTraduction().orderStepTraduction.get(step).get(DataManager.getData().language);
    }

    public static ArrayList<String> getIFRS16ColumnTraduction(){
        return TraductionManager.getTraduction().IFRS16ColumnTraduction.get(DataManager.getData().language);
    }

    public static ArrayList<String> getFMHomePageIDSList(){
        return TraductionManager.getTraduction().fmHomePageBlocksIDS.get(DataManager.getData().language+"-"+DataManager.getData().account_type);
    }

    public static ArrayList<String> getVehicleListColumnTraduction(String url){
        return TraductionManager.getTraduction().vehicleListColumnTraduction.get(url).get(DataManager.getData().language);
    }

    public static ArrayList<String> getVehicleDetailsTraductions(String table){
        return TraductionManager.getTraduction().vehicleDetailsColumnTraduction.get(table).get(DataManager.getData().language);
    }

    public static String getDisconnectionMenuTraduction(String country){
        return TraductionManager.getTraduction().disconnectionMenuTraduction.get(country);
    }

    public static ArrayList<String> getMonthsTraduction(){
        return TraductionManager.getTraduction().calendarMonthsTraduction.get(DataManager.getData().language);
    }

    public static String getNoDelivriesTraduction(){
        return TraductionManager.getTraduction().noDelivriesTraduction.get(DataManager.getData().language);
    }

    public static ArrayList<String> getOrderListColumnTraduction(){
        return TraductionManager.getTraduction().orderListColumnTraduction.get(DataManager.getData().language);
    }

    public static String getMenuHomeTraduction(){
        return TraductionManager.getTraduction().menuHomeTraduction.get(DataManager.getData().language);
    }

    public static String getMenuUsefulLinksTraduction(){
        return TraductionManager.getTraduction().menuUsefulLinksTraduction.get(DataManager.getData().language+"-"+DataManager.getData().account_type);
    }
}
