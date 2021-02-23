package com.niji.steps.globalSteps;

import com.niji.data.DataManager;
import com.niji.factory.CapabilitiesManager;
import com.niji.factory.database.BDDConnectionClass;
import com.niji.factory.database.BDDConnectionFactory;
import com.niji.factory.database.BDDConnectionManager;
import cucumber.api.java.en.When;

import java.sql.ResultSet;

public class SQLSteps {

    @When("j'accede à la BDD \"(.+)\" \"(.+)\" \"(.+)\"")
    public static void setConnection(String url, String user, String password){
        BDDConnectionClass statement = BDDConnectionFactory.createInstance(url, user, password);
        BDDConnectionManager.setStatement(statement);
    }

    @When("je passe les contrats du compte \"(.+)\" à \"(.+)\"")
    public static void setContractStatus(String email, String status){
        String emailToUse = email;
        if (email.equals("email_auto")){
            switch (CapabilitiesManager.getCapabilities().capabilities.getCapability("platformName").toString()) {
                case "chrome":
                    emailToUse = DataManager.getData().os;
                    break;
            }
        }
        try {
            ResultSet rs = BDDConnectionManager.getStatement().statement.executeQuery("select * from contract like '%"+emailToUse+"%'");
            while ( rs.next() ) {
                String id = rs.getString("id");
                BDDConnectionManager.getStatement().statement.executeUpdate("update contract set state='"+status+"' where id='"+id+"'");
                BDDConnectionManager.getStatement().connection.commit();
            }
            BDDConnectionManager.getStatement().closeConnection();
        } catch (Exception e){

        }
    }
}
