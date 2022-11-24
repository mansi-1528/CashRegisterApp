package com.example.mb.cashregisterapp;

import android.os.Parcelable;

public class Product {

    String type;
    Double price;
    int quantity;

    public Product(String type, Double price, int quantity) {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product: " +
                "\ttype:" + type+
                "\tprice:" + price +
                "\tquantity:" + quantity;
    }
}
