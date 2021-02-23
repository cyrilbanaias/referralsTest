package com.niji.data;

public class TraductionManager {

    private static ThreadLocal<TraductionClass> trad = new ThreadLocal<TraductionClass>();

    public static TraductionClass getTraduction() {
        return trad.get();
    }

    public static void setTraduction(TraductionClass trad_) {
        trad.set(trad_);
    }
}
