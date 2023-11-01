package com.example.sensormovingpicture;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.view.View;

public class MyGraphics extends View implements Runnable {

    Drawable guybrush;
    int guybrushWidth, guybrushHeight;
    int screenWidth, screenHeight;
    int x = 550, y = 750;
    MainActivity main;

    float xMove, yMove;

    public MyGraphics(MainActivity main) {
        super(main);
        this.main = main;
        guybrush = main.getResources().getDrawable(R.drawable.guybrush_tiny, null);
        guybrushWidth = guybrush.getIntrinsicWidth();
        guybrushHeight = guybrush.getIntrinsicHeight();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenHeight = h;
        screenWidth = w;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        guybrush.setBounds(x, y, x + guybrushWidth, y + guybrushHeight);
        guybrush.draw(canvas);
    }



    @Override
    public void run() {
        while (true) {
            xMove += -main.x / 2;
            yMove += main.y / 2;
            if (x + guybrushWidth >= screenWidth) {
                xMove = -Math.abs(xMove) * 0.7f;
            }
            if (x <= 0) {
                xMove = Math.abs(xMove) * 0.7f;
            }

            if (y + guybrushHeight > screenHeight) {
                yMove = -Math.abs(yMove) * 0.7f;
            }
            if (y < 0) {
                yMove = Math.abs(yMove) * 0.7f;
            }

            x += xMove;
            y += yMove;

            main.posX = x;
            main.posY = y;

            postInvalidate();
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
