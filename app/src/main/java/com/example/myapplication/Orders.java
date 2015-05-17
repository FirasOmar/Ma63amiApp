package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;


public class Orders extends ActionBarActivity implements View.OnClickListener {
    ImageView orderImage;
    TextView orderText;
    String orders;
    ProgressBar pg;
    String []items;
    String []splitItems;
    ArrayList<String> ordersID = new ArrayList<String>();
    ArrayList<String> ordersList = new ArrayList<String>();
    ListView ItemsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        ImageView back = (ImageView) findViewById(R.id.AdminEditinfoback);
        back.setOnClickListener(this);
        orderImage = (ImageView) findViewById(R.id.AdminimageView9);
        orderText = (TextView) findViewById(R.id.AdmintextView14);
        pg = (ProgressBar) findViewById(R.id.AdminprogressBar7);
        ItemsView = (ListView) findViewById(R.id.AdminlistView);
        AsyncCallWS task = new AsyncCallWS();
        task.execute();
    }

    private class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int resource, int textViewResourceId, String[] objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.orderslistview, null);
                holder = new ViewHolder();
                holder.orderText = (TextView) convertView.findViewById(R.id.AdmintextView14);
                holder.imageIcon = (ImageView) convertView.findViewById(R.id.AdminimageView9);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.orderText.setText("Order #"+ordersID.get(position));
            //holder.imageIcon.setImageResource(R.drawable.ifeedback);

            return convertView;
        }
        public class ViewHolder {
            TextView orderText;
            ImageView imageIcon;
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.AdminEditinfoback) {
            finish();
        }
    }




    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            orders = WebService1.getItems("findOrders");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            //Make ProgressBar invisible
            pg.setVisibility(View.INVISIBLE);
            Log.d("djaldskjlkdjsalk", orders);
            items = orders.split("/");
            Log.d("djaldskjlkdjsalk",items.length+"");
            splitItems = items[0].split("@");
            Log.d("djaldskjlkdjsalk",splitItems[0]);
            for (int i = 0; i < items.length; i++) {
                splitItems = items[i].split("@");
                Log.d("djaldskjlkdjsalk",splitItems[0]);
                ordersID.add(splitItems[0]);
                ordersList.add(splitItems[1]);
            }
            ItemsView.setAdapter(new MyAdapter(getApplicationContext(),
                    android.R.layout.simple_list_item_1, R.id.itemName,
                    items));
            ItemsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    showMessage(i);
                }
            });
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
    public void showMessage(int pos) {
        String []items = new String[2];
        items[0] = ordersID.get(pos);
        items[1] = ordersList.get(pos);
        Intent edit = new Intent(this,viewOrder.class);
        edit.putExtra("itemInfo",items);
        startActivity(edit);
    }
}
