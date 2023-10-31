package com.example.movingpicture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

public class MyGraphics extends View implements Runnable {

    Drawable guybrush;
    int guybrushHeight, guybrushWidth;
    int x = 200, y = 200;
    int xMove = 10, yMove = 10;

    int screenWidth, screenHeight;
    MainActivity main;

    public MyGraphics(MainActivity main) {
        super(main);
        this.main = main;
        guybrush = main.getResources().getDrawable(R.drawable.guybrush_sm, null);
        guybrushWidth = guybrush.getIntrinsicWidth();
        guybrushHeight = guybrush.getIntrinsicHeight();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
        screenHeight = h;

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
            if (x + guybrushWidth > screenWidth) {
                xMove = -xMove;
            }
            if (x < 0) {
                xMove = Math.abs(xMove);
            }

            if (y + guybrushHeight > screenHeight) {
                yMove = -yMove;
            }
            if (y < 0) {
                yMove = Math.abs(yMove);
            }

            x += xMove;
            y += yMove;

            postInvalidate();
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
