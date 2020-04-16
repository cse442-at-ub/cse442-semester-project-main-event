package com.framgia.sample.calendardayview;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


import com.framgia.library.calendardayview.CalendarDayView;
import com.framgia.library.calendardayview.EventView;
import com.framgia.library.calendardayview.PopupView;
import com.framgia.library.calendardayview.data.IEvent;
import com.framgia.library.calendardayview.data.IPopup;
import com.framgia.library.calendardayview.decoration.CdvDecorationDefault;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


/**
 * Mohammed Samsuddin
 * CSE442
 * Version: 01
 */

public class Calender extends AppCompatActivity {


    public static CalendarDayView dayView;

    public static ArrayList<IEvent> myclasses = new ArrayList<>();
    //public static ArrayList<IPopup> myevents  = new ArrayList<>();
    //private static String user_name;

    public static int event_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        dayView = (CalendarDayView) findViewById(R.id.calendar);
        dayView.setLimitTime(8, 22);

        ((CdvDecorationDefault) (dayView.getDecoration())).setOnEventClickListener(
                new EventView.OnEventClickListener() {
                    @Override
                    public void onEventClick(EventView view, IEvent data) {
                        Log.e("TAG", "onEventClick:" + data.getName());
                    }

                    @Override
                    public void onEventViewClick(View view, EventView eventView, IEvent data) {
                        Log.e("TAG", "onEventViewClick:" + data.getName());
                        if (data instanceof Event) {
                            // change event (ex: set event color)
                            dayView.setEvents(myclasses);

                        }
                    }
                });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Calender.this, AddClassActivity.class));
                //Toast.makeText(getApplicationContext(),"Item add Selected",Toast.LENGTH_LONG).show();

            }
        });



        //Intent i = getIntent();
        //The second parameter below is the default string returned if the value is not there.
       // user_name = i.getExtras().getString("userName","");



        /*((CdvDecorationDefault) (dayView.getDecoration())).setOnPopupClickListener(
                new PopupView.OnEventPopupClickListener() {
                    @Override
                    public void onPopupClick(PopupView view, IPopup data) {
                        Log.e("TAG", "onPopupClick:" + data.getTitle());
                    }

                    @Override
                    public void onQuoteClick(PopupView view, IPopup data) {
                        Log.e("TAG", "onQuoteClick:" + data.getTitle());
                    }

                });*/





        /*String className = getIntent().getStringExtra("className");
        int startHour = getIntent().getIntExtra("startHour", 0);
        int startMin = getIntent().getIntExtra("startMin", 0);
        int endHour = getIntent().getIntExtra("endHour", 0);
        int endMin = getIntent().getIntExtra("endMin", 0);
        String location = getIntent().getStringExtra("Location");

        myclasses.add(setClasses(className, startHour, startMin, endHour, endMin, location));


        //Log.v("classes: ", String.valueOf(myclasses.size()));*/



        /*{

            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 12);
            timeStart.set(Calendar.MINUTE, 0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 13);
            timeEnd.set(Calendar.MINUTE, 30);

            Popup popup = new Popup();
            popup.setStartTime(timeStart);
            popup.setEndTime(timeEnd);
            popup.setTitle("Hack Night");
            popup.setDescription("Hacking with peoples");
            myevents.add(popup);
        }

        {

            Calendar timeStart = Calendar.getInstance();
            timeStart.set(Calendar.HOUR_OF_DAY, 14);
            timeStart.set(Calendar.MINUTE, 0);
            Calendar timeEnd = (Calendar) timeStart.clone();
            timeEnd.set(Calendar.HOUR_OF_DAY, 15);
            timeEnd.set(Calendar.MINUTE, 00);

            Popup popup = new Popup();
            popup.setStartTime(timeStart);
            popup.setEndTime(timeEnd);
            popup.setTitle("Math club");
            popup.setDescription("Solve math");
            myevents.add(popup);
        }


        dayView.setPopups(myevents);*/
        dayView.setEvents(myclasses);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }


    /**
     *
     * @param className
     * @param startHour
     * @param startMin
     * @param endHour
     * @param endMin
     * @param location
     */
    public static void setClasses(Context context, String className, int startHour, int startMin, int endHour, int endMin, String location, int classColor) {


        Calendar timeStart = Calendar.getInstance();
        timeStart.set(Calendar.HOUR_OF_DAY, startHour);
        timeStart.set(Calendar.MINUTE, startMin);
        Calendar timeEnd = (Calendar) timeStart.clone();
        timeEnd.set(Calendar.HOUR_OF_DAY, endHour);
        timeEnd.set(Calendar.MINUTE, endMin);
        Event event = new Event(event_ID, timeStart, timeEnd, className, "Location: " + location, classColor);


        Calendar class_oldEndTime = null;
        Calendar class_oldStartTime = null;

        Calendar event_oldEndTime = null;
        Calendar event_oldStartTime = null;

        boolean Class_collision = false;
        boolean event_collision = false;

        for (int i = 0; i < myclasses.size(); i++) {

            class_oldEndTime = myclasses.get(i).getEndTime();
            class_oldStartTime = myclasses.get(i).getStartTime();


            if (event.getEndTime().after(class_oldStartTime) && event.getStartTime().before(class_oldEndTime)) {
                Class_collision = true;
                break;
            }
        }


        /*for (int j = 0; j < myevents.size(); j++) {

            event_oldEndTime = myevents.get(j).getEndTime();
            event_oldStartTime = myevents.get(j).getStartTime();


            if (event.getEndTime().after(event_oldStartTime) && event.getStartTime().before(event_oldEndTime)) {
                event_collision = true;
                break;
            }
        }*/



        if (!Class_collision) {
            Toast.makeText(context, className + " Added", Toast.LENGTH_LONG).show();
            event_ID++;
            myclasses.add(event);

            String start_hour = String.valueOf(startHour);
            String start_min = String.valueOf(startMin);
            String end_hour = String.valueOf(endHour);
            String end_min = String.valueOf(endMin);

            String type = "addEvent";
            String user_name = "mohammed12";
            BackgroundWorker backgroundWorker = new BackgroundWorker(context);
            backgroundWorker.execute(type, user_name, className, start_hour, start_min, end_hour, end_min, location);

        } else {
            Toast.makeText(context, "Collision", Toast.LENGTH_LONG).show();
        }

        dayView.setEvents(myclasses);

    }

    /*public void saveData(String className, String startHour, String startMin, String endHour, String endMin, String location) {
        String type = "addEvent";
        user_name = "mohammed12";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, user_name, className, startHour, startMin, endHour, endMin, location);
    }*/



    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Intent intent1 = new Intent(this, AddClassActivity.class);
                this.startActivity(intent1);
                //Toast.makeText(getApplicationContext(),"Item add Selected",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/







}