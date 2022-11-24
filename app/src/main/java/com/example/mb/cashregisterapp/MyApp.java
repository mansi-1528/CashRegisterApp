package com.example.mb.cashregisterapp;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {
   public ArrayList<Product> productList=new ArrayList<>(0);
   public ArrayList<History> historyList=new ArrayList<>(0);

  public void setProductListData(){
      if(productList.isEmpty()){
         productList.add(new Product("Shirt", 20.40, 10));
         productList.add(new Product("Pants", 35.20, 20));
         productList.add(new Product("Hats", 15.40, 26));
         productList.add(new Product("T-Shirts", 30.00, 22));
         productList.add(new Product("Joggers", 40.80, 30));
         productList.add(new Product("Glasses", 20.10, 50));
      }

   }
}
