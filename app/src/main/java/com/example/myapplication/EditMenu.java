package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class EditMenu extends ActionBarActivity implements View.OnClickListener {
    ProgressBar pg;
    ListView ItemsView;
    String orders = "";
    static String[] items;
    static String[] splitItems;
    ArrayList<String> itemsName = new ArrayList<String>();
    ArrayList<String> itemsDescription = new ArrayList<String>();
    ArrayList<String> itemsPrices = new ArrayList<String>();
    ArrayList<String> itemsCategory = new ArrayList<String>();
    ArrayList<String> itemsIDs = new ArrayList<String>();
    String item;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);
        ItemsView = (ListView) findViewById(R.id.AdminItemsList);
        pg = (ProgressBar) findViewById(R.id.AdminprogressBar);
        ImageView back = (ImageView) findViewById(R.id.AdminEditinfoback);
        ImageView addItem = (ImageView) findViewById(R.id.AdminmenueditItem);
        back.setOnClickListener(this);
        addItem.setOnClickListener(this);
        Toast.makeText(getApplicationContext(),"Hello", Toast.LENGTH_SHORT);

    }
    // MyAdapter class
    private class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int resource, int textViewResourceId, String[] objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.itemslistview, null);
                holder = new ViewHolder();
                holder.itemName = (TextView) convertView.findViewById(R.id.itemName);
                holder.imageIcon = (ImageView) convertView.findViewById(R.id.itemImage);
                holder.itemPrice = (TextView) convertView.findViewById(R.id.itemPrice);
                holder.itemDescription = (TextView) convertView.findViewById(R.id.AdminEdititemDescription);
                //holder.itemCategory = (TextView) convertView.findViewById(R.id.itemPrice);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.itemName.setText(itemsName.get(position));
            holder.imageIcon.setImageResource(R.drawable.ifeedback);
            holder.itemPrice.setText(itemsPrices.get(position)+"$");
            holder.itemDescription.setText(itemsDescription.get(position));

            return convertView;
        }
        public class ViewHolder {
            TextView itemName;
            ImageView imageIcon;
            TextView itemPrice;
            TextView itemDescription;
            TextView itemCategory;
        }
    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            item = WebService1.getItems("getItems");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            //Make ProgressBar invisible
            pg.setVisibility(View.INVISIBLE);
            items = item.split(",");
            for(int i=0;i<items.length;i++) {
                splitItems = items[i].split("#");
                itemsIDs.add(splitItems[0]);
                itemsName.add(splitItems[1]);
                itemsPrices.add(splitItems[2]);
                itemsDescription.add(splitItems[3]);
                itemsCategory.add(splitItems[4]);
            }
            ItemsView.setAdapter(new MyAdapter(getApplicationContext(),
                    android.R.layout.simple_list_item_1,R.id.itemName,
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
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.AdminEditinfoback) {
            finish();
        }
        else if(v.getId() == R.id.AdminmenueditItem) {
            startActivity(new Intent(EditMenu.this,additems.class));
        }
    }


    public void showMessage(int pos) {
        //Toast.makeText(getApplicationContext(),"Name:"+itemsName.get(pos)+"\nprice:"+itemsPrices.get(pos)+"\n description:"+itemsDescription.get(pos)+"\n Category:"+itemsCategory.get(pos),Toast.LENGTH_SHORT).show();
        Intent edit = new Intent(this,editItem.class);
        String []item = {itemsName.get(pos),itemsPrices.get(pos),itemsDescription.get(pos),itemsCategory.get(pos), itemsIDs.get(pos)};
        edit.putExtra("itemInfo",item);
        startActivity(edit);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // calling the web service
        AsyncCallWS task = new AsyncCallWS();
        task.execute();
    }
}
