package com.framgia.sample.calendardayview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginPage extends AppCompatActivity {

    EditText UsernameEt, PasswordEt;

    public void OnLogin(View view) {

        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();

        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);

        PasswordEt.getText().clear();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        configureRegisterButton();
        getEventsFromDB();

        UsernameEt = (EditText) findViewById(R.id.UserName);
        PasswordEt = (EditText) findViewById(R.id.Password);
    }


    private void configureRegisterButton() {
        TextView goToRegister = (TextView) findViewById(R.id.LogIn);
        goToRegister.setOnClickListener(new TextView.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(getApplication(), RegistrationPage.class);
                                                startActivity(intent);
                                            }

                                        }
        );
    }

    private void getEventsFromDB() {
        String type = "retrieve_events";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type);
    }



}