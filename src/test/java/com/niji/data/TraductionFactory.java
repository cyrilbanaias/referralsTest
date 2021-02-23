package com.niji.data;

public class TraductionFactory {

    public static TraductionClass createInstance() {
        TraductionClass trad = new TraductionClass();
        trad.initialize();
        return trad;
    }
}

