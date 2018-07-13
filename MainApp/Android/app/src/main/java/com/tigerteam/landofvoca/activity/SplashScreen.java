package com.tigerteam.landofvoca.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tigerteam.landofvoca.R;
import com.tigerteam.landofvoca.model.User;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    startActivity(LoginActivity.makeIntent(SplashScreen.this));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
