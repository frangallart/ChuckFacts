package com.example.fran.chuckfacts.controllers;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import com.example.fran.chuckfacts.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe SplashScreen
 * Created by Fran on 27/02/2015.
 */
public class SplashScreen extends Activity {

    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 2500;

    /**
     * Mètode que s'executa en crear l'activitat
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Cridem al layout
        setContentView(R.layout.activity_splash_screen);

        // Timer per passar al menú principal
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                Intent mainIntent = new Intent().setClass(
                        SplashScreen.this, MainActivity.class);
                startActivity(mainIntent);

                // Close the activity so the user won't able to go back this
                // activity pressing Back button
                finish();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}
