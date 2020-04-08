package com.framgia.sample.calendardayview;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.*;


/**
 * Mohammed Samsuddin
 * CSE442
 * Version: 01
 */


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Page");


        configurePromotedButton();
        configureEventFinderButton();
        configureCalendarButton();
        configureEventRegistrationButton();
        configureRegisterPageButton();
        configureProfileButton();
        configureSettingsButton();

    }

    private void configureSettingsButton() {
        ImageButton Settings = findViewById((R.id.settingsButton));
        Settings.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Settings.class);
                startActivity(intent);
            }
        });
    }

    private void configureProfileButton() {
        ImageButton Profile = findViewById((R.id.profileButton));
        Profile.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Profile.class);
                startActivity(intent);
            }
        });
    }

    private void configureEventRegistrationButton() {
        ImageButton Register_Event = findViewById((R.id.register_event_button));
        Register_Event.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Event_Registration_Form.class);
                startActivity(intent);
            }
        });
    }

    private void configurePromotedButton(){
        //Create a reference to the promoted button
        ImageButton promotedButton =  findViewById((R.id.PromotedButton));
        //set event listener for the button
        promotedButton.setOnClickListener(new Button.OnClickListener(){
                                              @Override
                                              public void onClick(View v){
                                                  //this opens the promoted events page when the button is clicked.
                                                  Intent intent = new Intent(MainActivity.this,PromotedEventsPage.class);
                                                  startActivity(intent);
                                              }
                                          }
        );
    }

    private void configureEventFinderButton(){
        ImageButton eventFinderButton =  findViewById((R.id.EventFinderButton));
        eventFinderButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EventFinderPage.class);
                startActivity(intent);
            }
        });
    }

    private void configureCalendarButton(){
        ImageButton calendarButton =  findViewById((R.id.CalendarButton));
        calendarButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: complete this function after the calendar page is created.
                Intent intent = new Intent(MainActivity.this,Calender.class);
                startActivity(intent);
            }
        });
    }

    private void configureRegisterPageButton(){
        Button registerPageButton =  findViewById(R.id.RegisterPageButton);
        registerPageButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegistrationPage.class);
                startActivity(intent);
            }
        });
    }



}
