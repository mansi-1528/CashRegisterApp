package com.example.mb.cashregisterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener {
Button historyBtn,restockBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        historyBtn=findViewById(R.id.buttonHistory);
        restockBtn=findViewById(R.id.buttonRestock);
        historyBtn.setOnClickListener(this);
        restockBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonHistory){
        Intent i1=new Intent(ManagerActivity.this,ShowHistoryActivity.class);
        startActivity(i1);
        }else if(view.getId()==R.id.buttonRestock){
            Intent i2=new Intent(ManagerActivity.this,RestockActivity.class);
            startActivity(i2);

        }
    }


}