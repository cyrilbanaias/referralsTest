package com.niji.pageObjects;

import com.niji.data.DataManager;
import com.niji.factory.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.postgresql.core.EncodingPredictor;

import java.util.List;

public class InsertsElements {

    public static List<WebElement> getCarrousselItemTitles(){
        return DriverManager.getDriver().driver.findElements(By.xpath("//*[contains(@class,\"carousel-item\")]//div[@class=\"card-text\"]/div[not(@class)]"));
    }

    public static WebElement getHomePageInsertBlock(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//div[@class=\"carousel-inner\"]"));
    }

    public static WebElement getBodyInsertBlock(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//div[@class=\"col-12 col-md-8\"]"));
    }

    public static WebElement getColumnInsertBlock(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//div[@class=\"col-12 col-md-4\"]"));
    }

    public static WebElement getPopopInsertBlock(){
        return DriverManager.getDriver().driver.findElement(By.xpath("//div[contains(@class,\"modal-content\")]"));
    }

    public static WebElement getInsertTitle(WebElement block, String insertion){
        return block.findElement(By.xpath(".//*[text()=\"title_" + insertion + "\"]"));
    }

    public static WebElement getInsertSubtitle(WebElement block, String insertion){
        return block.findElement(By.xpath(".//*[text()=\"subtitle_" + insertion + "\"]"));
    }

    public static WebElement getInsertDescription(WebElement block, String insertion){
        return block.findElement(By.xpath(".//*[text()=\"description_" + insertion + "\"]"));
    }

    public static WebElement getInsertImage(WebElement block, String insertion){
        return block.findElement(By.xpath(".//img[contains(@src,\"title_" + insertion + "\")]"));
    }

    public static WebElement getInsertAction(WebElement block, String insertion){
        return block.findElement(By.xpath(".//*[text()=\"action_" + insertion + "\"]"));
    }

    public static WebElement getInsertActionURL(WebElement block, String insertion){
        return block.findElement(By.xpath(".//*[@href=\"actionURL_" + insertion + "\"]"));
    }
}
