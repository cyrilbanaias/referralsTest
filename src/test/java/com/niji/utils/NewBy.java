package com.niji.utils;

import org.openqa.selenium.By;

import java.util.ArrayList;

public class NewBy {

    /**
     * Recherche par texte exact
     * @param text texte de l'élément
     * @return
     */
    public static By text(String text) {
        return By.xpath("//*[text()=\"" + text + "\"]");
    }

    /**
     * Recherche par texte partiel
     * @param text texte de l'élément
     * @return
     */
    public static By partialText(String text) {
        return By.xpath("//*[contains(text(),\"" + text + "\")]");
    }

    /**
     * Recherche par valeur exacte (attribut html value)
     * @param value valeur de l'élément
     * @return
     */
    public static By value(String value) {
        return By.xpath("//*[@value=\"" + value + "\"]");
    }

    /**
     * Recherche par class exacte (attribut html class)
     * @param classe classe de l'élément
     * @return
     */
    public static By classe(String classe){
        return By.xpath(".//*[@class=\"" + classe + "\"]");
    }

    /**
     * Recherche exacte parmi une liste donnée en paramètre
     * @param classes liste de classes possibles pour le ou les éléments ciblés
     * @return
     */
    public static By classes(ArrayList<String> classes){
        String xpath = ".//*[";
        for (String classe:classes){
            xpath = xpath + "@class=\""+classe+"\"";
            if (classes.indexOf(classe) != classes.size()-1){
                xpath = xpath + " or ";
            }
        }
        xpath = xpath + "]";
        return By.xpath(xpath);
    }

    /**
     * Recherche par classe partielle
     * @param classe classe de l'élément
     * @return
     */
    public static By partialClass(String classe) { return By.xpath("//*[contains(@class,\""+ classe +"\")]");}

    public static By partialClasses(ArrayList<String> classes){
        String xpath = ".//*[";
        for (String classe:classes){
            xpath = xpath + "contains(@class,\""+classe+"\")";
            if (classes.indexOf(classe) != classes.size()-1){
                xpath = xpath + " or ";
            }
        }
        xpath = xpath + "]";
        return By.xpath(xpath);
    }

    /**
     * Recherche par valeur de l'attribut href (partielle)
     * @param href valeur de l'attribut href
     * @return
     */
    public static By partialHRef(String href) { return By.xpath("//*[contains(@href,\""+ href +"\")]");}

    /**
     * Recherche par jhitranslate exact
     * @param text jhitranslate de l'élément
     * @return
     */
    public static By jhitranslate(String text) {
        return By.xpath("//*[@jhitranslate=\"" + text + "\"]");
    }

    /**
     * Recherche par jhitranslate partiel
     * @param text jhitranslate de l'élément
     * @return
     */
    public static By partialJhitranslate(String text) {
        return By.xpath("//*[contains(@jhitranslate,\"" + text + "\")]");
    }

    /**
     * Recherche par for exact
     * @param text for de l'élément
     * @return
     */
    public static By forAttribute(String text) {
        return By.xpath("//*[@for=\"" + text + "\"]");
    }

    /**
     * Recherche par for partiel
     * @param text for de l'élément
     * @return
     */
    public static By partialForAttribute(String text) {
        return By.xpath("//*[contains(@for,\"" + text + "\")]");
    }
}
