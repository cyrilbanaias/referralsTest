package com.niji.data;

public class Session {

    public static class Account {

        public Account(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public final String email;
        public final String password;
    }

    private Account freshCreatedAccount;


    public void setFreshlyCreatedAccount(String email, String password) {
        this.freshCreatedAccount = new Account(email, password);
    }

    public Account getFreshlyCreatedAccount() {
        return freshCreatedAccount;
    }
}
