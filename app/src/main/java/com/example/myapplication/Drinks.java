package com.example.myapplication;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapplication.MyTestService;
import java.util.ArrayList;

import static android.widget.Toast.makeText;


public class Drinks extends ActionBarActivity {

    android.support.v7.app.ActionBar ab;
    Context context = this;
    ListView list;
    Spinner spnr;
    String []catName ;
    String []catID;
    String[] cats;
    ProgressBar pg ;
    ArrayList<String> items_name = new ArrayList<String>();
    ArrayList<String> items_price = new ArrayList<String>();
    ArrayList<String> items_disc = new ArrayList<String>();
    ArrayList<String> items_cat = new ArrayList<String>();
    static int cat;
    static ArrayList<String[]> order;
    String[] prices;
    String [] descriptions;
    ArrayList<String[]> temp_order;
    private static String order_status;
    static String order_id;
    static String bill_id;
    int sum;

    public static String getOrder_status() {
        return order_status;
    }

    public static void setOrder_status(String order_status) {
        Drinks.order_status = order_status;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);
        ab = getSupportActionBar();
        ab.hide();
        list = (ListView) findViewById(R.id.list);
        pg = (ProgressBar) findViewById(R.id.progressBar2);
        spnr = (Spinner) findViewById(R.id.spinner);
        temp_order = new ArrayList<String[]>();
        AsyncCallWS test = new AsyncCallWS();
        test.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelAlarm();
    }

    public void btn_onclick(View view) {
        order = temp_order;
        temp_order = null;
        AsyncCallWS1 asntask = new AsyncCallWS1();
        asntask.execute();
    }

    private class MyAdapter extends ArrayAdapter<String>{

        public MyAdapter(Context context, String[] objects) {
            super(context, R.layout.list_item,  objects);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater infltr = LayoutInflater.from(getContext());
            final View row = infltr.inflate(R.layout.list_item, parent, false);
            final String item = getItem(position);
            TextView textView = (TextView) row.findViewById(R.id.textView26);
            final TextView textView1 = (TextView) row.findViewById(R.id.textView27);
            TextView textView2 = (TextView) row.findViewById(R.id.textView28);
            textView.setText(item);
            textView1.setText(prices[position]);
            textView2.setText(descriptions[position]);
            final EditText editText = (EditText) row.findViewById(R.id.editText2);
            final CheckBox cb = (CheckBox) row.findViewById(R.id.checkBox);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cb.isChecked()) {
                        if (String.valueOf(editText.getText()).equals("")) {
                            editText.setText("1");
                            String[] arr = new String[3];
                            arr[0] = item;
                            arr[1] = prices[position] + "";
                            arr[2] = String.valueOf(editText.getText());
                            temp_order.add(arr);
                        }
                        else{
                            String[] arr = new String[3];
                            arr[0] = item;
                            arr[1] = prices[position] + "";
                            arr[2] = String.valueOf(editText.getText());
                            temp_order.add(arr);
                        }
                    }
                    else{
                        Log.d("list", "unchecked "+ " "+item);
                        int indx = 0;
                        if(temp_order.size() != 0){
                            for(int i=0;i<temp_order.size();i++){
                                if (temp_order.get(i)[0].equals(item)){
                                    indx = i;
                                    break;
                                }
                            }
                            temp_order.remove(indx);
                        }
                    }

                }
            });
            return row;
        }

    }

    public void back4_onclick(View v){
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drinks, menu);
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


    private class AsyncCallWS extends AsyncTask<String, Void, Void> {

        String category = null, items_str = null;

        @Override
        protected Void doInBackground(String... params) {
            category = WebServices.getItems("getCategories");
            items_str = WebServices.getItems("getItems");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            cats = category.split(",");
            catName = new String[cats.length];
            catID = new String[cats.length];
            String []split;
            for(int i=0;i<cats.length;i++) {
                split = cats[i].split("#");
                catName[i] = split[0];
                catID[i] = split[1];
            }
            spnr.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, catName));
            if(items_str != null || items_str != "") {
                String[] items_arr = items_str.split(",");
                for (int i = 0; i < items_arr.length; i++) {
                    String[] item = items_arr[i].split("#");
                    items_name.add(item[1]);
                    items_price.add(item[2]);
                    items_disc.add(item[3]);
                    items_cat.add(item[4]);
                }

                spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        cat = parent.getSelectedItemPosition();
                        set_list();
                        pg.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }
            else
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPreExecute() {
            pg.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

    private class AsyncCallWS1 extends AsyncTask<String, Void, Void> {

        String results;

        @Override
        protected Void doInBackground(String... params) {
            String str = MainActivity.user_info[0] + "/";

            for(int i=0;i<order.size();i++){
                Log.d("jgyygyuyu", order.get(i)[0]+" "+order.get(i)[1]+" "+order.get(i)[2]);
                sum += (Integer.parseInt(order.get(i)[1]) * Integer.parseInt(order.get(i)[2]));
                for(int j=0;j<order.get(i).length;j++){
                    if(j < order.get(i).length - 1)
                        str += order.get(i)[j] + "#";
                    else
                        str += order.get(i)[j];
                }
                if(i<order.size() - 1)
                    str += ",";
            }

            Log.d("54t54gg", sum+"");
            results = WebServices.addOrder(str,"addOrder");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d("bhjhbj", results);
            if(!results.equals("false")){
                Log.d("order id", results);
                order_id = results;
                AsyncCallWS2 task = new AsyncCallWS2();
                task.execute();
                Toast.makeText(context, "Your order had been sent please wait the confirmation", Toast.LENGTH_LONG).show();
                scheduleAlarm();
            }
            else {
                Toast.makeText(context, "Error occurred please try again", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

    private class AsyncCallWS2 extends AsyncTask<String, Void, Void> {

        String status;

        @Override
        protected Void doInBackground(String... params) {
            status = WebServices.addBill(order_id + "#" + sum, "addBill");
            bill_id = status;
            Log.d("bill id", status);
            return null;
        }

        protected void onPostExecute(Void result) {
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }


    void set_list(){
        for(int i=0;i<catName.length;i++){
            if(cat == i){
                String[] names;
                ArrayList<String> names_list = new ArrayList<String>();
                ArrayList<String> prices_list = new ArrayList<String>();
                ArrayList<String> disc_list = new ArrayList<String>();

                for (int j=0;j<items_name.size();j++){
                    if(items_cat.get(j).equals((i + 1)+"")){
                        names_list.add(items_name.get(j));
                        prices_list.add(items_price.get(j));
                        disc_list.add(items_disc.get(j));
                    }
                }

                names = new String[names_list.size()];
                prices = new String[names_list.size()];
                descriptions = new String[names_list.size()];

                for (int j=0;j<names.length;j++){
                    names[j] = names_list.get(j);
                    prices[j] = prices_list.get(j);
                    descriptions[j] = disc_list.get(j);
                }

                list.setAdapter(new MyAdapter(this, names));
            }
        }
    }

    public void scheduleAlarm() {
        // Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, MyAlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Setup periodic alarm every 5 seconds
        long firstMillis = System.currentTimeMillis(); // first run of alarm is immediate
        int intervalMillis = 10000; // 5 seconds
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, intervalMillis, pIntent);
    }

    public void cancelAlarm() {
        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, MyAlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pIntent);
    }

}
