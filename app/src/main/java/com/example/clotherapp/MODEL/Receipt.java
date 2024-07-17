package com.example.clotherapp.MODEL;

import java.util.Date;

public class Receipt {
    int id;
    int idUser;
    int idProduct; // Bổ sung trường IDProduct
    double totalMoney;
    String payMethod;
    Date paidDate;
    String color;
    int size;

    public Receipt(int id, int idUser, int idProduct, double totalMoney, String payMethod, Date paidDate, String color, int size) {
        this.id = id;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.totalMoney = totalMoney;
        this.payMethod = payMethod;
        this.paidDate = paidDate;
        this.color = color;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
