package com.framgia.sample.calendardayview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


/**
 * TODO: document your custom view class.
 */
public class Event_Registration_Form extends AppCompatActivity {

    EditText Et_Url, Et_Event_Name, Et_Location, Et_Start, Et_End, Et_RSVP, Et_Description;
    CheckBox Promote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__registration__form);
        setTitle("Event Registration Page");

        Et_Url = (EditText)findViewById(R.id.url);
        Et_Event_Name = (EditText)findViewById(R.id.event_Name);
        Et_Location = (EditText)findViewById(R.id.location);
        Et_Start = (EditText)findViewById(R.id.start_time);
        Et_End = (EditText)findViewById(R.id.end_time);
        Et_RSVP = (EditText)findViewById(R.id.rsvp);
        Promote = (CheckBox) findViewById(R.id.promote_Check);
        Et_Description = (EditText)findViewById(R.id.desription);

        configurePromotedButton();
        configureEventFinderButton();
        configureCalendarButton();
        configureEventRegistrationButton();
        configureProfileButton();
        configureSettingsButton();
        configureLogo();
    }

    private void configureLogo() {
        ImageButton Settings = findViewById((R.id.logo));
        Settings.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
            }
        });
    }

    private void configureSettingsButton() {
        ImageButton Settings = findViewById((R.id.settingsButton));
        Settings.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Settings.class);
                startActivity(intent);
            }
        });
    }

    private void configureProfileButton() {
        ImageButton Profile = findViewById((R.id.profileButton));
        Profile.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Profile.class);
                startActivity(intent);
            }
        });
    }

    private void configureEventRegistrationButton() {
        ImageButton Register_Event = findViewById((R.id.register_event_button));
        Register_Event.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Event_Registration_Form.class);
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
                                                  Intent intent = new Intent(getApplicationContext(),PromotedEventsPage.class);
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
                Intent intent = new Intent(getApplicationContext(),EventFinderPage.class);
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
                Intent intent = new Intent(getApplicationContext(),Calender.class);
                startActivity(intent);
            }
        });
    }

    public void OnRegisterEvent(View view){
        String str_Url = Et_Url.getText().toString();
        String str_Event_Name = Et_Event_Name.getText().toString();
        String str_Location = Et_Location.getText().toString();
        String str_Start = Et_Start.getText().toString();
        String str_End = Et_End.getText().toString();
        String str_RSVP = Et_RSVP.getText().toString();
        String str_Description = Et_Description.getText().toString();
        String type = "event_register";
        String str_Promote;
        if (Promote.isChecked())
            str_Promote = "1";
        else
            str_Promote = "0";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_Url, str_Event_Name, str_Location, str_Start, str_End, str_RSVP, str_Promote, str_Description);
    }


}

