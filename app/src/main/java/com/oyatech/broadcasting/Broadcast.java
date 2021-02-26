package com.oyatech.broadcasting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import static com.oyatech.broadcasting.MainActivity.mHandlerThread;

public class Broadcast
        extends BroadcastReceiver {
    final String TAG =  getClass().getSimpleName();
    Context mContext;


    public Broadcast(Context context) {
     this.mContext = context;
    }

    public Broadcast() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: get called");

        int counter = intent.getIntExtra("Starter",0);

        /**
         * Creating a background thread for long computation so that since the onReceive OLNY
         * callback for 10 seconds
         */
       Handler handler = new Handler(mHandlerThread.getLooper());
       handler.post(runnable(counter));
        Log.d(TAG, "onReceive: " + "number received " + counter );
        Log.d(TAG, "onReceive: " +Thread.currentThread().getName());
    }

    /**
     *
     *
     * @param counter the user input value which determines how many time the for loop runs
     *                in the runnable
     * @return a an intance of the runnable to the post in the onReceives()
     */
    private Runnable runnable(final int counter) {

        return new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <counter ; i++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("counter").append(i);
                    SystemClock.sleep(1000);
                    Toast.makeText(mContext, sb, Toast.LENGTH_SHORT).show();

                    Log.d(TAG, "run: " + i + " on Thread " + Thread.currentThread().getName());
                }
            }
        };
    }
}
