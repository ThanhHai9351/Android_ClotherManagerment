package com.example.clotherapp.MODEL;

public class ChiTietSize {
    int idSize,idProduct;

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public ChiTietSize(int idSize, int idProduct) {
        this.idSize = idSize;
        this.idProduct = idProduct;
    }
}
