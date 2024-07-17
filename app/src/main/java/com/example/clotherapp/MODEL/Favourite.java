package com.example.clotherapp.MODEL;

public class Favourite {
    int id,idUser,idProduct;
    String nameUser,nameProduct,image;
    Double price;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
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

    public Favourite(int id, int idUser, int idProduct, String nameUser, String nameProduct, String image, Double price) {
        this.id = id;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.nameUser = nameUser;
        this.nameProduct = nameProduct;
        this.image = image;
        this.price = price;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }


}
