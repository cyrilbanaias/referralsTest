package com.niji.factory.database;

public class BDDConnectionManager {
    private static ThreadLocal<BDDConnectionClass> statement = new ThreadLocal();

    public static BDDConnectionClass getStatement() {
        return statement.get();
    }

    public static void setStatement(BDDConnectionClass statement_) {
        statement.set(statement_);
    }
}

