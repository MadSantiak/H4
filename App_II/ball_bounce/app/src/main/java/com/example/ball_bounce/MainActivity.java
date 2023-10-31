package com.example.ball_bounce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    ImageView imgBall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        imgBall = findViewById(R.id.imgBall);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBall.clearAnimation();

                TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0, MainActivity.this.getResources().getDisplayMetrics().heightPixels/2);
                translateAnimation.setStartOffset(350);
                translateAnimation.setDuration(3000);
                translateAnimation.setFillAfter(true);
                translateAnimation.setInterpolator(new BounceInterpolator());
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        imgBall.clearAnimation();
                        final int left = imgBall.getLeft();
                        final int top = imgBall.getTop();
                        final int right = imgBall.getRight();
                        final int bottom = imgBall.getBottom();
                        imgBall.layout(left,top,right,bottom);
                    }
                });

                imgBall.startAnimation(translateAnimation);
            }
        });
    }
}