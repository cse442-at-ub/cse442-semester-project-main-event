package com.framgia.sample.calendardayview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

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
