package com.example.clotherapp.MODEL;

public class Color {
    int id;
    String color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Color(int id, String color) {
        this.id = id;
        this.color = color;
    }
}
