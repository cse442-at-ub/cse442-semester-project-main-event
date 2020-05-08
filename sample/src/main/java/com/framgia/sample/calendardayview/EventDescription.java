package com.framgia.sample.calendardayview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EventDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);
        ImageView image = findViewById(R.id.image_from_DB);
        TextView eventNameEt = (TextView) findViewById(R.id.event_name);
        TextView locationEt = (TextView) findViewById(R.id.event_location);
        TextView timeEt = (TextView) findViewById(R.id.event_time);
        TextView rsvpEt = (TextView) findViewById(R.id.event_rsvp_limit);
        TextView descriptionEt = (TextView) findViewById(R.id.event_description);
        String URL= "";

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            String url = extra.getString("path");
            String event_name = extra.getString("event_name");
            String location = extra.getString("location");
            String startTime = extra.getString("start_time");
            String endTime = extra.getString("end_time");
            String rsvp = extra.getString("rsvp");
            String description = extra.getString("description");

            eventNameEt.setText(event_name);
            locationEt.setText(location);
            timeEt.setText(startTime + " - " + endTime);
            rsvpEt.setText(rsvp);
            descriptionEt.setText(description);
            URL = url;
            Picasso.get().load(URL).into(image);
        }


    }
}
