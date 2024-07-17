package com.example.clotherapp.DAO;

import com.example.clotherapp.MODEL.DataHolder;

public class ConnectDB {
    private static String ip= DataHolder.getInstance().getIp();
    private static String url= ip;

    public static String getUrl() {
        return url;
    }
    public static void setUrl(String url) {
        ConnectDB.url = url;
    }

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        ConnectDB.ip = ip;
    }


}
