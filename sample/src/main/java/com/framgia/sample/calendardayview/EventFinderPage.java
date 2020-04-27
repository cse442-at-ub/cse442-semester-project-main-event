package com.framgia.sample.calendardayview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class EventFinderPage extends AppCompatActivity {



    ListView eventList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_finder_page);
        setTitle("Event Finder");
        configurePromotedButton();
        configureEventFinderButton();
        configureCalendarButton();
        configureEventRegistrationButton();
        configureProfileButton();
        configureSettingsButton();
        configureLogo();




        //bind eventList to the ListView on the activity_event_finder_page
        eventList = (ListView) findViewById(R.id.event_list);

        //gets the items to put in the ListView from strings.xml
        ArrayList<String> eventArray = new ArrayList<>();
        eventArray.addAll(Arrays.asList(getResources().getStringArray(R.array.my_events)));

        //create an adapter that will change the ListView based on what we searched
        adapter = new ArrayAdapter<String>(
                EventFinderPage.this,
                android.R.layout.simple_list_item_1,
                eventArray
        );

        //binds ListView to adapter
        eventList.setAdapter(adapter);

        configureEventListButton();

    }



    private void configureEventListButton() {
        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),EventDescription.class);
                startActivity(intent);
            }
        });
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

    private void configurePromotedButton() {
        //Create a reference to the promoted button
        ImageButton promotedButton = findViewById((R.id.PromotedButton));
        //set event listener for the button
        promotedButton.setOnClickListener(new Button.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  //this opens the promoted events page when the button is clicked.
                                                  Intent intent = new Intent(getApplicationContext(), PromotedEventsPage.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu to hold the search button
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView)item.getActionView();

        //set the TextListener to the search button that will filter results everytime the text changes
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }
}
