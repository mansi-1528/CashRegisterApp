package com.example.mb.cashregisterapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    Button one, two, three, four, five, six, seven, eight, nine, zero, clear, buy, managerBtn;
    TextView typeTV, quantityTV, totalTV;
    ListDataAdapter adapter;
    Double total = 0.0;
    ListView itemsListView;
    int index = -1;
    ArrayList<Product> stockList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Setup the data source
        initViews();
        setClickListeners();
        setListData();
        itemsListView.setOnItemClickListener(this);


    }

    private void setClickListeners() {
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        buy.setOnClickListener(this);
        clear.setOnClickListener(this);
        managerBtn.setOnClickListener(this);
    }

    private void setListData() {
        ((MyApp) getApplication()).setProductListData();

        stockList = ((MyApp) getApplication()).productList;

        adapter = new ListDataAdapter(((MyApp) getApplication()).productList, MainActivity.this);
        itemsListView.setAdapter(adapter);
    }

    private void initViews() {
        typeTV = findViewById(R.id.product_type);
        quantityTV = findViewById(R.id.quantity_tv);
        totalTV = findViewById(R.id.total_tv);
        one = findViewById(R.id.button1);
        two = findViewById(R.id.button2);
        three = findViewById(R.id.button3);
        four = findViewById(R.id.button4);
        five = findViewById(R.id.button5);
        six = findViewById(R.id.button6);
        seven = findViewById(R.id.button7);
        eight = findViewById(R.id.button8);
        nine = findViewById(R.id.button9);
        zero = findViewById(R.id.button0);
        clear = findViewById(R.id.buttonClear);
        buy = findViewById(R.id.buttonBuy);
        managerBtn = findViewById(R.id.buttonManager);
        itemsListView = findViewById(R.id.listview_products);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        typeTV.setText(stockList.get(i).getType());
        index = i;
        if (!quantityTV.getText().toString().equals("")) {
            total = Double.parseDouble(quantityTV.getText().toString()) * stockList.get(i).getPrice();
            totalTV.setText(String.format("%.2f", total));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonBuy:
                if (!totalTV.getText().toString().equals("")) {
                    showAlert();
                } else {
                    Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.buttonClear:
                setDefaultViews();
                break;
            case R.id.buttonManager:
                gotoManager();
                break;
            default:
                Button b = (Button) view;
                String num1 = quantityTV.getText().toString();
                String num2 = b.getText().toString();
                String num = num1 + num2;

                if (index!=-1) {
                    addTotal(num);
                }
                quantityTV.setText(String.format("%s%s", num1, num2));

                if (Integer.parseInt(quantityTV.getText().toString()) > stockList.get(index).getQuantity()) {
                    Toast.makeText(this, "No enough quantity in the stock", Toast.LENGTH_SHORT).show();
                    setDefaultViews();
                }
        }
    }

    private void gotoManager() {
        Intent intentManager = new Intent(MainActivity.this, ManagerActivity.class);
        startActivity(intentManager);
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Your purchase is " + quantityTV.getText().toString() + " " + stockList.get(index).getType() + " for " + totalTV.getText().toString())
                .setTitle("Thank You for your purchase");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // open a report activity
                setDefaultViews();
            }
        });
        builder.create().show();

        Product obj = ((MyApp) getApplication()).productList.get(index);
        obj.setQuantity(obj.getQuantity() - Integer.parseInt(quantityTV.getText().toString()));
        ((MyApp) getApplication()).productList.set(index, obj);
        adapter.notifyDataSetChanged();
        Date d1 = new Date();
        History h1 = new History(obj.getType(), Double.parseDouble(totalTV.getText().toString()), Integer.parseInt(quantityTV.getText().toString()), d1);
        ((MyApp) getApplication()).historyList.add(h1);
    }

    private void setDefaultViews() {
        quantityTV.setText("");
        quantityTV.setHint("Quantity");
        totalTV.setText("");
        totalTV.setHint("Total");
        typeTV.setText("");
        typeTV.setHint("Product Type");
    }

    private void addTotal(String qtty) {
        Double price = stockList.get(index).getPrice();
        total = price * Double.parseDouble(qtty);
        totalTV.setText(String.format("%.2f", total));
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}