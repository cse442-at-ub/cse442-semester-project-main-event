package com.framgia.sample.calendardayview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class RegistrationPage extends AppCompatActivity {
    private static final String FILE_NAME = "account_credentials.txt";
    EditText username,password,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        setTitle("Register Account");

        username = (EditText) findViewById(R.id.username_text);
        password = (EditText) findViewById(R.id.password_text);
        email = (EditText) findViewById(R.id.email_text);

        configureLoginButton();


    }



    private  void configureLoginButton(){
        TextView goToMain = (TextView) findViewById(R.id.LogIn);
        goToMain.setOnClickListener(new TextView.OnClickListener() {

                                        public void onClick(View v) {
                                            Intent intent = new Intent(getApplication(), LoginPage.class);
                                            startActivity(intent);
                                        }

                                    }
        );
    }



    public void OnReg(View view) {
        String str_username = username.getText().toString();
        String str_password = password.getText().toString();
        String str_e_mail = email.getText().toString();
        if(str_e_mail.length() < 7  || str_username.length() < 7 || str_password.length() < 7){
            AlertDialog alert = new AlertDialog.Builder(this).create();
            alert.setTitle("Invalid Input");
            alert.setMessage("All inputs must be 7 or more characters long");
            alert.show();
        } else {
            String type = "account_register";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, str_username, str_password, str_e_mail);
            username.getText().clear();
            password.getText().clear();
            email.getText().clear();
        }
    }


//    private void configureRegisterButton(){
//        Button registerButton = (Button) findViewById(R.id.register_button);
//        registerButton.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveDetails(FILE_NAME);
//            }
//        });
//    }
}
