package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class offer extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        ImageView back = (ImageView) findViewById(R.id.AdminEditinfoback);
        back.setOnClickListener(this);

        ImageView addOffer = (ImageView) findViewById(R.id.Adminaddoffer);
        addOffer.setOnClickListener(this);

        ImageView editOffer = (ImageView) findViewById(R.id.Admineditoffer);
        editOffer.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.AdminEditinfoback) {
            finish();
        }
        else if(v.getId() == R.id.Adminaddoffer) {
            startActivity(new Intent(offer.this,addoffer.class));
        }
        else if(v.getId() == R.id.Admineditoffer) {
            startActivity(new Intent(offer.this,editoffer.class));
        }
    }
}
