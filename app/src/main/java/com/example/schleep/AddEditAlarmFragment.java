package com.example.schleep;

import android.app.Dialog;
import android.content.DialogInterface;
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
        //Button save = (Button) v.findViewById(R.id.save_button);
        mTimePicker = v.findViewById(R.id.time_picker);

        selected_days = new boolean[8];
        mLabel = (EditText) v.findViewById(R.id.name_alarm_create);
        ViewUtils.setTimePickerTime(mTimePicker, alarm.getTime());
        mLabel.setText(alarm.getLabel());
        repeat = v.findViewById(R.id.repeat3);
        Sound = v.findViewById(R.id.Sound);
        Snooze = v.findViewById(R.id.Snooze);

        //button1 = v.findViewById(R.id.button1);

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //BottomSheetDays bottomSheet = new BottomSheetDays ();
                //bottomSheet.show(getParentFragmentManager(), "exampleBottomSheet");
                /*button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "BUTTON 1 !!!", Toast.LENGTH_SHORT).show();
                        //mListener.onButtonClicked("Button 1 clicked");
                        // dismiss();
                    }
                });*/
                //bottomSheetDialog.show();
                // ExampleBottomSheetDialog bottomSheet = new ExampleBottomSheetDialog();
                //bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
            }
        });




    /*save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });*/
        setHasOptionsMenu(true);

/*
        mTimePicker = (TimePicker) v.findViewById(R.id.edit_alarm_time_picker);
        ViewUtils.setTimePickerTime(mTimePicker, alarm.getTime());

        mLabel = (EditText) v.findViewById(R.id.edit_alarm_label);
        mLabel.setText(alarm.getLabel());

        mMon = (CheckBox) v.findViewById(R.id.edit_alarm_mon);
        mTues = (CheckBox) v.findViewById(R.id.edit_alarm_tues);
        mWed = (CheckBox) v.findViewById(R.id.edit_alarm_wed);
        mThurs = (CheckBox) v.findViewById(R.id.edit_alarm_thurs);
        mFri = (CheckBox) v.findViewById(R.id.edit_alarm_fri);
        mSat = (CheckBox) v.findViewById(R.id.edit_alarm_sat);
        mSun = (CheckBox) v.findViewById(R.id.edit_alarm_sun);

        setDayCheckboxes(alarm);
 */

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View val = getLayoutInflater().inflate(R.layout.bottom_sheet_days, null);
                Dialog d = new BottomSheetDialog(getActivity());
                d.setContentView(val);
                d.show();
                RadioButton never = val.findViewById(R.id.Never);
                RadioButton mon = val.findViewById(R.id.Monday);
                RadioButton tue = val.findViewById(R.id.Tuesday);
                RadioButton wed = val.findViewById(R.id.Wednesday);
                RadioButton thur = val.findViewById(R.id.Thursday);
                RadioButton fri = val.findViewById(R.id.Friday);
                RadioButton sat = val.findViewById(R.id.Saturday);
                RadioButton sun = val.findViewById(R.id.Sunday);


                mon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                /*
                if(never.isChecked()){
                    for(int i = 0; i < 8; i++){
                        days_array[i] = false;
                    }
                } else{
                    days_array[1] = selected_days[1];
                    days_array[2] = selected_days[2];
                    days_array[3] = selected_days[3];
                    days_array[4] = selected_days[4];
                    days_array[5] = selected_days[5];
                    days_array[6] = selected_days[6];
                    days_array[7] = selected_days[7];
                }

                 */

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
/*
        alarm.setDay(Alarm.MON, mMon.isChecked());
        alarm.setDay(Alarm.TUES, mTues.isChecked());
        alarm.setDay(Alarm.WED, mWed.isChecked());
        alarm.setDay(Alarm.THURS, mThurs.isChecked());
        alarm.setDay(Alarm.FRI, mFri.isChecked());
        alarm.setDay(Alarm.SAT, mSat.isChecked());
        alarm.setDay(Alarm.SUN, mSun.isChecked());
*/
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