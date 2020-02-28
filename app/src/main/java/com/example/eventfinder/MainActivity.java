package com.example.eventfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Page");


        configurePromotedButton();
        configureEventFinderButton();
        configureCalendarButton();

    }

    private void configurePromotedButton(){
        //Create a reference to the promoted button
        Button promotedButton = (Button) findViewById((R.id.PromotedButton));
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
        Button eventFinderButton = (Button) findViewById((R.id.EventFinderButton));
        eventFinderButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EventFinderPage.class);
                startActivity(intent);
            }
        });
    }

    private void configureCalendarButton(){
        Button calendarButton = (Button) findViewById((R.id.CalendarButton));
        calendarButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: complete this function after the calendar page is created.
                //Intent intent = new Intent(MainActivity.this,CalendarPage.class);
                //startActivity(intent);
            }
        });
    }


}
