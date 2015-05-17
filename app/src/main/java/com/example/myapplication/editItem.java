package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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


public class editItem extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    ArrayList<String> []catigories=null;
    int numOfCatigories = 4;
    String itemValues[];
    ArrayList<String> categoryName = new ArrayList<String>();
    ArrayList<String> categoryId = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Context context = this;
    int flag =0;
    String category ="";
    ProgressBar pg;
    String []categories;
    String []splitCategory;
    Spinner categorySpinner;
    EditText nameText;
    EditText priceText;
    EditText desText;
    String name;
    String price;
    String disc;
    String catID;
    String itemID;
    String infoLine;
    String resultes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        pg = (ProgressBar) findViewById(R.id.progressBar3);
        categorySpinner = (Spinner) findViewById(R.id.Admincategoryadd);
        /*String[] items = new String[]{"Drinks", "Bizza", "cooked food", "Sandwiches"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        category.setAdapter(adapter);*/

        Intent intent = getIntent();
        itemValues = intent.getStringArrayExtra("itemInfo");
        nameText = (EditText) findViewById(R.id.AdminEditeditText);
        priceText = (EditText) findViewById(R.id.AdminEditeditText2);
        desText = (EditText) findViewById(R.id.AdminEdititemDescription);
        nameText.setText(itemValues[0]);
        priceText.setText(itemValues[1]);
        desText.setText(itemValues[2]);
        catID = itemValues[3];
        itemID = itemValues[4];
        Button cancel = (Button) findViewById(R.id.AdminEditbutton8);
        cancel.setOnClickListener(this);
        Button edit = (Button) findViewById(R.id.AdminEditbutton2);
        edit.setOnClickListener(this);
        ImageView back = (ImageView) findViewById(R.id.AdminEditimageView8);
        back.setOnClickListener(this);
        AsyncCallWS task = new AsyncCallWS();
        task.execute();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.AdminEditbutton8) {
            infoLine = itemID;
            flag = 2;
            AsyncCallWS task = new AsyncCallWS();
            task.execute();
            finish();
        }
        else if(v.getId() == R.id.AdminEditbutton2) {

            flag = 1;
            name = nameText.getText().toString();
            price = priceText.getText().toString();
            disc = desText.getText().toString();

            infoLine = name+"#"+price+"#"+disc+"#"+catID+"#"+itemID;
            AsyncCallWS task = new AsyncCallWS();
            task.execute();
        }
        else if(v.getId() == R.id.AdminEditimageView8) {
            finish();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        catID = categoryId.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            if (flag == 0)
                category = WebService1.getItems("getCategories");
            else if(flag == 1)
                resultes = WebService1.editItem(infoLine, "editItem");
            else if(flag ==2)
                resultes = WebService1.editItem(infoLine,"deleteItem");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            //Make ProgressBar invisible
            pg.setVisibility(View.INVISIBLE);
            if(flag == 0) {
                categories = category.split(",");
                for(int i=0;i<categories.length;i++) {
                    splitCategory = categories[i].split("#");
                    categoryName.add(splitCategory[0]);
                    categoryId.add(splitCategory[1]);
                }
                adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,categoryName);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                categorySpinner.setAdapter(adapter);
                categorySelection();

            }
            else if (flag == 1) {
                if(resultes.equals("true"))
                    Toast.makeText(getApplicationContext(), "Editing Completed", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "A problem occured, item not edited", Toast.LENGTH_LONG).show();
            }
            else if (flag == 2) {
                if(resultes.equals("true"))
                    Toast.makeText(getApplicationContext(), "Item Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "A problem occured, item not deleted", Toast.LENGTH_LONG).show();
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
