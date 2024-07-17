package com.example.clotherapp.MODEL;

public class Evaluate {
    int id,idUser,idProduct,star;
    String comment;

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

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Evaluate(int id, int idUser, int idProduct, int star, String comment) {
        this.id = id;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.star = star;
        this.comment = comment;
    }
}
