package com.example.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
//import android.R;

public class addoffer extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addoffer);

        Button cancel = (Button) findViewById(R.id.Admincancel);
        cancel.setOnClickListener(this);

        ImageView back = (ImageView) findViewById(R.id.AdminEditinfoback);
        back.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.Admincancel) {
            finish();
        }
        else if(v.getId() == R.id.AdminEditinfoback) {
            finish();
        }
    }
}
