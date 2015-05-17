package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.IntentService;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by 4540 on 5/7/2015.
 */
public class MyTestService extends IntentService{
    public MyTestService() {
        super("MyTestService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        // Do the task here
        AsyncCallWS1 asnktask = new AsyncCallWS1();
        asnktask.execute();
    }

    private class AsyncCallWS1 extends AsyncTask<String, Void, Void> {

        String status;
        @Override
        protected Void doInBackground(String... params) {
            status = WebServices.getOrderStatus(MainActivity.user_info[0], "getOrderStatus");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if(status == null)
            Log.d("asnk calls", "null");
            
            if(status.equals("0"))
                Drinks.setOrder_status("0");
            else if(status.equals("2")){
                Toast.makeText(getApplicationContext(), "Sorry!! The order has been rejected", Toast.LENGTH_LONG).show();
                Drinks.setOrder_status("2");
                AsyncCallWS2 asnktask = new AsyncCallWS2();
                asnktask.execute();
            }
            else{
                Drinks.setOrder_status("1");
                Toast.makeText(getApplicationContext(), "The order has been confirmed", Toast.LENGTH_LONG).show();
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
            status = WebServices.deleteOrder(MainActivity.user_info[0]+"","deleteOrder");
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
