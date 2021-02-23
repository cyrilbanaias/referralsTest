package com.niji.factory.database;

public class BDDConnectionFactory {

    public static BDDConnectionClass createInstance(String url, String user, String password){
        BDDConnectionClass statement = new BDDConnectionClass();
        statement.initialize(url, user, password);
        return statement;
    }
}