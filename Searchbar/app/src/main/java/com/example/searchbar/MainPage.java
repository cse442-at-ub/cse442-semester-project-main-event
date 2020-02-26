package com.example.searchbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        setTitle("Main Page");

        configureEventFinderButton();

        configureCalendarButton();
    }

    private void configureEventFinderButton(){
        Button eventFinderButton = (Button) findViewById(R.id.event_finder_button);
        eventFinderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this, SearchActivity.class));
            }
        });
    }

    private void configureCalendarButton(){
        Button calendarButton = (Button) findViewById(R.id.calendar_button);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: start the calendar activity once we have the calendar class
            }
        });
    }

}
