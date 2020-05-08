package com.framgia.sample.calendardayview;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.framgia.library.calendardayview.CalendarDayView;
import com.framgia.library.calendardayview.EventView;
import com.framgia.library.calendardayview.PopupView;
import com.framgia.library.calendardayview.data.IEvent;
import com.framgia.library.calendardayview.data.IPopup;
import com.framgia.library.calendardayview.decoration.CdvDecorationDefault;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
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
    public static String user_name = "";

    public static int event_ID = 1;


    // Floating Action Button
    FloatingActionButton fab;
    RelativeLayout rootLayout;

    //Save the FAB's active status
    //false -> fab = close
    //true -> fab = open
    private boolean FAB_Status = false;

    //Animations
    Animation show_fab;
    Animation hide_fab;



    String allEventsUnseparated;
    ArrayList<ArrayList<String>> dataSeparatedEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        rootLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        //Floating Action Buttons
        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton2);

        //Animations
        show_fab = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_show);
        hide_fab = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_hide);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (FAB_Status == false) {
                    //Display FAB menu
                    expandFAB();
                    FAB_Status = true;
                } else {
                    //Close FAB menu
                    hideFAB();
                    FAB_Status = false;
                }
            }
        });




        dayView = (CalendarDayView) findViewById(R.id.calendar);
        dayView.setLimitTime(8, 22);

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            user_name = extra.getString("current_user");
        }


        ((CdvDecorationDefault) (dayView.getDecoration())).setOnEventClickListener(
                new EventView.OnEventClickListener() {
                    @Override
                    public void onEventClick(EventView view, IEvent data) {
                        Log.e("TAG", "onEventClick:" + data.getName());
                    }

                    public boolean onTouch(View v, MotionEvent event) {
                        if (FAB_Status) {
                            hideFAB();
                            FAB_Status = false;
                        }
                        return false;
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


        //retrieve_User_Events();
        //getEvents();
        //splitEventData();
        //EventItems();






        dayView.setEvents(myclasses);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }

    private void retrieve_User_Events(){
        String type = "retrieve_user_events";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, user_name);
    }

    private void splitEventData() {
        if (allEventsUnseparated != null) {
            String[] eachEvent = allEventsUnseparated.split("\\|");
            eachEvent = Arrays.copyOf(eachEvent, eachEvent.length - 1);
            System.out.println(Arrays.toString(eachEvent));
            ArrayList<ArrayList<String>> eventsWithTheirDataSeparated = new ArrayList<>();
            for (String x : eachEvent) {
                String[] dataSeparatedA = x.split(",");
                ArrayList<String> dataSeparatedAL = new ArrayList<>(Arrays.asList(dataSeparatedA));
                eventsWithTheirDataSeparated.add(dataSeparatedAL);
            }
            dataSeparatedEvents = eventsWithTheirDataSeparated;

        }
//        for (ArrayList<String> AL: dataSeparatedEvents) {
//            for(String x : AL) {
//                System.out.println(x);
//            }
//        }
    }

    private void getEvents() {

        FileInputStream fis = null;
        try {
            fis = openFileInput("Events.txt");
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


    private void EventItems() {
        int classColor = ContextCompat.getColor(this, R.color.eventColor1);
        Iterator<ArrayList<String>> iter = dataSeparatedEvents.iterator();
        ArrayList<String> found = new ArrayList<>();
        while(iter.hasNext()){
            found = iter.next();
            int startHour = Integer.parseInt(found.get(1));
            int startMin = Integer.parseInt(found.get(2));
            int endHour = Integer.parseInt(found.get(3));
            int endMin = Integer.parseInt(found.get(4));

            setClasses(getApplicationContext(), found.get(0), startHour, startMin, endHour, endMin, found.get(5), classColor);
        }


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

            String type = "addCalendarEvent";

            BackgroundWorker backgroundWorker = new BackgroundWorker(context);
            backgroundWorker.execute(type, user_name, className, start_hour, start_min, end_hour, end_min, location);

        } else {
            Toast.makeText(context, "Collision", Toast.LENGTH_LONG).show();
        }

        dayView.setEvents(myclasses);

    }


    private void expandFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab.getLayoutParams();
        layoutParams.rightMargin += (int) (fab.getWidth() * 1.7);
        layoutParams.bottomMargin += (int) (fab.getHeight() * 0.25);
        fab.setLayoutParams(layoutParams);
        fab.startAnimation(show_fab);
        fab.setClickable(true);

    }


    private void hideFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab.getLayoutParams();
        layoutParams.rightMargin -= (int) (fab.getWidth() * 1.7);
        layoutParams.bottomMargin -= (int) (fab.getHeight() * 0.25);
        fab.setLayoutParams(layoutParams);
        fab.startAnimation(hide_fab);
        fab.setClickable(false);

    }




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