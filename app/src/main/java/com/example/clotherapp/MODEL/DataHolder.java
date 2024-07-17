package com.example.clotherapp.MODEL;

public class DataHolder {
    private static final DataHolder instance = new DataHolder();

    private int id;
    private String name;
    private String username;

    private String ip = "https://tam1111.000webhostapp.com/";

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getIdFavourite() {
        return idFavourite;
    }

    public void setIdFavourite(int idFavourite) {
        this.idFavourite = idFavourite;
    }

    private int idFavourite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private DataHolder() {}

    public static DataHolder getInstance() {
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}

