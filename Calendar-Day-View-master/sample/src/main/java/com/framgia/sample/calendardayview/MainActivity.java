package com.framgia.sample.calendardayview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.framgia.library.calendardayview.CalendarDayView;
import com.framgia.library.calendardayview.EventView;
import com.framgia.library.calendardayview.PopupView;
import com.framgia.library.calendardayview.data.IEvent;
import com.framgia.library.calendardayview.data.IPopup;
import com.framgia.library.calendardayview.decoration.CdvDecorationDefault;
import java.util.ArrayList;
import java.util.Calendar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
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
                Intent intent = new Intent(MainActivity.this,Calender.class);
                startActivity(intent);
            }
        });
    }



}
