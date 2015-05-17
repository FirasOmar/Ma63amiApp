package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class Feedback extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ImageView iFeedback = (ImageView) findViewById(R.id.Adminifeedback);
        iFeedback.setOnClickListener(this);

        ImageView rFeedback = (ImageView) findViewById(R.id.Adminrfeedback);
        rFeedback.setOnClickListener(this);

        ImageView back = (ImageView) findViewById(R.id.AdminEditinfoback);
        back.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.Adminifeedback) {
            startActivity(new Intent(Feedback.this,ItemFeedback.class));
        }
        else if(v.getId() == R.id.Adminrfeedback) {
            startActivity(new Intent(Feedback.this,RestaurantFeedback.class));
        }
        else if(v.getId() == R.id.AdminEditinfoback) {
            finish();
        }
    }
}
