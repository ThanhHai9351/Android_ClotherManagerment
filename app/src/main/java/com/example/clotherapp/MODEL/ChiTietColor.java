package com.example.clotherapp.MODEL;

public class ChiTietColor {
    int idColor,idProduct;

    public int getIdColor() {
        return idColor;
    }

    public ChiTietColor(int idColor, int idProduct) {
        this.idColor = idColor;
        this.idProduct = idProduct;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}
