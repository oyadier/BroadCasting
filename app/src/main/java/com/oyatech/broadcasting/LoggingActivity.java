package com.oyatech.broadcasting;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;


public class LoggingActivity extends Activity {

    final String TAG =  getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null)
        {
            Log.d(TAG, "onCreate: object already created");
        }
else Log.d(TAG, "onCreate: activity is about to be created");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: the activity is becoming to the foreground");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: the activity is fully seen and interactive with the user");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: the activity is now invisible");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
