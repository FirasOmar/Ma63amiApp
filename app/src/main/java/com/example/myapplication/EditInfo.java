package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


public class EditInfo extends ActionBarActivity implements View.OnClickListener {
    String results;
    EditText resName;
    EditText resFace;
    EditText resAbout;
    String name = "";
    String face = "";
    String about = "";
    Context context = this;
    String infoLine;
    ProgressBar pg;
    String []resInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        resName = (EditText) findViewById(R.id.AdmineditText11);
        resFace = (EditText) findViewById(R.id.AdmineditText13);
        resAbout = (EditText) findViewById(R.id.AdmineditText14);
        pg = (ProgressBar) findViewById(R.id.AdminprogressBar5);
        ImageView back = (ImageView) findViewById(R.id.AdminEditinfoback);
        back.setOnClickListener(this);
        Button edit = (Button) findViewById(R.id.Adminbutton4);
        edit.setOnClickListener(this);

        Intent intent = getIntent();
        resInfo = intent.getStringArrayExtra("resInfo");
        resName.setText(resInfo[0]);
        resFace.setText(resInfo[1]);
        resAbout.setText(resInfo[2]);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.AdminEditinfoback) {
            finish();
        }
        else if(v.getId() == R.id.Adminbutton4) {
            name = resName.getText().toString();
            face = resFace.getText().toString();
            about = resAbout.getText().toString();
            infoLine = "1#"+name +"#"+face+"#"+about;
            AsyncCallWS task = new AsyncCallWS();
            task.execute();
        }
    }


    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            results = WebService1.AdminupdateResInfo(infoLine, "updateResInfo");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            //Make ProgressBar invisible
            pg.setVisibility(View.INVISIBLE);
            if(results.equals("True"))
                Toast.makeText(context,"Edit succeeded",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context,"Faile to edit",Toast.LENGTH_SHORT).show();
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

}
