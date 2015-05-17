package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class Gallery extends ActionBarActivity {

    int indx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
    }

    public void prev_onclick(View v){
        indx = indx - 1;
        set_image(indx);
    }

    private void set_image(int i) {
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        if(i == 0)
            iv.setImageResource(R.drawable.palestine);
        else if(i == 1)
            iv.setImageResource(R.drawable.jordan);
        else if(i == 2)
            iv.setImageResource(R.drawable.lebanon);
        else if(i == 3)
            iv.setImageResource(R.drawable.syria);
        else if(i == 4)
            iv.setImageResource(R.drawable.qatar);
        else if(i == 5)
            iv.setImageResource(R.drawable.iraq);
        else if(i > 5) {
            indx = 0;
            set_image(indx);
        }
        else{
            indx = 5;
            set_image(indx);
        }
    }

    public void next_onclick(View v){
        indx = indx + 1;
        set_image(indx);
    }

    public void back3_onclick(View v){
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
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
}
