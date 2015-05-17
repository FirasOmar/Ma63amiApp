package com.example.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class Registeration extends ActionBarActivity implements View.OnClickListener {
    TextView NameText;
    TextView EmailText;
    TextView PassText;
    TextView PhoneText;
    ProgressBar pg;
    TextView nameNote;
    TextView emailNote;
    TextView passNote;
    TextView phoneNote;
    TextView registerNote;
    String Name;
    String Email;
    String Pass;
    String Phone;
    String accountInfo;
    String result1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        TextView linkToLogin = (TextView) findViewById(R.id.link_to_login);
        linkToLogin.setOnClickListener(this);

        NameText = (TextView) findViewById(R.id.reg_fullname);
        EmailText = (TextView) findViewById(R.id.reg_email);
        PassText = (TextView) findViewById(R.id.reg_password);
        PhoneText = (TextView) findViewById(R.id.reg_Phone);
        pg = (ProgressBar) findViewById(R.id.progressBarLogin);
        nameNote = (TextView) findViewById(R.id.nameNote);
        emailNote = (TextView) findViewById(R.id.emailNote);
        passNote = (TextView) findViewById(R.id.passNote);
        phoneNote = (TextView) findViewById(R.id.phoneNote);
        registerNote = (TextView) findViewById(R.id.registerNote);
        Button register = (Button) findViewById(R.id.btnRegister);
        register.setOnClickListener(this);
    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            //item = WebService1.getItems("getItems");
            result1 = WebServicesLogin.createAccount(accountInfo,"createUser");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            //Make ProgressBar invisible
            pg.setVisibility(View.INVISIBLE);
            registerNote.setText(result1);
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
        if(v.getId() == R.id.link_to_login) {
            //Toast.makeText(this,"btrRegister Clicked",Toast.LENGTH_SHORT);
            finish();
        }

        if(v.getId() == R.id.btnRegister) {
            Name = NameText.getText().toString();
            Email = EmailText.getText().toString();
            Pass = PassText.getText().toString();
            Phone = PhoneText.getText().toString();
            int flag = 1;
            if(Name == "" || Name.equals("") || Name.isEmpty()) {
                nameNote.setText("Please, Enter your name");
                flag = 0;
            }
            else {
                nameNote.setText("");
            }
            if(Email == "" || Email.isEmpty() || Email.equals("")) {
                emailNote.setText("Please, Enter your email");
                flag = 0;
            }
            else {
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    emailNote.setText("Please, Enter a valid email address");
                    flag = 0;
                }

                else
                    emailNote.setText("");
            }
            if(Pass == "" || Pass.isEmpty() || Pass.equals("")) {
                passNote.setText("Please, Enter a password");
                flag = 0;
            }
            else {
                passNote.setText("");
            }
            if(Phone == "" || Phone.isEmpty() || Phone.equals("")) {
                phoneNote.setText("Please, Enter your phone number");
                flag = 0;
            }
            else {
                phoneNote.setText("");
            }
            if(flag == 1) {
                accountInfo = Name+"#"+Pass+"#"+Phone+"#"+Email;
                AsyncCallWS task = new AsyncCallWS();
                task.execute();
            }
        }
    }
}
