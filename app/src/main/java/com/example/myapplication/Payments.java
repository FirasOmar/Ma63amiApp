package com.example.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class Payments extends ActionBarActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        ImageView back = (ImageView) findViewById(R.id.AdminPaymentsimageView8);
        back.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.AdminPaymentsimageView8) {
            finish();
        }
    }
}
