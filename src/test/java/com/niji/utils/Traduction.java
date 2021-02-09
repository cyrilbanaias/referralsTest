package com.niji.utils;

import com.niji.data.DataManager;
import com.niji.data.TraductionManager;

import javax.xml.crypto.Data;
import java.util.*;

public class Traduction {

    public static String getLeadStatusTraduction(String type){
        return TraductionManager.getTraduction().leadStatus.get(type).get(DataManager.getData().language);
    }
}