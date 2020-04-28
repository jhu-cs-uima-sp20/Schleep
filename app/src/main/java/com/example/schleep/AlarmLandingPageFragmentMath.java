package com.example.schleep;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.w3c.dom.Text;

import java.util.Random;

import static android.content.Context.VIBRATOR_SERVICE;
import static com.example.schleep.MainActivity.password;

public final class AlarmLandingPageFragmentMath extends Fragment implements SensorEventListener {
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
    int correct = 0;
    private PowerManager.WakeLock wl;
    private Vibrator vibrator;
    int num;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_alarm_landing_page_math, container, false);
        vibrator = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        final Button enterBtn = (Button) v.findViewById(R.id.enter_btn);
        final Button dismiss = (Button) v.findViewById(R.id.dismiss_btn);
        final EditText inputText = (EditText) v.findViewById(R.id.input);
        final TextView textView = (TextView) v.findViewById(R.id.question);
        final ImageButton keyBtn = (ImageButton) v.findViewById(R.id.key_button);
        //do math stuff here!
        num = Integer.parseInt(AlarmReceiver.math_ques);
        int[] answer = {0};
        String expr = getExpr(answer);
        correct = 0;
        textView.setText(expr);

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (!isInt(inputText.getText().toString())) {
                    // throw toast and erase text then break?
                    Toast toast= Toast.makeText(getActivity(),"Invalid Integer",Toast.LENGTH_SHORT);
                    toast.show();
                }*/
               // Toast.makeText(getActivity(),"Questions: " + AlarmReceiver.math_ques + "" ,Toast.LENGTH_SHORT).show();

                // check if answer is correct
                try {
                    if (answer[0] == Integer.parseInt(inputText.getText().toString())) {
                        count++;
                        if(count == num) {
                            getActivity().finish();
                            startActivity(new Intent(getContext(), MainActivity.class));
                            //vibrator.cancel();
                        } else {
                            String exp = getExpr(answer);
                            textView.setText(exp);
                        }
                        //getActivity().finish();
                    } else {
                        Toast toast = Toast.makeText(getActivity(), "Incorrect", Toast.LENGTH_SHORT);
                        toast.show();
                        //make text disappear?
                    }
                }
                catch (NumberFormatException e) {
                    Toast toast = Toast.makeText(getActivity(), "Invalid Integer", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vibrator.cancel();
                getActivity().finish();
            }
        });

        keyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordFragment fragment2 = new PasswordFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        pm=(PowerManager)getActivity().getSystemService(Context.POWER_SERVICE);

        //startvibe();
        return v;
    }

    @Override
    public void onStart(){
        super.onStart();
        if(accelerormeterSensor!=null)
            sensorManager.registerListener(this, accelerormeterSensor,
                    SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onStop() {
        super.onStop();
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
            if (gabOfTime > 100) {
                lastTime = currentTime;
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];
                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;
                if (speed > SHAKE_THRESHOLD) {
                    lastTime=currentTime;
                    count++;//for shake test
                    if(count==10)
                    {
                        vibrator.cancel();
                        getActivity().finish();
                    }
                }
                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }
        }
    }

    public void startvibe(){
        vibrator.vibrate(new long[]{100,1000,100,500,100,1000},0);
    }

    public String getExpr(int[] answer) {
        Random rand = new Random();
        StringBuilder expr = new StringBuilder("");
        int int1 = 0;
        int int2 = 0;

        switch (rand.nextInt(4)) {
            case 0: // addition
                int1 = rand.nextInt(100) + 1;
                int2 = rand.nextInt(100) + 1;
                answer[0] = int1 + int2;
                expr.append(int1);
                expr.append(" + ");
                expr.append(int2);
                expr.append(" = ?");
                break;
            case 1: // subtraction
                int1 = rand.nextInt(100) + 1;
                int2 = rand.nextInt(100) + 1;

                // if int2 > int1, swap
                if (int2 > int1) {
                    int temp = int1;
                    int1 = int2;
                    int2 = temp;
                }

                answer[0] = int1 - int2;
                expr.append(int1);
                expr.append(" - ");
                expr.append(int2);
                expr.append(" = ?");
                break;
            case 2: // multiplication
                int1 = rand.nextInt(12) + 1;
                int2 = rand.nextInt(12) + 1;
                answer[0] = int1 * int2;
                expr.append(int1);
                expr.append(" * ");
                expr.append(int2);
                expr.append(" = ?");
                break;
            case 3: // division
                int1 = rand.nextInt(100) + 1;
                int2 = rand.nextInt(12) + 1;

                // if int2 > int1, swap
                if (int2 > int1) {
                    int temp = int1;
                    int1 = int2;
                    int2 = temp;
                }
                answer[0] = int1 / int2;
                expr.append(int1);
                expr.append(" / ");
                expr.append(int2);
                expr.append(" = ?");
                break;
        }

        return expr.toString();
    }

    public boolean isInt(String str) {
        for(int i = 0; i < str.length(); i++) {
            if(i == 0 && str.charAt(i) == '-') {
                if(str.length() == 1)
                    return false;
                else
                    continue;
            }
            if(!Character.isDigit(str.charAt(i)))
                return false;
        }
        return true;
    }
}