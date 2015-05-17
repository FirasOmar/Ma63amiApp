package com.example.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;


public class Tables extends ActionBarActivity {

    String table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tables, menu);
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

    public void ok_onclick(View view) {
        RadioButton rb1on = (RadioButton) findViewById(R.id.radioButton4);
        RadioButton rb1off = (RadioButton) findViewById(R.id.radioButton5);
        RadioButton rb2on = (RadioButton) findViewById(R.id.radioButton6);
        RadioButton rb2off = (RadioButton) findViewById(R.id.radioButton7);
        RadioButton rb3on = (RadioButton) findViewById(R.id.radioButton8);
        RadioButton rb3off = (RadioButton) findViewById(R.id.radioButton9);
        RadioButton rb4on = (RadioButton) findViewById(R.id.radioButton10);
        RadioButton rb4off = (RadioButton) findViewById(R.id.radioButton11);
        RadioButton rb5on = (RadioButton) findViewById(R.id.radioButton12);
        RadioButton rb5off = (RadioButton) findViewById(R.id.radioButton13);
        RadioButton rb6on = (RadioButton) findViewById(R.id.radioButton14);
        RadioButton rb6off = (RadioButton) findViewById(R.id.radioButton15);

        if(rb1on.isChecked()){
            table = "1#0";
            AsyncCallWS1 task = new AsyncCallWS1();
            task.execute();
        }
        else if(rb1off.isChecked()){
            table = "1#1";
            AsyncCallWS1 task = new AsyncCallWS1();
            task.execute();
        }
        if(rb2on.isChecked()){
            table = "2#0";
            AsyncCallWS1 task = new AsyncCallWS1();
            task.execute();
        }
        else if(rb2off.isChecked()){
            table = "2#1";
            AsyncCallWS1 task = new AsyncCallWS1();
            task.execute();
        }
        if(rb3on.isChecked()){
            table = "3#0";
            AsyncCallWS1 task = new AsyncCallWS1();
            task.execute();
        }
        else if(rb3off.isChecked()){
            table = "3#1";
            AsyncCallWS1 task = new AsyncCallWS1();
            task.execute();
        }
        if(rb4on.isChecked()){
            table = "4#0";
            AsyncCallWS1 task = new AsyncCallWS1();
            task.execute();
        }
        else if(rb4off.isChecked()){
            table = "4#1";
            AsyncCallWS1 task = new AsyncCallWS1();
            task.execute();
        }
        if(rb5on.isChecked()){
            table = "5#0";
            AsyncCallWS1 task = new AsyncCallWS1();
            task.execute();
        }
        else if(rb5off.isChecked()){
            table = "5#1";
            AsyncCallWS1 task = new AsyncCallWS1();
            task.execute();
        }
        if(rb6on.isChecked()){
            table = "6#0";
            AsyncCallWS1 task = new AsyncCallWS1();
            task.execute();
        }
        else if(rb6off.isChecked()){
            table = "6#1";
            AsyncCallWS1 task = new AsyncCallWS1();
            task.execute();
        }
    }

    private class AsyncCallWS1 extends AsyncTask<String, Void, Void> {

        String status;

        @Override
        protected Void doInBackground(String... params) {
            status = WebServices.setTableStatus(table, "setTableStatus");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(status.equals("true"))
                Toast.makeText(getApplicationContext(), "Your request is sent successfully", Toast.LENGTH_LONG).show();
            else{
                Toast.makeText(getApplicationContext(), "Sorry!! Your request wasn't sent successfully", Toast.LENGTH_LONG).show();
            }


        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

}
