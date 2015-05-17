package com.example.myapplication;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HeadChef extends ActionBarActivity {

    ListView list;
    ProgressBar pb;
    CustOrder[] custOrders;
    String custid;
    final Handler handler = new Handler();
    Timer timer = new Timer();
    TimerTask doAsynchronousTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_chef);

        list = (ListView) findViewById(R.id.list);
        pb = (ProgressBar) findViewById(R.id.progressBar);
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
        timer.schedule(doAsynchronousTask, 0, 20000); //execute in every 5

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        doAsynchronousTask.cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_head_chef, menu);
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

    private class AsyncCallWS1 extends AsyncTask<String, Void, Void> {

        String status;

        @Override
        protected Void doInBackground(String... params) {
            status = WebServices.getNewOrders("getNewOrders");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(status.equals("empty")){
                Toast.makeText(getApplicationContext(), "There is no orders", Toast.LENGTH_SHORT).show();
            }
            else{
                String[] orders = status.split("/");
                String indices[] = new String[orders.length];
                for(int i=0;i<orders.length;i++)
                    custOrders = new CustOrder[orders.length];
                for(int i=0;i<orders.length;i++){
                    String custid = orders[i].substring(0, orders[i].indexOf('.'));
                    Log.d("id", custid);
                    String order_str = orders[i].substring(orders[i].indexOf('.') + 1);
                    Log.d("order", order_str);
                    String[] order_arr = order_str.split(",");
                    String[][] items = new String[order_arr.length][];
                    for(int j=0;j<order_arr.length;j++){
                        String[] item = order_arr[j].split("#");
                        items[j] = item;
                    }
                    custOrders[i] = new CustOrder(custid, items);
                }
                list.setAdapter(new MyAdapter(getApplicationContext(), indices));
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

    private class MyAdapter extends ArrayAdapter<String>{

        public MyAdapter(Context context, String[] objects) {
            super(context, R.layout.head_chef_item,  objects);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater infltr = LayoutInflater.from(getContext());
            View row = infltr.inflate(R.layout.head_chef_item, parent, false);
            String item = getItem(position);
            TextView textView = (TextView) row.findViewById(R.id.textView36);
            TextView textView1 = (TextView) row.findViewById(R.id.textView37);
            ImageButton tick = (ImageButton) row.findViewById(R.id.imageButton15);
            ImageButton cross = (ImageButton) row.findViewById(R.id.imageButton16);

            textView.setText("Show Details");
            textView1.setText(position + 1 + "");
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ConfirmOrder.class);
                    intent.putExtra("key", set_order(custOrders[position].getOrder()));
                    startActivity(intent);
                }
            });

            tick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    custid = custOrders[position].getId();
                    AsyncCallWS2 task = new AsyncCallWS2();
                    task.execute();
                }
            });

            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    custid = custOrders[position].getId();
                    AsyncCallWS3 task = new AsyncCallWS3();
                    task.execute();
                }
            });

            return row;
        }

    }

    private class AsyncCallWS2 extends AsyncTask<String, Void, Void> {

        String results;

        @Override
        protected Void doInBackground(String... params) {
            results = WebServices.setStatus(custid + "#" + "1","setStatus");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(results.equals("true"))
                Toast.makeText(getApplicationContext(), "The order has been confirmed", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Sorry!! Error occurred please try again", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

    }

    private class AsyncCallWS3 extends AsyncTask<String, Void, Void> {

        String results;

        @Override
        protected Void doInBackground(String... params) {
            results = WebServices.setStatus(custid + "#" + "2","setStatus");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(results.equals("true"))
                Toast.makeText(getApplicationContext(), "The order has been deleted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Sorry!! Error occurred please try again", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

    }

    private String[] set_order(String[][] order) {
        String[] result = new String[order.length];
        for(int i=0;i<result.length;i++){
            result[i] = order[i][0] + "\t" + order[i][2];
        }
        return result;
    }

}