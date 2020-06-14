package com.fitnessfreak.pedometer;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fitnessfreak.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PedometerActivity extends AppCompatActivity {

    float meters;
    @BindView(R.id.steps)
    TextView steps;
    long calorie;
    SensorManager sensorManager;
    double MagnitudeDelta;
    private double magnitudePrevious = 0;
    private Integer stepCount = 0;
    boolean isStartEnabled = false;
    Sensor sensor;
    double Magnitude;
    @BindView(R.id.km)
    TextView km;
    @BindView(R.id.kcal)
    TextView kcal;
    @BindView(R.id.time)
    Chronometer chronometer;
    @BindView(R.id.start)
    CircleImageView start;
    @BindView(R.id.reset)
    ImageView stop;
    long stopTime = 0;

    @OnClick(R.id.reset)
    void stopPedometer() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        km.setText(String.valueOf(meters));
        kcal.setText(String.valueOf(calorie));
        isStartEnabled = false;
        start.setBackgroundResource(R.drawable.start);
        starttext.setText("Start");
        stepCount = sharedPreferences.getInt("stepCount", 0);
        steps.setText(String.valueOf(stepCount));
        chronometer.setBase(SystemClock.elapsedRealtime());
        stopTime = 0;
        chronometer.stop();
        sensorManager.unregisterListener(stepDetector);
    }

    @BindView(R.id.screenshot)
    ImageView screenshot;
    @BindView(R.id.start_text)
    TextView starttext;

    @OnClick(R.id.start)
    void startPedometer() {
        if (isStartEnabled) {
            stopTime = chronometer.getBase() - SystemClock.elapsedRealtime();
            chronometer.stop();
            isStartEnabled = false;
            start.setBackgroundResource(R.drawable.start);
            starttext.setText("Start");
            steps.setText(String.valueOf(stepCount));
            sensorManager.unregisterListener(stepDetector);
        } else {
            chronometer.setBase(SystemClock.elapsedRealtime() + stopTime);
            chronometer.start();
            isStartEnabled = true;
            start.setBackgroundResource(R.drawable.pause);
            starttext.setText("Pause");
            stepDetector = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    if (sensorEvent != null) {
                        float x_acceleration = sensorEvent.values[0];
                        float y_acceleration = sensorEvent.values[1];
                        float z_acceleration = sensorEvent.values[2];

                        Magnitude = Math.sqrt(x_acceleration * x_acceleration + y_acceleration * y_acceleration + z_acceleration * z_acceleration);
                        MagnitudeDelta = Magnitude - magnitudePrevious;
                        magnitudePrevious = Magnitude;

                        if (MagnitudeDelta > 6) {
                            stepCount++;
                        }
                        steps.setText(String.valueOf(stepCount));
                        meters =stepCount * 0.6f;
                        km.setText(String.format("%.1f",meters));
                        if (stepCount == 1000) calorie = 29;
                        else if (stepCount == 2000) calorie = 55;
                        else if (stepCount == 3000) calorie = 83;
                        else calorie = 0;
                        kcal.setText(String.valueOf(calorie));
                        //  steps.setText(stepCount.toString());
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {
                }
            };

            sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    SensorEventListener stepDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);
        ButterKnife.bind(this);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        stepCount = sharedPreferences.getInt("stepCount", 0);
        meters = sharedPreferences.getFloat("meter", 0f);
        calorie = sharedPreferences.getLong("calorie", 0);
        steps.setText(String.valueOf(stepCount));
        km.setText(String.format("%.1f",meters));
        kcal.setText(String.valueOf(calorie));
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);
        editor.putFloat("meter", meters);
        editor.putLong("calorie", calorie);
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);
        editor.putFloat("meter",meters);
        editor.putLong("calorie", calorie);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        stepCount = sharedPreferences.getInt("stepCount", 0);
        meters = sharedPreferences.getFloat("meter", 0f);
        calorie = sharedPreferences.getLong("calorie", 0);
    }
}
