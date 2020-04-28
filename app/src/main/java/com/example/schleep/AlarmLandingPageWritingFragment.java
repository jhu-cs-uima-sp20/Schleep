package com.example.schleep;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.os.Vibrator;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.Random;

import static android.content.Context.VIBRATOR_SERVICE;

public final class AlarmLandingPageWritingFragment extends Fragment implements SensorEventListener {
    private  long lastTime;
    private   float speed;
    private  float lastX;
    private  float lastY;
    private  float lastZ;
    private  float x, y, z;
    private int count;

    static final int SHAKE_THRESHOLD = 100;
    public static  int DATA_X = SensorManager.DATA_X;
    public static  int DATA_Y = SensorManager.DATA_Y;
    public static  int DATA_Z = SensorManager.DATA_Z;
    public SensorManager sensorManager;
    public Sensor accelerormeterSensor;
    private PowerManager pm;
    private PowerManager.WakeLock wl;
    private Vibrator vibrator;

    private String beeMovie = "According to all known laws of aviation, there is no way a bee should be able to fly.  Its wings are too small to get its fat little body off the ground.  The bee, of course, flies anyway because bees don't care what humans think is impossible.";
    private String princessBride = "The year that Buttercup was born, the most beautiful woman in the world was a French scullery maid named Annette. Annette worked in Paris for the Duke and Duchess de Guiche, and it did not escape the Duke's notice that someone extraordinary was polishing the pewter. The Duke's notice did not escape the notice of the Duchess either, who was not very beautiful and not very rich, but plenty smart. The Duchess set about studying Annette and shortly found her adversary's tragic flaw.";
    private String cinderella = "ONCE upon a time there was a gentleman who married, for his second wife, the proudest and most haughty woman that ever was seen. She had two daughters of her own, who were, indeed, exactly like her in all things. The gentleman had also a young daughter, of rare goodness and sweetness of temper, which she took from her mother, who was the best creature in the world.";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_alarm_landing_page_writing, container, false);
        vibrator = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        final Button checkWritingButton = (Button) v.findViewById(R.id.enter_writing_btn);
        final Button dismiss = (Button) v.findViewById(R.id.snooze_btn_write);
        final EditText writingText = (EditText) v.findViewById(R.id.user_writing_text);
        final TextView prompt = (TextView) v.findViewById(R.id.writing_prompt);
        prompt.setMovementMethod(new ScrollingMovementMethod());
        writingText.setMovementMethod(new ScrollingMovementMethod());
        Random randy = new Random();
        //int decide = randy.nextInt(3);
        int decide = 0;
        if (decide == 0) {
            prompt.setText(beeMovie);
        }
        else if (decide == 1) {
            prompt.setText(princessBride);
        }
        else if (decide == 3) {
            prompt.setText(cinderella);
        }
        else{
            prompt.setText(beeMovie);
        }
        final String[] intermediateInput = {""};
        ProgressBar wProgressBar = (ProgressBar) v.findViewById(R.id.progress_bar_writing);
        CountDownTimer wCountdownTimer;
        final int[] counter = {0};
        wProgressBar.setProgress(counter[0]);
        wCountdownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress" + counter[0] + millisUntilFinished);
                counter[0]++;
                wProgressBar.setProgress((int) counter[0] * 100/(30000/1000));
                if (!writingText.getText().toString().equals(intermediateInput[0])) {
                    wProgressBar.setProgress(0);
                }
            }

            @Override
            public void onFinish() {
                startvibe();
                //add making sounds here
            }
        };
        wCountdownTimer.start();

        CountDownTimer checkerCountDownTimer = new CountDownTimer(30000, 5000) {  //this timer is to check every 5 seconds that the user is typing
            @Override
            public void onTick(long millisUntilFinished) {
                intermediateInput[0] = writingText.getText().toString();
            }

            @Override
            public void onFinish() {
                //do nothing
            }
        };
        checkerCountDownTimer.start();

        checkWritingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check that the edit text content matches the content of the textview
                String userInput = writingText.getText().toString();
                if (userInput.equals(prompt.getText().toString())) {
                    startActivity(new Intent(getContext(), MainActivity.class));
                    getActivity().finish();
                }
                else {
                    Toast.makeText(getActivity(), "Incorrect! Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.cancel();
                getActivity().finish();
            }
        });
        pm=(PowerManager)getActivity().getSystemService(Context.POWER_SERVICE);

        //startvibe();
        return v;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void startvibe(){
        vibrator.vibrate(new long[]{100,1000,100,500,100,1000},0);
    }
}
