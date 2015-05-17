package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class Statistics extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ImageView cStatistics = (ImageView) findViewById(R.id.Admincstatistics);
        cStatistics.setOnClickListener(this);

        ImageView sStatistics = (ImageView) findViewById(R.id.Adminsstatistics);
        sStatistics.setOnClickListener(this);

        ImageView back = (ImageView) findViewById(R.id.AdminEditinfoback);
        back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.Admincstatistics) {
            startActivity(new Intent(Statistics.this,CStatistics.class));
        }
        else if(v.getId() == R.id.Adminsstatistics) {
            startActivity(new Intent(Statistics.this,SalesStatistics.class));
        }
        else if(v.getId() == R.id.AdminEditinfoback) {
            finish();
        }
    }
}
