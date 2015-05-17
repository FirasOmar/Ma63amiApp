package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Order extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        TextView tv = (TextView) findViewById(R.id.textView43);
        if(Drinks.getOrder_status() == null){
            tv.setText("You havn't order yet");
            tv.setTextColor(Color.RED);
        }
        else{
            TextView tv1 = (TextView) findViewById(R.id.textView44);
            tv1.setText("order #" + Drinks.order_id);
            tv1.setVisibility(View.VISIBLE);
            if(Drinks.getOrder_status().equals("2")){
            tv.setText("Sorry!! Your order hasn't been confirmed");
            tv.setTextColor(Color.RED);
            }
            else if(Drinks.getOrder_status().equals("1")){
                tv.setText("Your order has been confirmed");
                tv.setTextColor(Color.GREEN);
            }
            else{
                tv.setText("Your order hasn't been confirmed yet");
                tv.setTextColor(Color.BLUE);
            }
        }
    }

    public void ordback_onclick(View v){
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
