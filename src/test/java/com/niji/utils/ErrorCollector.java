package com.niji.utils;

import cucumber.api.java.After;

public class ErrorCollector extends org.junit.rules.ErrorCollector {
    @After
    public void reportErrors() throws Throwable {
        this.verify();
    }
}