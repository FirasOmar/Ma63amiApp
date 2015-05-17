package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;


public class Bill extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar3);
        ListView list = (ListView) findViewById(R.id.list);
        if(Drinks.order != null){
            TextView tv = (TextView) findViewById(R.id.textView35);
            tv.setVisibility(View.INVISIBLE);
            pb.setVisibility(View.VISIBLE);

            String names[] = new String[Drinks.order.size()];
            int sum = 0;
            for(int i=0;i<Drinks.order.size();i++){
                names[i] = Drinks.order.get(i)[0];
                int price = Integer.parseInt(Drinks.order.get(i)[1]);
                int quant = Integer.parseInt(Drinks.order.get(i)[2]);
                sum += (price * quant);
            }
            list.setAdapter(new MyAdapter(this, names));
            TextView tv1 = (TextView) findViewById(R.id.textView23);
            tv1.setText(sum + "");
            TextView tv2 = (TextView) findViewById(R.id.billid);
            tv2.setText("Bill #" + Drinks.bill_id);
            tv2.setVisibility(View.VISIBLE);
            pb.setVisibility(View.INVISIBLE);
        }
    }

    public void billback_onclick(View v){
        finish();
    }

    public void pay_onclick(View v){
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bill, menu);
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

    private class MyAdapter extends ArrayAdapter<String> {


        public MyAdapter(Context context, String[] objects) {
            super(context, R.layout.bill_list_item,  objects);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater infltr = LayoutInflater.from(getContext());
            View row = infltr.inflate(R.layout.bill_list_item, parent, false);
            String item = getItem(position);

            TextView textView = (TextView) row.findViewById(R.id.textView30);
            TextView textView1 = (TextView) row.findViewById(R.id.textView31);
            TextView textView2 = (TextView) row.findViewById(R.id.textView32);
            TextView textView3 = (TextView) row.findViewById(R.id.textView34);

            textView.setText(item);
            textView1.setText(Drinks.order.get(position)[1]);
            textView2.setText(Drinks.order.get(position)[2]);
            int total = (Integer.parseInt(Drinks.order.get(position)[1]) * Integer.parseInt(Drinks.order.get(position)[2]));
            textView3.setText(total + "");
            return row;
        }

    }


}
