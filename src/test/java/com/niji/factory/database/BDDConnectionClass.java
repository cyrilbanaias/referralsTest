package com.niji.factory.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BDDConnectionClass {

    public Connection connection = null;
    public Statement statement = null;

    public void initialize(String url, String user, String password) {
        connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Opened database successfully");
            statement = connection.createStatement();
        } catch (Exception e){
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    public void closeConnection(){
        try {
            statement.close();
            connection.close();
        } catch (Exception e){

        }
    }
}

