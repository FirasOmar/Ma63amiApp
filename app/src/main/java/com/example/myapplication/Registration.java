package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;


public class Registration extends ActionBarActivity {

    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;
    ImageView iv5;
    ImageView iv6;
    final Handler handler = new Handler();
    final Timer timer = new Timer();
    TimerTask doAsynchronousTask;
    private String tableRequest;
    String conf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        iv1 = (ImageView) findViewById(R.id.table1);
        iv2 = (ImageView) findViewById(R.id.table2);
        iv3 = (ImageView) findViewById(R.id.table3);
        iv4 = (ImageView) findViewById(R.id.table4);
        iv5 = (ImageView) findViewById(R.id.table5);
        iv6 = (ImageView) findViewById(R.id.table6);

        AsyncCallWS task = new AsyncCallWS();
        task.execute();

    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        doAsynchronousTask.cancel();
    }

    public void on_click(View view) {
        ImageView iv = (ImageView) view;
        if(iv.getId() == R.id.table1){
            tableRequest = MainActivity.user_info[0] + "#" + "1";

        }
        else if(iv.getId() == R.id.table2){
            tableRequest = MainActivity.user_info[0] + "#" + "2";

        }
        else if(iv.getId() == R.id.table3){
            tableRequest = MainActivity.user_info[0] + "#" + "3";

        }
        else if(iv.getId() == R.id.table4){
            tableRequest = MainActivity.user_info[0] + "#" + "4";

        }
        else if(iv.getId() == R.id.table5){
            tableRequest = MainActivity.user_info[0] + "#" + "5";

        }
        else if(iv.getId() == R.id.table6){
            tableRequest = MainActivity.user_info[0] + "#" + "6";

        }
        if(tableRequest != null){
            AsyncCallWS1 task1 = new AsyncCallWS1();
            task1.execute();
        }
    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void> {

        String tablesInfo;

        @Override
        protected Void doInBackground(String... params) {
            tablesInfo= WebServices.getTables("getTables");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            String[] tables = tablesInfo.split(",");
            for(int i=0;i<tables.length;i++) {
                String[] table = tables[i].split("#");
                if(i == 0){
                    if(table[2].equals("0"))
                        iv1.setBackgroundColor(Color.GREEN);
                    else if(table[2].equals("0")){
                        iv1.setBackgroundColor(Color.RED);
                        iv1.setEnabled(false);
                    }
                }
                else if(i == 1){
                    if(table[2].equals("0"))
                        iv2.setBackgroundColor(Color.GREEN);
                    else if(table[2].equals("0")){
                        iv2.setBackgroundColor(Color.RED);
                        iv2.setEnabled(false);
                    }
                }
                else if(i == 2){
                    if(table[2].equals("0"))
                        iv3.setBackgroundColor(Color.GREEN);
                    else if(table[2].equals("0")){
                        iv3.setBackgroundColor(Color.RED);
                        iv3.setEnabled(false);
                    }
                }
                else if(i == 3){
                    if(table[2].equals("0"))
                        iv4.setBackgroundColor(Color.GREEN);
                    else if(table[2].equals("0")){
                        iv4.setBackgroundColor(Color.RED);
                        iv4.setEnabled(false);
                    }
                }
                else if(i == 4){
                    if(table[2].equals("0"))
                        iv5.setBackgroundColor(Color.GREEN);
                    else if(table[2].equals("0")){
                        iv5.setBackgroundColor(Color.RED);
                        iv5.setEnabled(false);
                    }
                }
                else{

                    if(table[2].equals("0"))
                        iv6.setBackgroundColor(Color.GREEN);
                    else if(table[2].equals("0")){
                        iv6.setBackgroundColor(Color.RED);
                        iv6.setEnabled(false);
                    }
                }
            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

    private class AsyncCallWS1 extends AsyncTask<String, Void, Void> {

        String request;

        @Override
        protected Void doInBackground(String... params) {
            request = WebServices.requestTable(tableRequest,"requestTable");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(request.equals("True")){
                Toast.makeText(getApplicationContext(), "Your request is sent successfully", Toast.LENGTH_LONG).show();
                Log.d("kjbnkb", tableRequest);
                doAsynchronousTask = new TimerTask()
                {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            public void run() {
                                try
                                {

                                    new AsyncCallWS2().execute();

                                }
                                catch (Exception e)
                                {

                                }
                                if(conf != null) {
                                    Log.d("dnkjdsnv", conf);
                                    if (conf.equals("2")) {
                                        AsyncCallWS3 task2 = new AsyncCallWS3();
                                        task2.execute();
                                        Toast.makeText(getApplicationContext(), "Sorry!! Your request had been rejected", Toast.LENGTH_LONG).show();
                                        timer.cancel();
                                        doAsynchronousTask.cancel();
                                    } else if (conf.equals("1")) {
                                        AsyncCallWS3 task2 = new AsyncCallWS3();
                                        task2.execute();
                                        Toast.makeText(getApplicationContext(), "Your request had been accepted", Toast.LENGTH_LONG).show();
                                        timer.cancel();
                                        doAsynchronousTask.cancel();
                                    }
                                }
                            }
                        });
                    }
                };
                timer.schedule(doAsynchronousTask, 0, 5000);
            }
            else{
                Toast.makeText(getApplicationContext(), "Sorry!! Your request wasn't sent successfully", Toast.LENGTH_LONG).show();
            }


        }

        @Override
        protected void onPreExecute() {
           Log.d("request table", "in");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

    private class AsyncCallWS2 extends AsyncTask<String, Void, Void> {

        String status;
        @Override
        protected Void doInBackground(String... params) {
            status = WebServices.getRequestStatus(tableRequest, "getRequestStatus");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(status.equals("1"))
                conf = "1";
            else if(status.equals("2"))
                conf = "2";
            else
                conf = "0";
            Log.d("confrm", conf);

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

    private class AsyncCallWS3 extends AsyncTask<String, Void, Void> {

        String deleteTableRequest;
        @Override
        protected Void doInBackground(String... params) {
            deleteTableRequest= WebServices.deleteRequest(tableRequest, "deleteRequest");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

}
