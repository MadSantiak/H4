package com.example.fingertouch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.method.Touch;
import android.view.MotionEvent;
import android.view.View;

public class MyGraphics extends View implements View.OnTouchListener {

    int centerX = 200;
    int centerY = 100;
    int radius = 100;
    boolean moving = false;
    int previousX,previousY;

    Context context;
    public MyGraphics(Context context) {
        super(context);
        this.context = context;
        this.setOnTouchListener(this);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        paint.setColor(0xFF00FF00);
        canvas.drawCircle(centerX,centerY, radius, paint);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int newY = (int) motionEvent.getY();
        int newX = (int) motionEvent.getX();

        switch(motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                int xDiff = newX - centerX;
                int yDiff = newY - centerY;
                if (Math.sqrt(xDiff * xDiff + yDiff * yDiff) < radius)
                {
                    moving = true;
                    previousX = newX;
                    previousY = newY;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (moving)
                {
                    centerX += newX - previousX;
                    centerY += newY - previousY;
                    previousX = newX;
                    previousY = newY;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                moving = false;
                break;
        }
        if (motionEvent.getPointerCount() == 2)
        {
            int x1 = (int)motionEvent.getX(0);
            int y1 = (int)motionEvent.getY(0);
            int x2 = (int)motionEvent.getX(1);
            int y2 = (int)motionEvent.getY(1);
            int x1Rel = x1 - centerX;
            int y1Rel = y1 - centerY;
            int x2Rel = x2 - centerX;
            int y2Rel = y2 - centerY;
            if(Math.sqrt(x1Rel * x1Rel + y1Rel * y1Rel) < radius
                    && Math.sqrt(x2Rel * x2Rel + y2Rel * y2Rel) < radius)
            {
                int xDiff = x1 - x2;
                int yDiff = y1 - y2;
                radius = (int)(Math.sqrt(xDiff * xDiff + yDiff * yDiff));
                invalidate();
            }
        }
        return true;
    }
}