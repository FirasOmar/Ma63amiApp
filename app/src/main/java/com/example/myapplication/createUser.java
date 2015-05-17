package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class createUser extends ActionBarActivity implements View.OnClickListener {
    String userType ="";
    Context context = this;
    String Name;
    String Email;
    String Pass;
    String Phone;
    String accountInfo;
    String result1;
    EditText NameText;
    EditText EmailText;
    EditText PassText;
    EditText PhoneText;
    ProgressBar pg;
    TextView nameNote;
    TextView emailNote;
    TextView passNote;
    TextView phoneNote;
    TextView registerNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        Intent intent = getIntent();
        userType = intent.getStringExtra("userType");
        if(userType.equals("2")) {
            Toast.makeText(context,"New Head Chef Account",Toast.LENGTH_LONG).show();
        }
        else if(userType.equals("3")) {
            Toast.makeText(context, "New Head Waiter Account", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context,"New Cachier Account",Toast.LENGTH_LONG).show();
        }
        NameText = (EditText) findViewById(R.id.AdmineditText4);
        EmailText = (EditText) findViewById(R.id.AdmineditText5);
        PassText = (EditText) findViewById(R.id.AdmineditText6);
        PhoneText = (EditText) findViewById(R.id.AdmineditText7);
        Button register = (Button) findViewById(R.id.Adminbutton7);
        register.setOnClickListener(this);
        nameNote = (TextView) findViewById(R.id.AdmintextView69);
        emailNote = (TextView) findViewById(R.id.AdmintextView70);
        passNote = (TextView) findViewById(R.id.AdmintextView71);
        phoneNote = (TextView) findViewById(R.id.AdmintextView72);
        registerNote = (TextView) findViewById(R.id.AdmintextView73);
        pg = (ProgressBar) findViewById(R.id.AdminprogressBar6);
        ImageView back = (ImageView) findViewById(R.id.AdminimageView10);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.Adminbutton7) {
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
                accountInfo = Name+"#"+Pass+"#"+Phone+"#"+Email+"#"+userType;
               AsyncCallWS task = new AsyncCallWS();
                task.execute();
            }

        }
        else if(view.getId() == R.id.AdminimageView10) {
            finish();
        }
    }


    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            //item = WebService1.getItems("getItems");
            result1 = WebService1.createAccount(accountInfo,"createUserT");
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
}
