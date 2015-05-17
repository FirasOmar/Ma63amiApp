package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;


public class AboutRestaurant extends ActionBarActivity {

    TextView tv1;
    TextView tv3;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_restaurant);

        tv1 = (TextView) findViewById(R.id.textView47);
        btn = (Button) findViewById(R.id.button2);
        tv3 = (TextView) findViewById(R.id.textView51);

        AsyncCallWS task = new AsyncCallWS();
        task.execute();
    }

    public void abotback_onclick(View v){
        finish();
    }

    public void tv_onclick(View view) {
        WebView wv = (WebView) findViewById(R.id.webView3);
        String url = (String) btn.getText();
        wv.loadUrl(url);
    }


    private class AsyncCallWS extends AsyncTask<String, Void, Void> {

        String results;

        @Override
        protected Void doInBackground(String... params) {
            results = WebServices.AdmingetResInfo("1", "getResInfo");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            String[] info = results.split("#");
            tv1.setText(info[1]);
            btn.setText(info[2]);
            tv3.setText(info[3]);
        }

        @Override
        protected void onPreExecute() {
        }


        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }}
