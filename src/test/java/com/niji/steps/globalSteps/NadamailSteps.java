package com.niji.steps.globalSteps;

import com.niji.data.DataManager;
import com.niji.factory.DriverManager;
import com.niji.pageObjects.NadamailPage;
import com.niji.utils.NewBy;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NadamailSteps {

    public static void waitForEmail(int nombreAttendu){
        int nombreActuel = DriverManager.getDriver().driver.findElements(NewBy.classe("msg_item")).size();
        int i = 0;
        while (nombreActuel < nombreAttendu && i < 20){
            try{
                WebDriverWait wait = new WebDriverWait(DriverManager.getDriver().driver,10);
                wait.until(ExpectedConditions.visibilityOfElementLocated(NewBy.classe("msg_item")));
            } catch (Exception e){

            }
            nombreActuel = DriverManager.getDriver().driver.findElements(NewBy.classe("msg_item")).size();
            i++;
        }
        if ( nombreActuel >= nombreAttendu){
            DriverManager.getDriver().driver.findElement(NewBy.classe("msg_item")).click();
        }
    }

    @When("j'accede au compte \"(.+)\"")
    public static void accessToAccount(String email) throws Exception{
        String emailToUse = "";
        if (email.equals("email_random")){
            emailToUse = DataManager.getData().account_random;
        } else if (email.equals("email_auto")){
            emailToUse = DataManager.getData().getAutomationAccountEmail();
        } else {
            emailToUse = email;
        }
        BrowserSteps.access("http://www.getnada.com");
        NadamailPage.getAddAcountButton().click();
        NadamailPage.getEmailField().clear();
        NadamailPage.getEmailField().sendKeys(emailToUse.substring(0, emailToUse.indexOf("@")));
        NadamailPage.getAcceptButton().click();
        Thread.sleep(2000);
    }

    @When("je supprime les emails")
    public static void deleteEmails(){
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver().driver,20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(NewBy.classe("msg_item")));
            List<WebElement> emails = DriverManager.getDriver().driver.findElements(NewBy.classe("msg_item"));
            while (emails.size() != 0){
                emails.get(0).click();
                Thread.sleep(2000);
                DriverManager.getDriver().driver.findElement(NewBy.classe("icon-trash-empty")).click();
                Thread.sleep(2000);
                emails = DriverManager.getDriver().driver.findElements(NewBy.classe("msg_item"));
            }
        } catch (Exception e){

        }
    }

    @When("^je valide mon inscription sur nadamail pour le compte \"(.+)\"$$")
    public void validateNadamailAccount(String email) throws Exception{
        NadamailSteps.accessToAccount(email);
        NadamailSteps.waitForEmail(1);
        Thread.sleep(2000);
        DriverManager.getDriver().driver.switchTo().frame("idIframe");
        String url = NadamailPage.getConfirmLink().getAttribute("href");
        BrowserSteps.access(NadamailPage.getConfirmLink().getAttribute("href"));
        DriverManager.getDriver().driver.switchTo().defaultContent();
        Thread.sleep(5000);
        try{
            // Element de la page du lien
        } catch (Exception e){
            // Message en cas d'erreur
        }
    }
}
