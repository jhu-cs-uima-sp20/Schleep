package com.example.schleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
  static Map<Long, String> map;
  static Map<Long, String> math_map_dif;
  static Map<Long, String> math_map_ques;
  static String curr_task;
  static String curr_diff;
  static String curr_ques;
  static String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if(curr_task == null){

            curr_task = "none";
        }
        if(curr_diff == null){

            curr_diff = "easy";
        }
        if(curr_ques== null){
            curr_ques = "one";
        }
        if (map == null) {
        map = new HashMap<>();
        }
        if (math_map_dif == null) {
            math_map_dif = new HashMap<>();
        }
        if (math_map_ques == null) {
            math_map_ques = new HashMap<>();
        }
    }


}


/*
public class MainActivity extends AppCompatActivity implements LoadAlarmsReceiver.OnAlarmsLoadedListener {

    private Toolbar schleepToolbar;
    private FloatingActionButton createAlarmButton;
    private AlarmsAdapter mAdapter;
    public static final int EDIT_ALARM = 1;
    public static final int ADD_ALARM = 2;
   private LoadAlarmsReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
*/
        /* This code sets up the schleep Toolbar with the chewy font */
     /*   schleepToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(schleepToolbar);
        getSupportActionBar().setTitle(null);
        mReceiver = new LoadAlarmsReceiver(this);

        createAlarmButton = (FloatingActionButton) findViewById(R.id.createAlarmButton);
        createAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here
                //final Intent i = buildAddEditAlarmActivityIntent(MainActivity.class, ADD_ALARM);
                //startActivity(i);
                startActivity(new Intent(MainActivity.this, CreateAlarmActivity.class));
            }
        });*/
        /* When the floating action button is pressed, we will go to the Create
        Alarm activity.
         */
       /* EmptyRecyclerView rv = findViewById(R.id.recycler);
        mAdapter = new AlarmsAdapter();
        rv.setEmptyView(findViewById(R.id.empty_view));
        rv.setAdapter(mAdapter);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(), lm.getOrientation()));
        rv.setLayoutManager(lm);
        rv.setItemAnimator(new DefaultItemAnimator());
        Toast.makeText(getBaseContext(), mAdapter.getItemCount() + " items", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onStart() {
        super.onStart();
        final IntentFilter filter = new IntentFilter(LoadAlarmsService.ACTION_COMPLETE);
        LocalBroadcastManager.getInstance(getBaseContext()).registerReceiver(mReceiver, filter);
        LoadAlarmsService.launchLoadAlarmsService(this);
        Toast.makeText(this, mAdapter.getItemCount() + " items", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAlarmsLoaded(ArrayList<Alarm> alarms) {
        mAdapter.setAlarms(alarms);
    }
}
*/