package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class Main extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView  menu = (ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(this);

        ImageView bill = (ImageView) findViewById(R.id.bill);
        bill.setOnClickListener(this);

        ImageView gallery = (ImageView) findViewById(R.id.gallary);
        gallery.setOnClickListener(this);

        ImageView exit = (ImageView) findViewById(R.id.exit);
        exit.setOnClickListener(this);

        ImageView table = (ImageView) findViewById(R.id.table);
        table.setOnClickListener(this);

        ImageView offer = (ImageView) findViewById(R.id.offer);
        offer.setOnClickListener(this);

        ImageView orders = (ImageView) findViewById(R.id.order);
        orders.setOnClickListener(this);

        ImageView restInfo = (ImageView) findViewById(R.id.restInfo);
        restInfo.setOnClickListener(this);

        ImageView feedback = (ImageView) findViewById(R.id.feedback);
        feedback.setOnClickListener(this);

        ImageView payment = (ImageView) findViewById(R.id.payment);
        payment.setOnClickListener(this);

        ImageView statistics = (ImageView) findViewById(R.id.statistics);
        statistics.setOnClickListener(this);
        ImageView createUser = (ImageView) findViewById(R.id.imageView6);
        createUser.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.menu) {
            startActivity(new Intent(Main.this,EditMenu.class));
        }
        else if(v.getId() == R.id.exit) {
            finish();
        }
        else if(v.getId() == R.id.bill) {
            startActivity(new Intent(Main.this,edit_BillUI.class));
        }
        else if(v.getId() == R.id.gallary) {
            startActivity(new Intent(Main.this,edit_gallary.class));
        }
        else if(v.getId() == R.id.table) {
            startActivity(new Intent(Main.this,Tables.class));
        }
        else if(v.getId() == R.id.offer) {
            startActivity(new Intent(Main.this,offer.class));
        }
        else if(v.getId() == R.id.order) {
            startActivity(new Intent(Main.this,Orders.class));
        }

        else if(v.getId() == R.id.restInfo) {
            startActivity(new Intent(Main.this,Information.class));
        }
        else if(v.getId() == R.id.feedback) {
            startActivity(new Intent(Main.this,Feedback.class));
        }
        else if(v.getId() == R.id.payment) {
            startActivity(new Intent(Main.this,Payments.class));
        }
        else if(v.getId() == R.id.statistics) {
            startActivity(new Intent(Main.this,Statistics.class));
        }
        else if(v.getId() == R.id.imageView6) {
            startActivity(new Intent(Main.this,createUserType.class));
        }
    }
}
