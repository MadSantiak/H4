package com.example.sensormovingpicture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    LinearLayout linLayout;
    TextView txtX, txtY, txtZ;
    int y, x;
    int posY, posX;

    private SensorManager mSensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linLayout = findViewById(R.id.linLayout);
        txtX = findViewById(R.id.txtX);
        txtY = findViewById(R.id.txtY);
        txtZ = findViewById(R.id.txtZ);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        MyGraphics mg = new MyGraphics(this);

        // Brief storage during application lifecycle, when activity is restarted.
        if (savedInstanceState != null)
        {
            posX = savedInstanceState.getInt("posX");
            posY = savedInstanceState.getInt("posY");
            mg.x = posX;
            mg.y = posY;
        }

        linLayout.addView(mg);
        Thread t = new Thread(mg);
        t.start();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        mSensorManager.unregisterListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = Math.round(sensorEvent.values[0]);
            y = Math.round(sensorEvent.values[1]);
            txtX.setText(String.valueOf(sensorEvent.values[0]));
            txtY.setText(String.valueOf(sensorEvent.values[1]));
            txtZ.setText(String.valueOf(sensorEvent.values[2]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("posX", posX);
        outState.putInt("posY", posY);
    }
}