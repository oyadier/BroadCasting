package com.oyatech.broadcasting;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends LoggingActivity {
final static String SET_INCREMENT = "com.oyatech.broadcasting.INCREMENT";

EditText userData;
Switch snack;
    public static  HandlerThread mHandlerThread;
    private Broadcast mBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userData = findViewById(R.id.editTextTextPersonName);
        snack = findViewById(R.id.switch1);
        registerBroadcast();

        snack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(snack.isChecked())
                {
                    Snackbar.make(compoundButton,"Snack",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void sendBroadcast(View view) {
        int count = Integer.parseInt(userData.getText().toString());
        sendingBroadcast(count);
    }

    /**
     *
     * @param userData data entered by the user
     */
    public void sendingBroadcast (int userData)
    {
        Intent intent = new Intent();
        intent.setAction(SET_INCREMENT);
        intent.putExtra("Starter",userData);
        Log.d(TAG, "sendingBroadcast: sending incremental Broadcast");
        /**
         * making sure that the broadcast is sent for component withing the package name
         */
        //intent.setPackage(this.getPackageName());
        sendBroadcast(intent);
    }

    /**
     * Dynamically registering a broadcast receiver
     */
    public void registerBroadcast ()
    {
        mBroadcast = new Broadcast(this);
        mHandlerThread = new HandlerThread("Broadcasting");
        mHandlerThread.start();
        IntentFilter filter = new IntentFilter();
        filter.addAction(SET_INCREMENT);
        registerReceiver(mBroadcast,filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: the activity is completely destroyed");
        this.unregisterReceiver(mBroadcast);
        Log.d(TAG, "onDestroy: " + "unregistering broadcast receiver");
        mBroadcast = null;

    }
}