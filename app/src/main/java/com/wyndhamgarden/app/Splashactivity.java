package com.wyndhamgarden.app;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splashactivity extends Activity {
    private static int SPLASH_TIME_OUT = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);
        startThread();
    }

    private void startThread() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Splashactivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
