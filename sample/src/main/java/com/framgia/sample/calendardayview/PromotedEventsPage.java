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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class PromotedEventsPage extends AppCompatActivity {


    ListView eventList;
    ArrayAdapter<String> adapter;
    String allEventsUnseparated;
    ArrayList<ArrayList<String>> dataSeparatedEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promoted_events_page);
        setTitle("Promoted Page");
        configurePromotedButton();
        configureEventFinderButton();
        configureCalendarButton();
        configureEventRegistrationButton();
        configureProfileButton();
        configureSettingsButton();
        configureLogo();

        getEvents();
        splitEventData();
        configureEventList();
        configureEventListItemsButton();
    }

    private void configureEventList() {
        eventList = (ListView) findViewById(R.id.promoted_event_list);
        ArrayList<String> eventArray = new ArrayList<>();
        for (ArrayList<String> AL: dataSeparatedEvents) {
            eventArray.add(AL.get(0));
        }

        //create an adapter that will change the ListView based on what we searched
        adapter = new ArrayAdapter<String>(
                PromotedEventsPage.this,
                android.R.layout.simple_list_item_1,
                eventArray
        );

        //binds ListView to adapter
        eventList.setAdapter(adapter);


    }


    private void splitEventData() {
        String[] eachEvent = allEventsUnseparated.split("\\|");
        eachEvent = Arrays.copyOf(eachEvent, eachEvent.length-1);
        System.out.println(Arrays.toString(eachEvent));
        ArrayList<ArrayList<String>> eventsWithTheirDataSeparated = new ArrayList<>();
        for (String x: eachEvent) {
            String[] dataSeparatedA = x.split(",");
            ArrayList<String> dataSeparatedAL = new ArrayList<>(Arrays.asList(dataSeparatedA));
            eventsWithTheirDataSeparated.add(dataSeparatedAL);
        }
        dataSeparatedEvents = eventsWithTheirDataSeparated;
    }


    private void getEvents() {
        FileInputStream fis = null;
        try {
            fis = openFileInput("PromotedEvents.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while((text = br.readLine()) != null){
                sb.append(text).append("\n");
            }
            System.out.println(sb.toString());
            allEventsUnseparated = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void configureEventListItemsButton() {
        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Iterator<ArrayList<String>> iter = dataSeparatedEvents.iterator();
                ArrayList<String> found = new ArrayList<>();
                while(iter.hasNext()){
                    found = iter.next();
                    if(found.get(0).equals(adapter.getItem(position))){
                        break;
                    }
                }

                Intent intent = new Intent(getApplicationContext(), EventDescription.class);
                intent.putExtra("event_name", found.get(0));
                intent.putExtra("location", found.get(1));
                intent.putExtra("start_time", found.get(2));
                intent.putExtra("end_time", found.get(3));
                intent.putExtra("rsvp", found.get(4));
                intent.putExtra("description", found.get(5));
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
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
            }
        });
    }

    private void configureProfileButton() {
        ImageButton Profile = findViewById((R.id.profileButton));
        Profile.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
            }
        });
    }

    private void configureEventRegistrationButton() {
        ImageButton Register_Event = findViewById((R.id.register_event_button));
        Register_Event.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Event_Registration_Form.class);
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

    private void configureEventFinderButton() {
        ImageButton eventFinderButton = findViewById((R.id.EventFinderButton));
        eventFinderButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventFinderPage.class);
                startActivity(intent);
            }
        });
    }

    private void configureCalendarButton() {
        ImageButton calendarButton = findViewById((R.id.CalendarButton));
        calendarButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: complete this function after the calendar page is created.
                Intent intent = new Intent(getApplicationContext(), Calender.class);
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
