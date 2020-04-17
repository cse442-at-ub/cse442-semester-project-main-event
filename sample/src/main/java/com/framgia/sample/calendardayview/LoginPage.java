package com.framgia.sample.calendardayview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity {

    EditText UsernameEt, PasswordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        configureSeeAllEventButton();
        configureRegisterButton();

        UsernameEt = (EditText)findViewById(R.id.UserName);
        PasswordEt = (EditText)findViewById(R.id.Password);
    }

    private  void configureSeeAllEventButton(){
        Button goToMain = (Button) findViewById(R.id.GoToMain);
        goToMain.setOnClickListener(new Button.OnClickListener() {

                                        public void onClick(View v) {
                                            Intent intent = new Intent(getApplication(), MainActivity.class);
                                            startActivity(intent);
                                        }

                                    }
        );
    }

    private  void configureRegisterButton(){
        Button goToRegister = (Button) findViewById(R.id.Register);
        goToRegister.setOnClickListener(new Button.OnClickListener() {

                                            public void onClick(View v) {
                                                Intent intent = new Intent(getApplication(), RegistrationPage.class);
                                                startActivity(intent);
                                            }

                                        }
        );
    }

    public void OnLogin(View view) {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }

}
