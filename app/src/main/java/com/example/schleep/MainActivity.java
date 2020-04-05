package com.example.schleep;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private Toolbar schleepToolbar;
    private FloatingActionButton createAlarmButton;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* This code sets up the schleep Toolbar with the chewy font */
        schleepToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(schleepToolbar);
        getSupportActionBar().setTitle(null);

        /* When the floating action button is pressed, we will go to the Create
        Alarm activity.
         */
        createAlarmButton = (FloatingActionButton) findViewById(R.id.createAlarmButton);
        createAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here
                startActivity(new Intent(MainActivity.this, CreateAlarmActivity.class));
            }
        });
    }
}
