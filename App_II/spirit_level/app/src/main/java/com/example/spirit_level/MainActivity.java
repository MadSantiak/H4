package com.example.spirit_level;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView spirit, rotation;

    private SensorManager mSens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spirit = findViewById(R.id.spirit);
        rotation = findViewById(R.id.txtRotation);
        mSens = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        mSens.registerListener(this,
                mSens.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSens.unregisterListener(this,
                mSens.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType())
        {
            case Sensor.TYPE_ROTATION_VECTOR:
                spirit.setPadding((Math.round(event.values[2] * 1000)), 0, -(Math.round(event.values[2])), 0);
                rotation.setText(String.valueOf(event.values[2] * 1000));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}