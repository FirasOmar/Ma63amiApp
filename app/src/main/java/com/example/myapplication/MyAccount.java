package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MyAccount extends ActionBarActivity {

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        tv1 = (TextView) findViewById(R.id.id);
        tv2 = (TextView) findViewById(R.id.name);
        tv3 = (TextView) findViewById(R.id.passwd);
        tv4 = (TextView) findViewById(R.id.no);
        tv5 = (TextView) findViewById(R.id.email);

        tv1.setText(MainActivity.user_info[0]);
        tv2.setText(MainActivity.user_info[1]);
        tv3.setText(MainActivity.user_info[2]);
        tv4.setText(MainActivity.user_info[4]);
        tv5.setText(MainActivity.user_info[5]);
    }

    public void my_accback_onclick(View v){
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_account, menu);
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
