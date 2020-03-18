package com.omaressam.hangin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    private static  int Splash_time = 2000;
    private static final int TIME_INTERVAL = 3000; // # milliseconds, desired time passed between two back presses.
    private ImageView imageView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = findViewById(R.id.hangin);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this,Main3Activity.class);
                startActivity(intent);
                finish();
            }

        },Splash_time);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.myanim);
        imageView.startAnimation(animation);
    }




}
