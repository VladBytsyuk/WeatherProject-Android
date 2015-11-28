package com.vladbytsyuk.weatherforecastproject.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.vladbytsyuk.weatherforecastproject.R;

/**
 * Created by VladBytsyuk on 22.11.2015.
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.splash_screen);
        setContentView(R.layout.activity_splash_screen);
        long SPLASH_SCREEN_DELAY_MILLISECONDS = 1500 /* milliseconds */;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainActivityIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                SplashScreenActivity.this.startActivity(mainActivityIntent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_SCREEN_DELAY_MILLISECONDS);
    }
}
