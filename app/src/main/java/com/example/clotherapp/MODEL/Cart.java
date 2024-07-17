package com.example.clotherapp.MODEL;

import android.content.Context;

import com.example.clotherapp.DAO.DAOCart;

import org.json.JSONException;

import java.util.ArrayList;

public class Cart {
        int id;
        int user;

        int idProduct;
        String image,name,nameCategory;
        double price;
        int quantityReview,quantity;
        String color;
        int size;
        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }
        public int getIdProduct() {
                return idProduct;
        }

        public void setIdProduct(int idProduct) {
                this.idProduct = idProduct;
        }
        public int getUser() {
                return user;
        }

        public void setUser(int user) {
                this.user = user;
        }

        public String getImage() {
                return image;
        }

        public void setImage(String image) {
                this.image = image;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getNameCategory() {
                return nameCategory;
        }

        public void setNameCategory(String nameCategory) {
                this.nameCategory = nameCategory;
        }

        public double getPrice() {
                return price;
        }

        public void setPrice(float price) {
                this.price = price;
        }

        public int getQuantityReview() {
                return quantityReview;
        }

        public void setQuantityReview(int quantityReview) {
                this.quantityReview = quantityReview;
        }

        public int getQuantity() {
                return quantity;
        }

        public void setQuantity(int quantity) {
                this.quantity = quantity;
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


        public Cart(int id, int user, int idProduct, String image, String name, String nameCategory, double price, int quantityReview, int quantity, String color, int size) {
                this.id = id;
                this.user = user;
                this.idProduct = idProduct;
                this.image = image;
                this.name = name;
                this.nameCategory = nameCategory;
                this.price = price;
                this.quantityReview = quantityReview;
                this.quantity = quantity;
                this.color = color;
                this.size = size;
        }

        public Cart() {
        }
        DAOCart dao=new DAOCart();
        public void getAllCarts(Context context, int idUser, DAOCart.CartLoadedCallback callback){
                dao.getAllCarts(context,idUser, callback);
        }
        public void addCart(Context context,int idUser,int idProduct, int quantity, Double money, String color, int size){
                try {
                        dao.addCart(context,idUser,idProduct,quantity,money,color,size);
                } catch (JSONException e) {
                        throw new RuntimeException(e);
                }
        }
        public void updateQuantity (Context context,int idUser,int idProduct, int quantity,String color, int size){
                dao.updateQuantity(context,idUser,idProduct,quantity,color,size);
        }
        public void deleteProductInCart(Context context,int idUser,int idProduct){
                dao.deleteProductInCart(context,idUser,idProduct);
        }
}
