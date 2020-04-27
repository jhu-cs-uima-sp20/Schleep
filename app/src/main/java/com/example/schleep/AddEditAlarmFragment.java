package com.example.schleep;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;

public final class AddEditAlarmFragment extends Fragment {
    BottomSheetDialog bottomSheetDialog;
    private TimePicker mTimePicker;
    private EditText mLabel;
    private TextView repeat;
    private TextView Snooze;
    private TextView Sound;

    /*Test*/
    private TextView Task;

    private Button button1;
    private CheckBox mMon, mTues, mWed, mThurs, mFri, mSat, mSun;

    private boolean[] selected_days;

    public static AddEditAlarmFragment newInstance(Alarm alarm) {

        Bundle args = new Bundle();
        args.putParcelable(AddEditAlarmActivity.ALARM_EXTRA, alarm);

        AddEditAlarmFragment fragment = new AddEditAlarmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.createalarm_activity, container, false);
        final Alarm alarm = getAlarm();
        String task = getArguments().getString("task");
        //Button save = (Button) v.findViewById(R.id.save_button);
        mTimePicker = v.findViewById(R.id.time_picker);

        selected_days = new boolean[8];
        mLabel = (EditText) v.findViewById(R.id.name_alarm_create);
        ViewUtils.setTimePickerTime(mTimePicker, alarm.getTime());
        mLabel.setText(alarm.getLabel());
        repeat = v.findViewById(R.id.repeat3);
        Sound = v.findViewById(R.id.Sound);
        Snooze = v.findViewById(R.id.Snooze);
        Task = v.findViewById(R.id.task);
        String s = "";
        TextView rep = v.findViewById(R.id.task_button_repeat);
        if(alarm.getDay(Alarm.MON)){
            s += "M ";
        }
        if(alarm.getDay(Alarm.TUES)){
            s += "T ";
        }
        if(alarm.getDay(Alarm.WED)){
            s += "W ";
        }
        if(alarm.getDay(Alarm.THURS)){
            s += "Th ";
        }
        if(alarm.getDay(Alarm.FRI)){
            s += "F ";
        }
        if(alarm.getDay(Alarm.SAT)){
            s += "Sa ";
        }
        if(alarm.getDay(Alarm.SUN)){
            s += "Sa ";
        }

        if(s.compareTo("") != 0){

            rep.setText(s);
        }
        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
/*
        Task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectTask = new Intent(getActivity(), SelectTask.class);
                startActivity(selectTask);
            }
        });*/

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View val = getLayoutInflater().inflate(R.layout.bottom_sheet_days, null);
                Dialog d = new BottomSheetDialog(getActivity());
                d.setContentView(val);
                final Alarm alarm = getAlarm();
                d.show();
                RadioButton never = val.findViewById(R.id.Never);
                RadioButton mon = val.findViewById(R.id.Monday);
                RadioButton tue = val.findViewById(R.id.Tuesday);
                RadioButton wed = val.findViewById(R.id.Wednesday);
                RadioButton thur = val.findViewById(R.id.Thursday);
                RadioButton fri = val.findViewById(R.id.Friday);
                RadioButton sat = val.findViewById(R.id.Saturday);
                RadioButton sun = val.findViewById(R.id.Sunday);

                selected_days[1] =  alarm.getDay(Alarm.MON);
                mon.setChecked(selected_days[1]);
                selected_days[2] =  alarm.getDay(Alarm.TUES);
                tue.setChecked(selected_days[2]);
                selected_days[3] =  alarm.getDay(Alarm.WED);
                wed.setChecked(selected_days[3]);
                selected_days[4] =  alarm.getDay(Alarm.THURS);
                thur.setChecked(selected_days[4]);
                selected_days[5] =  alarm.getDay(Alarm.FRI);
                fri.setChecked(selected_days[5]);
                selected_days[6] =  alarm.getDay(Alarm.SAT);
                sat.setChecked(selected_days[6]);
                selected_days[7] =  alarm.getDay(Alarm.SUN);
                sun.setChecked(selected_days[7]);

                //Button b = findVietask_button_repeat

