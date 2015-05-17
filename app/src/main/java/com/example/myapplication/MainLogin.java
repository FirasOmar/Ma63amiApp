package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainLogin extends ActionBarActivity implements View.OnClickListener {

    Context context;
    TextView userEmailT;
    TextView userPassT;
    TextView userEmailNote;
    TextView userPassNote;
    TextView loginNote;
    String userEmail;
    String userPass;
    String userLine;
    String result1;
    ProgressBar pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        context = this;

        TextView linkToRegistration = (TextView) findViewById(R.id.link_to_register);
        linkToRegistration.setOnClickListener(this);

        userEmailT = (TextView) findViewById(R.id.userEmail);
        userPassT = (TextView) findViewById(R.id.userPass);
        Button login = (Button) findViewById(R.id.btnLogin);
        login.setOnClickListener(this);

        userEmailNote = (TextView) findViewById(R.id.userEmailNote);
        userPassNote = (TextView) findViewById(R.id.userPassNote);
        loginNote = (TextView) findViewById(R.id.loginNote);
        pg = (ProgressBar) findViewById(R.id.progressBarlog);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_login, menu);
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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.link_to_register) {
            startActivity(new Intent(MainLogin.this,Registeration.class));
        }
        else if(v.getId() == R.id.btnLogin) {
            userEmail = userEmailT.getText().toString();
            userPass = userPassT.getText().toString();
            int flag = 1;
            if(userEmail == "" || userEmail.equals("")) {
                userEmailNote.setText("Please enter your email");
                flag = 0;
            }
            else userEmailNote.setText("");
            if(userPass == "" || userPass.equals("")) {
                userPassNote.setText("Please enter your password");
                flag = 0;
            }
            else userPassNote.setText("");
            if(flag == 1) {
                userLine = userEmail+"#"+userPass;
                AsyncCallWS task = new AsyncCallWS();
                task.execute();
            }
        }
    }


    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            //item = WebService1.getItems("getItems");
            result1 = WebServicesLogin.createAccount(userLine,"Login");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            //Make ProgressBar invisible
            pg.setVisibility(View.INVISIBLE);
            Log.d("nlvdfjnvkj", result1.length()+"");
            if(result1.equals("false"))
                loginNote.setText("Incorrect e-mail or password");
            else{
                Log.d("kfnvldfnv", result1);
                String[] arr = result1.split("#");
                Log.d("user type", arr[3]);
                Log.d("user type", arr[3].length()+"");
                if(arr[3].equals("1")){
                    Intent intnt = new Intent(context, MainActivity.class);
                    intnt.putExtra("key", result1);
                    startActivity(intnt);
                }
                else if(arr[3].equals("2")){
                    startActivity(new Intent(context, HeadChef.class));
                }
                else if(arr[3].equals("3")){
                    startActivity(new Intent(context, Waiter.class));
                }
                else if(arr[3].equals("4")){
                    startActivity(new Intent(context, Cashier.class));
                }
                else{
                    startActivity(new Intent(context, Main.class));
                }
            }
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
