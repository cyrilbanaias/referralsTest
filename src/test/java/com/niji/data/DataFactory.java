package com.niji.data;

public class DataFactory {

    public static DataClass createInstance() {
        DataClass data = new DataClass();
        data.initialize();
        return data;
    }
}

