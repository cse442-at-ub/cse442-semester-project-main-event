package com.framgia.sample.calendardayview;

import androidx.appcompat.app.AppCompatActivity;

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
        configureSeeAllEventButton();

    }

    private  void configureSeeAllEventButton(){
        TextView goToMain = (TextView) findViewById(R.id.GoToMain);
        goToMain.setOnClickListener(new TextView.OnClickListener() {

                                        public void onClick(View v) {
                                            Intent intent = new Intent(getApplication(), MainActivity.class);
                                            startActivity(intent);
                                        }

                                    }
        );
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
        String type = "account_register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_username, str_password, str_e_mail);
    }

//    public void saveDetails(String file){
//        String username = user.getText().toString();
//        String password = pass.getText().toString();
//        String email_address = email.getText().toString();
//        String text_to_write = username + "\n" + password + "\n" + email_address;
//
//        FileOutputStream fos = null;
//        BufferedWriter bw = null;
//
//        try {
//            fos = openFileOutput(file, MODE_PRIVATE | MODE_APPEND);
//            bw = new BufferedWriter(new OutputStreamWriter(fos));
//
//            bw.write(text_to_write);
//            bw.newLine();
//
//
//            user.getText().clear();
//            pass.getText().clear();
//            email.getText().clear();
//            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if(fos != null){
//                try {
//                    bw.close();
//                    fos.close();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

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