                mon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mon.setChecked(selected_days[1]);
                        if(!selected_days[1]){
                            mon.setChecked(true);
                            selected_days[1] = true;

                          //  Toast.makeText(getContext(), selected_days[1] + " :is checked 1" , Toast.LENGTH_SHORT).show();
                        }else{
                            mon.setChecked(false);
                            selected_days[1] = false;
                            //Toast.makeText(getContext(), selected_days[1]  + " :is checked 2" , Toast.LENGTH_SHORT).show();
                        }
                      //  Toast.makeText(getContext(), days_array[7] + " :is checked" , Toast.LENGTH_SHORT).show();
                    }
                });

                tue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!selected_days[2]){
                            tue.setChecked(true);
                            selected_days[2] = true;

                          //  Toast.makeText(getContext(), selected_days[1] + " :is checked 1" , Toast.LENGTH_SHORT).show();
                        }else{
                            tue.setChecked(false);
                            selected_days[2] = false;
                            //Toast.makeText(getContext(), selected_days[1]  + " :is checked 2" , Toast.LENGTH_SHORT).show();
                        }
                        //  Toast.makeText(getContext(), days_array[7] + " :is checked" , Toast.LENGTH_SHORT).show();
                    }
                });

                wed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!selected_days[3]){
                            wed.setChecked(true);
                            selected_days[3] = true;

                           // Toast.makeText(getContext(), selected_days[1] + " :is checked 1" , Toast.LENGTH_SHORT).show();
                        }else{
                            wed.setChecked(false);
                            selected_days[3] = false;
                            //Toast.makeText(getContext(), selected_days[1]  + " :is checked 2" , Toast.LENGTH_SHORT).show();
                        }
                        //  Toast.makeText(getContext(), days_array[7] + " :is checked" , Toast.LENGTH_SHORT).show();
                    }
                });

                thur.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!selected_days[4]){
                            thur.setChecked(true);
                            selected_days[4] = true;

                        //    Toast.makeText(getContext(), selected_days[1] + " :is checked 1" , Toast.LENGTH_SHORT).show();
                        }else{
                            thur.setChecked(false);
                            selected_days[4] = false;
                            //Toast.makeText(getContext(), selected_days[1]  + " :is checked 2" , Toast.LENGTH_SHORT).show();
                        }
                        //  Toast.makeText(getContext(), days_array[7] + " :is checked" , Toast.LENGTH_SHORT).show();
                    }
                });

                fri.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!selected_days[5]){
                            fri.setChecked(true);
                            selected_days[5] = true;
                        }else{
                            fri.setChecked(false);
                            selected_days[5] = false;
                            //Toast.makeText(getContext(), selected_days[1]  + " :is checked 2" , Toast.LENGTH_SHORT).show();
                        }
                        //  Toast.makeText(getContext(), days_array[7] + " :is checked" , Toast.LENGTH_SHORT).show();
                    }
                });
                sat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!selected_days[6]){
                            sat.setChecked(true);
                            selected_days[6] = true;
                        }else{
                            sat.setChecked(false);
                            selected_days[6] = false;
                            //Toast.makeText(getContext(), selected_days[1]  + " :is checked 2" , Toast.LENGTH_SHORT).show();
                        }
                        //  Toast.makeText(getContext(), days_array[7] + " :is checked" , Toast.LENGTH_SHORT).show();
                    }
                });

                sun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!selected_days[7]){
                            sun.setChecked(true);
                            selected_days[7] = true;

                            //Toast.makeText(getContext(), selected_days[1] + " :is checked 1" , Toast.LENGTH_SHORT).show();
                        }else{
                            sun.setChecked(false);
                            selected_days[7] = false;
                            //Toast.makeText(getContext(), selected_days[1]  + " :is checked 2" , Toast.LENGTH_SHORT).show();
                        }
                        //  Toast.makeText(getContext(), days_array[7] + " :is checked" , Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
       Sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View val = getLayoutInflater().inflate(R.layout.bottom_sheet_sounds, null);
                Dialog d = new BottomSheetDialog(getActivity());
                d.setContentView(val);
                d.show();
            }
        });
       Snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View val = getLayoutInflater().inflate(R.layout.bottom_sheet_snooze, null);
                Dialog d = new BottomSheetDialog(getActivity());
                d.setContentView(val);
                d.show();

            }
        });


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.edit_alarm_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                save();
                break;
            case R.id.action_delete:
                delete();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private Alarm getAlarm() {
        return getArguments().getParcelable(AddEditAlarmActivity.ALARM_EXTRA);
    }

    private void setDayCheckboxes(Alarm alarm) {
        mMon.setChecked(alarm.getDay(Alarm.MON));
        mTues.setChecked(alarm.getDay(Alarm.TUES));
        mWed.setChecked(alarm.getDay(Alarm.WED));
        mThurs.setChecked(alarm.getDay(Alarm.THURS));
        mFri.setChecked(alarm.getDay(Alarm.FRI));
        mSat.setChecked(alarm.getDay(Alarm.SAT));
        mSun.setChecked(alarm.getDay(Alarm.SUN));
    }

    private void save() {

        final Alarm alarm = getAlarm();

        final Calendar time = Calendar.getInstance();
        time.set(Calendar.MINUTE, ViewUtils.getTimePickerMinute(mTimePicker));
        time.set(Calendar.HOUR_OF_DAY, ViewUtils.getTimePickerHour(mTimePicker));
        alarm.setTime(time.getTimeInMillis());

        alarm.setLabel(mLabel.getText().toString());
        alarm.setDay(Alarm.MON,selected_days[1]);
        alarm.setDay(Alarm.TUES, selected_days[2]);
        alarm.setDay(Alarm.WED,selected_days[3]);
        alarm.setDay(Alarm.THURS, selected_days[4]);
        alarm.setDay(Alarm.FRI, selected_days[5]);
        alarm.setDay(Alarm.SAT,selected_days[6]);
        alarm.setDay(Alarm.SUN, selected_days[7]);

        final int rowsUpdated = DatabaseHelper.getInstance(getContext()).updateAlarm(alarm);
        final int messageId = (rowsUpdated == 1) ? 1 : 0;

        //Toast.makeText(getContext(), messageId, Toast.LENGTH_SHORT).show();

        //AlarmReceiver.setReminderAlarm(getContext(), alarm);
        AlarmReceiver.setReminderAlarm(getContext(), alarm);
        getActivity().finish();

    }

    private void delete() {

        final Alarm alarm = getAlarm();

        final AlertDialog.Builder builder =
                new AlertDialog.Builder(getContext(), R.style.DeleteAlarmDialogTheme);
        builder.setTitle(R.string.delete_dialog_title);
        builder.setMessage(R.string.delete_dialog_content);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //Cancel any pending notifications for this alarm
                //AlarmReceiver.cancelReminderAlarm(getContext(), alarm);

                final int rowsDeleted = DatabaseHelper.getInstance(getContext()).deleteAlarm(alarm);
                int messageId;
                if (rowsDeleted == 1) {
                    //messageId = R.string.delete_complete;
                    //Toast.makeText(getContext(), messageId, Toast.LENGTH_SHORT).show();
                    LoadAlarmsService.launchLoadAlarmsService(getContext());
                    getActivity().finish();
                } else {
                    messageId = R.string.delete_failed;
                    Toast.makeText(getContext(), messageId, Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton(R.string.no, null);
        builder.show();

    }

}