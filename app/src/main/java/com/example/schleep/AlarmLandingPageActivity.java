package com.example.schleep;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public final class AlarmLandingPageActivity extends AppCompatActivity {
    private static final int CONTENT_VIEW_ID = 10101010;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_landing_page);
/*
        Fragment newFragment = new AlarmLandingPageFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(CONTENT_VIEW_ID, newFragment).commit();
        */
//landing_page_fragment_container
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(AlarmReceiver.selected_task.compareTo("math") == 0) {
// Replace the contents of the container with the new fragment
            ft.replace(R.id.landing_page_fragment_container, new AlarmLandingPageFragmentMath());
            ft.commit();
        }
        else if(AlarmReceiver.selected_task.compareTo("typing") == 0) {
// Replace the contents of the container with the new fragment
            ft.replace(R.id.landing_page_fragment_container, new AlarmLandingPageWritingFragment());
            ft.commit();
        } else{
            ft.replace(R.id.landing_page_fragment_container, new AlarmLandingPageFragment());
            ft.commit();
        }
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above

    }

    public static Intent launchIntent(Context context) {
        final Intent i = new Intent(context, AlarmLandingPageActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return i;
    }

}
