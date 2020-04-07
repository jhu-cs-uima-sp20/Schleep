package com.example.schleep;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.IntDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;

public class CreateAlarmActivity extends AppCompatActivity {
    private TimePicker mTimePicker;
    private EditText mLabel;
    private LoadAlarmsReceiver mReceiver;
    //private Alarm alarm;
    public static final String MODE_EXTRA = "mode_extra";
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({EDIT_ALARM,ADD_ALARM,UNKNOWN})
    @interface Mode{}
    public static final int EDIT_ALARM = 1;
    public static final int ADD_ALARM = 2;
    public static final int UNKNOWN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createalarm_activity);
        Button save = (Button)findViewById(R.id.save_button);
        mReceiver = new LoadAlarmsReceiver();
        mTimePicker  = findViewById(R.id.time_picker);
        mLabel = (EditText) findViewById(R.id.name_alarm_create);
        final Alarm alarm = getAlarm();

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Calendar time = Calendar.getInstance();
                time.set(Calendar.MINUTE, com.example.schleep.ViewUtils.getTimePickerMinute(mTimePicker));
                time.set(Calendar.HOUR_OF_DAY, com.example.schleep.ViewUtils.getTimePickerHour(mTimePicker));
                alarm.setTime(time.getTimeInMillis());
                alarm.setLabel(mLabel.getText().toString());
                final int rowsUpdated = DatabaseHelper.getInstance(getApplicationContext()).updateAlarm(alarm);
                final String messageId = (rowsUpdated == 1) ? "update_complete" : "update_failed";
                Toast.makeText(getApplicationContext(), messageId, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getBaseContext(), "this ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    private Alarm getAlarm() {
        final long id = DatabaseHelper.getInstance(this).addAlarm();
        LoadAlarmsService.launchLoadAlarmsService(this);
        return new Alarm(id);
    }
    @Override
    public void onStart() {
        super.onStart();
        final IntentFilter filter = new IntentFilter(LoadAlarmsService.ACTION_COMPLETE);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mReceiver, filter);
        LoadAlarmsService.launchLoadAlarmsService(getApplicationContext());
    }
    public static Intent buildAddEditAlarmActivityIntent(Context context, @Mode int mode) {
        final Intent i = new Intent(context, CreateAlarmActivity.class);
        i.putExtra(MODE_EXTRA, mode);
        return i;
    }



}
