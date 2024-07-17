package com.example.clotherapp.MODEL;

public class Payment {
    int id,idUser;
    String accountNumber,bankName;

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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Payment(int id, int idUser, String accountNumber, String bankName) {
        this.id = id;
        this.idUser = idUser;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
    }
}
