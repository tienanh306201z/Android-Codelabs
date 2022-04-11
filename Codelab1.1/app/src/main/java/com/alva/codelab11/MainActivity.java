package com.alva.codelab11;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(LOG_TAG, "Question 1: activity_main.xml");
        Log.i(LOG_TAG, "Question 2: app_name");
        Log.i(LOG_TAG, "Question 3: Device Manager");
        Log.i("MainActivity", "MainActivity layout is complete");
    }
}