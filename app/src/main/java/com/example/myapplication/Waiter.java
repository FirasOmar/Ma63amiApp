package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Waiter extends ActionBarActivity {

    ListView list;
    String[][] requested_tables;
    ProgressBar pb;
    String req_table;
    final Handler handler = new Handler();
    Timer timer = new Timer();
    TimerTask doAsynchronousTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);

        list = (ListView) findViewById(R.id.list);
        pb = (ProgressBar) findViewById(R.id.progressBar4);

        doAsynchronousTask = new TimerTask()
        {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try
                        {
                            new AsyncCallWS().execute();

                        }
                        catch (Exception e)
                        {

                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 20000); //execute in every 5
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        doAsynchronousTask.cancel();
    }

    private class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, String[] objects) {
            super(context, R.layout.waiter_item,  objects);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater infltr = LayoutInflater.from(getContext());
            View row = infltr.inflate(R.layout.waiter_item, parent, false);
            String item = getItem(position);
            TextView textView = (TextView) row.findViewById(R.id.tv1);
            TextView textView1 = (TextView) row.findViewById(R.id.tv2);
            ImageButton tick = (ImageButton) row.findViewById(R.id.ib1);
            ImageButton cross = (ImageButton) row.findViewById(R.id.ib2);

            textView.setText(position + 1 + "");
            textView1.setText(item);
            tick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    req_table = requested_tables[position][0] + "#" + requested_tables[position][1] + "#1";
                    Log.d("kjkbnkj", req_table);
                    AsyncCallWS1 task = new AsyncCallWS1();
                    task.execute();
                }
            });

            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    req_table = requested_tables[position][0] + "#" + requested_tables[position][1] + "#2";
                    Log.d("kjkbnkj", req_table);
                    AsyncCallWS1 task = new AsyncCallWS1();
                    task.execute();
                }
            });

            return row;
        }

    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        String requests;

        @Override
        protected Void doInBackground(String... params) {
            requests = WebServices.getNewRequests("getNewRequests");
          return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(!requests.equals("empty")){
                Log.d("requests", requests);
                String[] tables = requests.split(",");
                String [] table_id = new String[tables.length];
                requested_tables = new String[tables.length][];
                for(int i=0;i<tables.length;i++){
                    requested_tables[i] = tables[i].split("#");
                    Log.d(i+"", requested_tables[i].toString());
                    table_id[i] = requested_tables[i][1];
                }
                list.setAdapter(new MyAdapter(getApplicationContext(), table_id));
                pb.setVisibility(View.INVISIBLE);
            }
            else{
                Toast.makeText(getApplicationContext(), "There is no requests", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
        }


        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

    private class AsyncCallWS1 extends AsyncTask<String, Void, Void> {
        String status;
        @Override
        protected Void doInBackground(String... params) {
            status = WebServices.setRequestStateus(req_table,"setRequestStateus");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPreExecute() {
        }


        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

    public void button_oc(View view) {
        startActivity(new Intent(this, Tables.class));
    }
}
