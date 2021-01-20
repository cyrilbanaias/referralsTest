package com.niji.steps.projectSteps;

import com.niji.data.DataManager;
import com.niji.factory.CapabilitiesManager;
import com.niji.factory.DriverClass;
import com.niji.factory.DriverFactory;
import com.niji.factory.DriverManager;
import com.niji.pageObjects.LoginPage;
import com.niji.pageObjects.MenuLinks;
import com.niji.steps.globalSteps.BrowserSteps;
import com.niji.steps.globalSteps.ElementSteps;
import com.niji.steps.globalSteps.GenericSteps;
import cucumber.api.java.en.Then;
import com.niji.utils.ReadCSVFile;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ConnectionSteps {

    public ArrayList<ArrayList<String>> getAccounts(List<String> list) throws Exception{
        ArrayList<ArrayList<String>> csvAccounts = ReadCSVFile.readCsvFile("src/test/resources/input/accounts.csv");

        ArrayList<ArrayList<String>> accounts = new ArrayList<>();

        for(ArrayList<String> csvAccount:csvAccounts){
            // Same account type and country
            if (csvAccount.get(0).equals(list.get(0))){
                // Split input list and account settings
                List<String> accountSettings = Arrays.asList(csvAccount.get(1).split(" - ", -1));
                String[] input = list.get(1).split(" - ", -1);
                boolean accountToAdd = false;
                for(String s: input){
                    if(s.contains("not:") && !accountSettings.contains(s.substring(4))) accountToAdd = true;
                    else if (accountSettings.contains(s)) accountToAdd = true;
                    else if (s.equals("")) accountToAdd = true;
                    else {
                        accountToAdd = false;
                        break;
                    }
                }
                if (accountToAdd) accounts.add(csvAccount);
            }
        }

        return accounts;
    }

    @When("I login with an account:")
    public void loginWithAccount(DataTable dt) throws Exception{
        List<String> list = dt.asList(String.class);

        DataManager.getData().account_type = list.get(0);

        // Get all accounts that matches with expected conditions
        ArrayList<ArrayList<String>> accounts = getAccounts(list);

        // Try to connect with a random account from the list
        boolean connected = false;
        while (!connected && accounts.size() > 0) {
            try {
                int i = (int) (Math.random() * (accounts.size() - 1));
                ArrayList<String> account = accounts.get(i);
                accounts.remove(account);
                DriverManager.getDriver().scenario.write(account.get(2));
                DataManager.getData().account_settings = account.get(1);
                if (!DataManager.getData().account_type.equals("ADMIN")){
                    DataManager.getData().existing_client = account.get(4);
                }
                login(account.get(2), account.get(3));
                checkIfConnected();
                connected = true;
            } catch (Exception e){
                // Throw an error if connection is KO, and relaunch the driver
                DriverManager.getDriver().scenario.write("Connection error : cannot connect to the targeted account (" + DataManager.getData().used_account + ")");
                DriverManager.getDriver().scenario.write(e.getMessage());
                GenericSteps.screenShot();
                DriverManager.getDriver().stopCurrentDriver(CapabilitiesManager.getCapabilities().capabilities);
                DriverClass driver = DriverFactory.createInstance(CapabilitiesManager.getCapabilities().capabilities, DriverManager.getDriver().scenario);
                DriverManager.setDriver(driver);
                BrowserSteps.access(DataManager.getData().initialURL);
            }
        }

        if (accounts.size() == 0 && !connected){
            throw new Exception("Connection error : no account can be used for this scenario");
        }
    }

    @When("I don't log well in with an account:")
    public void loginWithWrongAccount(DataTable dt) throws Exception{
        BrowserSteps.refreshBrowser();
        List<String> list = dt.asList(String.class);

        DataManager.getData().account_type = list.get(0);
        DataManager.getData().language = list.get(1);

        ArrayList<ArrayList<String>> accounts = getAccounts(list);

        // Select a rendom account
        int i = (int) (Math.random() * (accounts.size() - 1));
        ArrayList<String> account = accounts.get(i);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String append = formatter.format(new Date());

        String emailToUse;
        String passwordToUse;
        // Wrong login
        if (list.get(3).contains("Wrong")) emailToUse = append + account.get(4);
        else if (list.get(3).contains("No")) emailToUse = "";
        else emailToUse = account.get(4);
        // Wrong Password
        if (list.get(4).contains("Wrong")) passwordToUse = append + account.get(5);
        else if (list.get(4).contains("No")) passwordToUse = "";
        else passwordToUse = account.get(5);

        login(emailToUse, passwordToUse);
    }

    @When("^I log in with \"(.+)\" / \"(.+)\"$")
    public void login(String email, String password){
        Actions action = new Actions(DriverManager.getDriver().driver);
        DataManager.getData().used_account = email;
        ElementSteps.waitForVisibilityOfElement(LoginPage.getUserEmailField(), 30);
        LoginPage.getUserEmailField().clear();
        action.sendKeys(LoginPage.getUserEmailField(), email).build().perform();
        //LoginPage.getUserEmailField().sendKeys(email);
        LoginPage.getPasswordField().clear();
        action.sendKeys(LoginPage.getPasswordField(), password).build().perform();
        //LoginPage.getPasswordField().sendKeys(password);
        LoginPage.getConnectionButton().click();
        BrowserSteps.waitForLoad(DriverManager.getDriver().driver, 30);
    }

    @When("I write password \"(.+)\"")
    public void writePassword(String password){
        LoginPage.getPasswordField().clear();
        LoginPage.getPasswordField().sendKeys(password);
    }

    @When("I am on the homepage")
    public static void checkIfConnected() throws Exception{
        try {
            new WebDriverWait(DriverManager.getDriver().driver, 60).until(ExpectedConditions.or(ExpectedConditions.urlContains("referrals/"),
                    ExpectedConditions.urlContains("admin"),
                    ExpectedConditions.urlContains("lightning/page/home")));
        } catch (Exception e){
            DriverManager.getDriver().scenario.write(DriverManager.getDriver().driver.getCurrentUrl());
            throw new Exception("The actual page is not the expected one (" +DriverManager.getDriver().driver.getCurrentUrl()+ "). Expected referrals HP or admin tool");
        }
        DriverManager.getDriver().scenario.write(DriverManager.getDriver().driver.getCurrentUrl());
        try{
            if (DataManager.getData().closePopup) PopUpSteps.closePoUp();
        } catch (Exception e){
            // Do Nothing if no popup
        }
    }

    @When("I disconnect from Referrals")
    public void disconnect(){
        ElementSteps.makeVisible(MenuLinks.getAccountMenuLink(), true);
        MenuLinks.getAccountMenuLink().click();
        ElementSteps.waitForVisibilityOfElement(MenuLinks.getLogoutMenuLink(), 5);
        MenuLinks.getLogoutMenuLink().click();
        ElementSteps.waitForVisibilityOfElement(LoginPage.getUserEmailField(), 20);
    }

    @Then("I am back on login page")
    public void iAmBackOnLoginPage() throws Exception {
        try {
            ElementSteps.waitForVisibilityOfElement(LoginPage.getUserEmailField(),30);
        } catch (Exception e){
            throw new Exception("Unexpected behaviour after log out.");
        }
    }

    @When("I login on Salesforce")
    public void loginOnSalesforce() throws Exception{
        com.niji.pageObjects.salesforce.LoginPage.getUSernameField().sendKeys(DataManager.getData().salesforce_account);
        com.niji.pageObjects.salesforce.LoginPage.getPasswordField().sendKeys(DataManager.getData().salesforce_password);
        com.niji.pageObjects.salesforce.LoginPage.getConnectionButton().click();
        checkIfConnected();
    }
}
