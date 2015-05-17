package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class viewOrder extends ActionBarActivity implements View.OnClickListener {
    String [] itemValues;
    String [] itemsLine;
    String [] itemsInfo;
    String []names;
    String []prices;
    String []quantities;
    TextView order;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        Intent intent = getIntent();
        itemValues = intent.getStringArrayExtra("itemInfo");
        TextView name = (TextView) findViewById(R.id.AdmintextView43);
        TextView price = (TextView) findViewById(R.id.AdmintextView67);
        TextView quantity = (TextView) findViewById(R.id.AdmintextView75);
        order = (TextView) findViewById(R.id.AdmintextView39);
        back = (ImageView) findViewById(R.id.Adminvieworderback);
        back.setOnClickListener(this);
        order.setText("Order #"+itemValues[0]);
        itemsLine = itemValues[1].split(",");
        names = new String[itemsLine.length];
        prices = new String[itemsLine.length];
        quantities = new String[itemsLine.length];

        for(int i=0;i< itemsLine.length;i++) {
            itemsInfo = itemsLine[i].split("#");
            name.append(itemsInfo[0]+"\n\n");
            price.append(itemsInfo[1]+"$\n\n");
            quantity.append(itemsInfo[2]+"\n\n");
        }
           }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.Adminvieworderback) {
            finish();
        }
    }
}
