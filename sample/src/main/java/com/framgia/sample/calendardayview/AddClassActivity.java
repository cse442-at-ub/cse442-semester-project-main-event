package com.framgia.sample.calendardayview;

import android.content.Intent;
import android.os.Parcelable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.framgia.library.calendardayview.CalendarDayView;
import com.framgia.library.calendardayview.EventView;
import com.framgia.library.calendardayview.data.IEvent;
import com.framgia.library.calendardayview.decoration.CdvDecorationDefault;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Mohammed Samsuddin
 * CSE442
 * Version: 01
 */

public class AddClassActivity extends AppCompatActivity {

    public static Calender cal = new Calender();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);



        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }


    public void addClass(View view) {

        int classColor = ContextCompat.getColor(this, R.color.eventColor1);


        EditText edit1 = (EditText)findViewById(R.id.editText);
        EditText edit2 = (EditText)findViewById(R.id.editText2);
        EditText edit3 = (EditText)findViewById(R.id.editText3);
        EditText edit4 = (EditText)findViewById(R.id.editText4);
        EditText edit5 = (EditText)findViewById(R.id.editText5);
        EditText edit6 = (EditText)findViewById(R.id.editText6);

        String classname = edit1.getText().toString();
        int startHour = Integer.parseInt(edit2.getText().toString());
        int startMin = Integer.parseInt(edit3.getText().toString());
        int endHour = Integer.parseInt(edit4.getText().toString());
        int endMin = Integer.parseInt(edit5.getText().toString());
        String location = edit6.getText().toString();


        cal.setClasses(getApplicationContext() , classname, startHour, startMin, endHour, endMin, location, classColor);

        /*Intent intent = new Intent(AddClassActivity.this, Calender.class);
        intent.putExtra("className", classname);
        intent.putExtra("startHour", startHour);
        intent.putExtra("startMin", startMin);
        intent.putExtra("endHour", endHour);
        intent.putExtra("endMin", endMin);
        intent.putExtra("Location", location);
        startActivity(intent);*/


        finish();
    }

















}
