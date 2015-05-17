package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Categories extends ActionBarActivity {

    String[] cats = {"Drinks", "Deserts", "Salads", "Main Meals", "Sandwiches"};
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cats));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            if(position == 0)
                                                startActivity(new Intent(getApplicationContext(), Drinks.class));
                                        }
                                    }

        );

    }

    public void back2_onclick(View v){
        startActivity(new Intent(getApplicationContext(), MainMenu.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categories, menu);
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
