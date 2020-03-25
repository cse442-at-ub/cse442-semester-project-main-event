package com.framgia.sample.calendardayview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        setTitle("Event Finder");

        configureSeeAllEvents();
    }

    //click the see all events botton will open the main page
    private void configureSeeAllEvents() {
        //Create a reference to the button
        Button goToMainButton = (Button) findViewById((R.id.GoToMain));

        goToMainButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent;
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }
                }
        );
    }
}

