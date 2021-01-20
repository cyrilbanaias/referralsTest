package com.niji.steps.projectSteps;

import com.niji.data.DataManager;
import com.niji.factory.DriverManager;
import com.niji.pageObjects.InsertsElements;
import com.niji.steps.globalSteps.GenericSteps;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HomePageSteps {

    @When("the insert is displayed on the homepage")
    public void checkInsertOnHomePage() throws Exception{
        // Check title, subtitle, description, link text and link href
        try {
            WebElement block = InsertsElements.getHomePageInsertBlock();
            InsertsElements.getInsertTitle(block, DataManager.getData().insertionName);
            InsertsElements.getInsertSubtitle(block, DataManager.getData().insertionName);
            InsertsElements.getInsertDescription(block, DataManager.getData().insertionName);
            InsertsElements.getInsertImage(block, DataManager.getData().insertionName);
            InsertsElements.getInsertAction(block, DataManager.getData().insertionName);
            InsertsElements.getInsertActionURL(block, DataManager.getData().insertionName);
        } catch (Exception e){
            GenericSteps.screenShot();
            throw new Exception("One or more elements of the insert is not displayed/present");
        }
    }

    @When("the popup is displayed on the homepage")
    public void checkPopupOnHomePage() throws Exception{
        DataManager.getData().closePopup = true;
        try {
            WebElement block = InsertsElements.getPopopInsertBlock();
            InsertsElements.getInsertTitle(block, DataManager.getData().insertionName);
            InsertsElements.getInsertSubtitle(block, DataManager.getData().insertionName);
            InsertsElements.getInsertDescription(block, DataManager.getData().insertionName);
            InsertsElements.getInsertImage(block, DataManager.getData().insertionName);
            InsertsElements.getInsertAction(block, DataManager.getData().insertionName);
            InsertsElements.getInsertActionURL(block, DataManager.getData().insertionName);
            PopUpSteps.closePoUp();
        } catch (Exception e){
            GenericSteps.screenShot();
            throw new Exception("One or more elements of the insert is not displayed/present");
        }
    }

    @When("inserts are in the good order")
    public void checkInserstOrder(){
        List<WebElement> carrousselTitles = InsertsElements.getCarrousselItemTitles();
        boolean order = true;
        for (int i = 0; i < carrousselTitles.size(); i++){
            if(!carrousselTitles.get(i).getAttribute("innerHTML").equals(DataManager.getData().insertionsOrder.get(i))){
                order = false;
            }
        }
        if (!order) DataManager.getData().errorCollector.addError(new Throwable("Inserts are not in the good order on the homepage"));
    }
}
