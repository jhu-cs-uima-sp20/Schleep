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
    private Button button1;
    private CheckBox mMon, mTues, mWed, mThurs, mFri, mSat, mSun;

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
        mLabel = (EditText) v.findViewById(R.id.name_alarm_create);
        ViewUtils.setTimePickerTime(mTimePicker, alarm.getTime());
        mLabel.setText(alarm.getLabel());
        repeat = v.findViewById(R.id.repeat3);
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
                //button1 = val.findViewById(R.id.button1);
                /*button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getContext(), "BUTTON 1 !!!", Toast.LENGTH_SHORT).show();
                        //mListener.onButtonClicked("Button 1 clicked");
                        // dismiss();
                    }
                });*/
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
        alarm.setDay(Alarm.MON, true);
        alarm.setDay(Alarm.TUES, true);
        alarm.setDay(Alarm.WED, true);
        alarm.setDay(Alarm.THURS, true);
        alarm.setDay(Alarm.FRI, true);
        alarm.setDay(Alarm.SAT, true);
        alarm.setDay(Alarm.SUN, true);
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