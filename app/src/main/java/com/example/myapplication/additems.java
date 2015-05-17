package com.example.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class additems extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    ArrayList<String> categoryName = new ArrayList<String>();
    ArrayList<String> categoryId = new ArrayList<String>();
    ProgressBar pg;
    String category ="";
    String []categories;
    String []splitCategory;
    ArrayAdapter<String> adapter;
    Spinner categorySpinner;
    int flag = 0;
    String resultes="";
    String name="";
    String price="";
    String description="";
    String cId = "";
    EditText Name;
    EditText Price;
    EditText Description;
    String addItemString = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additems);

        Name = (EditText) findViewById(R.id.AdmineditText3);
        Price = (EditText) findViewById(R.id.AdmineditText15);
        Description = (EditText) findViewById(R.id.AdmineditText16);
        categorySpinner = (Spinner) findViewById(R.id.Adminspinner);
        ImageView cancel = (ImageView) findViewById(R.id.AdminimageView8);
        cancel.setOnClickListener(this);
        Button add = (Button) findViewById(R.id.Adminbutton);
        add.setOnClickListener(this);
        pg = (ProgressBar) findViewById(R.id.AdminprogressBar2);
        AsyncCallWS task = new AsyncCallWS();
        task.execute();
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.AdminimageView8) {
            finish();
        }
        else if(v.getId() == R.id.Adminbutton) {
            name = Name.getText().toString();
            price = Price.getText().toString();
            description = Description.getText().toString();
            flag = 1;
            addItemString = name +"#"+ price +"#"+ description +"#"+cId;
            AsyncCallWS task = new AsyncCallWS();
            task.execute();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        cId = categoryId.get(position);
        Toast.makeText(this, "Category: " + categoryName.get(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Selections cleared.", Toast.LENGTH_SHORT).show();
    }


    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            if(flag == 0)
                category = WebService1.getItems("getCategories");
            else
                resultes = WebService1.InsertItem(addItemString,"InsertItem");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            //Make ProgressBar invisible
            pg.setVisibility(View.INVISIBLE);
            if(flag == 0) {
                Log.d("jhdlsah", category);
                categories = category.split(",");
                for(int i=0;i<categories.length;i++) {
                    splitCategory = categories[i].split("#");
                    categoryName.add(splitCategory[0]);
                    categoryId.add(splitCategory[1]);
                }
                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, categoryName);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                categorySpinner.setAdapter(adapter);
                categorySelection();
            }
            else {
                if(resultes.equals("True"))
                    Toast.makeText(getApplicationContext(), name+" add successfully ", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), name+" already exist! ", Toast.LENGTH_SHORT).show();
            }

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

    private void categorySelection() {
        categorySpinner.setOnItemSelectedListener(this);
    }
}
