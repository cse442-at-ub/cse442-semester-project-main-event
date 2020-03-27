package com.framgia.sample.calendardayview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegistrationPage extends AppCompatActivity {
    private static final String FILE_NAME = "account_credentials.txt";
    EditText user,pass,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        setTitle("Register Account");

        user = (EditText) findViewById(R.id.username_text);
        pass = (EditText) findViewById(R.id.password_text);
        email = (EditText) findViewById(R.id.email_text);

        configureRegisterButton();
    }

    public void saveDetails(String file){
        String username = user.getText().toString();
        String password = pass.getText().toString();
        String email_address = email.getText().toString();
        String text_to_write = username + "\n" + password + "\n" + email_address;

        FileOutputStream fos = null;

        try {
            fos = openFileOutput(file, MODE_PRIVATE);
            fos.write(text_to_write.getBytes());

            user.getText().clear();
            pass.getText().clear();
            email.getText().clear();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void configureRegisterButton(){
        Button registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDetails(FILE_NAME);
            }
        });
    }
}
