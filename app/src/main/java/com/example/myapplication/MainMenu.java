package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;


public class MainMenu extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void back1_onclick(View v){
        finish();
    }

    public void menu_onclick(View v){
        startActivity(new Intent(getApplicationContext(), Drinks.class));
    }

    public void gallery_onclick(View v){
        startActivity(new Intent(getApplicationContext(), Gallery.class));
    }

    public void evaluation_onclick(View v){
        startActivity(new Intent(getApplicationContext(), Evaluation.class));
    }

    public void offers_onclick(View v){
        startActivity(new Intent(getApplicationContext(), Offers.class));
    }

    public void about_rest_onclick(View v){
        startActivity(new Intent(getApplicationContext(), AboutRestaurant.class));

    }

    public void myacct_onclick(View v){
        startActivity(new Intent(getApplicationContext(), MyAccount.class));
    }

    public void bill_onclick(View v){
        startActivity(new Intent(getApplicationContext(), Bill.class));
    }

    public void order_onclick(View v){
        startActivity(new Intent(getApplicationContext(), Order.class));
    }

    public void fb_onclick(View v){
        WebView wv = (WebView) findViewById(R.id.webView2);
        String url = "https://www.facebook.com/pages/Ma63amiapp/1547798328806797?__mref=message_bubble";
        wv.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

    public void gps_onclick(View view) {
        startActivity(new Intent(this, MapsActivity.class));
    }

    public void restable_onclick(View view) {
        startActivity(new Intent(this, Registration.class));
    }
}
