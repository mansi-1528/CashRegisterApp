package com.example.mb.cashregisterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class ListDataAdapter extends BaseAdapter {

   ArrayList<Product> list;
   Context context;

   public ListDataAdapter(ArrayList<Product> list, Context context) {
      this.list = list;
      this.context = context;
   }

   @Override
   public int getCount() {
      return list.size();
   }

   @Override
   public Object getItem(int i) {
      return list.get(i);
   }

   @Override
   public long getItemId(int i) {
      return 0;
   }

   @Override
   public View getView(int i, View view, ViewGroup viewGroup) {


      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View v = inflater.inflate(R.layout.list_row,viewGroup,false);

      TextView productType = v.findViewById(R.id.list_type);
      TextView productPrice = v.findViewById(R.id.list_price);
      TextView productQty = v.findViewById(R.id.list_quantity);

      productType.setText(list.get(i).type);
      productPrice.setText(String.valueOf(list.get(i).price));
      productQty.setText(String.valueOf(list.get(i).quantity));

      return v;
   }
}
