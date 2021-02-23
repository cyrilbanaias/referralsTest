package com.niji.data;

public class DataManager {

    private static ThreadLocal<DataClass> data = new ThreadLocal<DataClass>();

    public static DataClass getData() {
        return data.get();
    }

    public static void setData(DataClass data_) {
        data.set(data_);
    }
}
