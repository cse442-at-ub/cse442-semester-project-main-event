package com.framgia.sample.calendardayview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        configureSeeAllEvent();
        ConfigRegistration();

    }

    private  void configureSeeAllEvent(){
        Button gotMain = (Button) findViewById(R.id.GoToMain);

        gotMain.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {
                        Intent intent = new Intent(getApplication(), MainActivity.class);
                        startActivity(intent);

                    }

                }
        );
    }

    private  void ConfigRegistration(){
        Button gotMain = (Button) findViewById(R.id.Register);

        gotMain.setOnClickListener(
                new Button.OnClickListener() {

                    public void onClick(View v) {
                        Intent intent = new Intent(getApplication(), RegistrationPage.class);
                        startActivity(intent);

                    }

                }
        );
    }
}
