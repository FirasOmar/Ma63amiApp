package com.example.myapplication;

import android.content.Context;
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


public class Cashier extends ActionBarActivity {

    ListView list;
    ProgressBar pb;
    ArrayList<String[]> bills;
    final Timer timer = new Timer();
    TimerTask doAsynchronousTask;
    String bill_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier);

        list = (ListView) findViewById(R.id.list);
        pb = (ProgressBar) findViewById(R.id.progressBar6);

        final Handler handler = new Handler();
        doAsynchronousTask = new TimerTask()
        {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try
                        {
                            new AsyncCallWS1().execute();


                        }
                        catch (Exception e)
                        {

                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 20000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        doAsynchronousTask.cancel();
    }

    private class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, String[] objects) {
            super(context, R.layout.cashier_item,  objects);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater infltr = LayoutInflater.from(getContext());
            View row = infltr.inflate(R.layout.cashier_item, parent, false);
            String item = getItem(position);
            TextView textView = (TextView) row.findViewById(R.id.tView36);
            TextView textView1 = (TextView) row.findViewById(R.id.tView37);
            TextView textView2 = (TextView) row.findViewById(R.id.textView45);
            ImageButton tick = (ImageButton) row.findViewById(R.id.iButton15);

            textView1.setText(position + 1 + "");
            textView.setText("#" + item);
            textView2.setText(bills.get(position)[1]);

            tick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bill_id = bills.get(position)[0];
                    AsyncCallWS2 task = new AsyncCallWS2();
                    task.execute();
                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
                }
            });

            return row;
        }
    }

    private class AsyncCallWS1 extends AsyncTask<String, Void, Void> {

        String billes;

        @Override
        protected Void doInBackground(String... params) {
            billes= WebServices.getNewBills("getNewBills");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(! billes.equals("empty")){
                Log.d("gbthb", billes);
                bills = new ArrayList<String[]>();
                String[] res = billes.split(",");
                String[] ids = new String[res.length];

                for(int i=0;i<res.length;i++){
                    bills.add(res[i].split("#"));
                    ids[i] = res[i].split("#")[0];
                }

                list.setAdapter(new MyAdapter(getApplicationContext(), ids));
                pb.setVisibility(View.INVISIBLE);
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

    private class AsyncCallWS2 extends AsyncTask<String, Void, Void> {

        String status;

        @Override
        protected Void doInBackground(String... params) {
            status = WebServices.setBillStatus(bill_id + "#1","setBillStatus");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d("billid", bill_id);
        }

        @Override
        protected void onPreExecute() {
        }


        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

}
