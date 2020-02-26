package com.example.eventfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create a reference to the promoted button
        Button PromotedButton = (Button) findViewById((R.id.PromotedButton));
        //set event listener for the button
        PromotedButton.setOnClickListener(
                //set interface
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        openPromotedEventsPage();
                    }
                }
        );
    }

    //This method opens the promoted events page when the button is clicked.
    private void openPromotedEventsPage() {
        Intent intent = new Intent(this,PromotedEventsPage.class);
        startActivity(intent);

    }
}
