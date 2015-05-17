package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Information extends ActionBarActivity implements View.OnClickListener {
    TextView resName;
    TextView resFace;
    TextView resAbout;
    String results;
    String []split;
    String name;
    String face;
    String about;
    Context context=this;
    ProgressBar pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        ImageView back = (ImageView) findViewById(R.id.AdminEditinfoback);
        back.setOnClickListener(this);
        Button editInfo = (Button) findViewById(R.id.AdmineditInfo);
        editInfo.setOnClickListener(this);
        pg = (ProgressBar) findViewById(R.id.AdminprogressBar4);

        resName = (TextView) findViewById(R.id.AdmintextView21);
        resFace = (TextView) findViewById(R.id.AdmintextView23);
        resAbout = (TextView) findViewById(R.id.AdmintextView25);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.AdminEditinfoback) {
            finish();
        }
        else if(v.getId() == R.id.AdmineditInfo) {
            Intent edit = new Intent(this,EditInfo.class);
            String []resInfo = {name,face,about};
            edit.putExtra("resInfo", resInfo);
            startActivity(edit);
        }
    }


    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            results = WebService1.AdmingetResInfo("1", "getResInfo");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            //Make ProgressBar invisible
            pg.setVisibility(View.INVISIBLE);
            split = results.split("#");
            name = split[1];
            face = split[2];
            about = split[3];
            resName.setText(name);
            resFace.setText(face);
            resAbout.setText(about);
        }

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            pg.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        AsyncCallWS task = new AsyncCallWS();
        task.execute();
    }
}
