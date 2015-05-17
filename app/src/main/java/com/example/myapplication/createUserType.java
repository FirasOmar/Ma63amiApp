package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class createUserType extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_type);

        Button headChef = (Button) findViewById(R.id.Adminbutton5);
        headChef.setOnClickListener(this);
        Button headWaiter = (Button) findViewById(R.id.Adminbutton3);
        headWaiter.setOnClickListener(this);
        Button cachier = (Button) findViewById(R.id.Adminbutton6);
        cachier.setOnClickListener(this);
        ImageView back = (ImageView) findViewById(R.id.AdminimageView5);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // HeadChef => 2
        if(view.getId() == R.id.Adminbutton5) {
            Intent create = new Intent(this,createUser.class);
            create.putExtra("userType","2");
            startActivity(create);
        }
        //Head Waiter => 3
        else if(view.getId() == R.id.Adminbutton3) {
            Intent create = new Intent(this,createUser.class);
            create.putExtra("userType","3");
            startActivity(create);
        }
        // Cachier => 4
        else if(view.getId() == R.id.Adminbutton6) {
            Intent create = new Intent(this,createUser.class);
            create.putExtra("userType","4");
            startActivity(create);
        }
        else if(view.getId() == R.id.AdminimageView5) {
            finish();
        }
    }
}
