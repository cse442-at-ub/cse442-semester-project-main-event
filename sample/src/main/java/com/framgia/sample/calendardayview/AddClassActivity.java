package com.framgia.sample.calendardayview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


/**
 * Mohammed Samsuddin
 * CSE442
 * Version: 01
 */

public class AddClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);


        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }
}
