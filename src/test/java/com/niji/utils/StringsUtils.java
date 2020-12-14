package com.niji.utils;

import com.niji.data.DataManager;
import org.testng.Assert;

public class StringsUtils {

    /**
     * Méthode pour la comparaison de deux strings. Normalise la chaîne de caractère, en retirant les accents, espace, saut à la ligne...)
     * @param textToNormalize
     * @return
     */
    public static String normalizeString(String textToNormalize){
        String result;
        result = textToNormalize.toLowerCase();
        result = result.replaceAll("é","e");
        result = result.replaceAll("è","e");
        result = result.replaceAll("à","a");
        result = result.replaceAll("\\n","");
        result = result.replaceAll(" ","");
        return result;
    }


    /**
     * Méthode pour la comparaison de deux prix. Normalise la chaîne de caractère, en retirant les devices, les espaces, en remplacant kes virgules par des points...)
     * @param priceToNormalize
     * @return
     */
    public static String normalizePrice(String priceToNormalize){
        String result;
        result = priceToNormalize.replaceAll("€","");
        result = result.replaceAll(" ","");
        result = result.replace(",",".");
        result = result.replaceAll("-","");
        return result;
    }

    public static void verifyNotNull(String info){
        try{
            Assert.assertNotNull(info);
        } catch (Exception e){
            DataManager.getData().errorCollector.addError(new Throwable("The field should not be empty."));
        }
    }
}
