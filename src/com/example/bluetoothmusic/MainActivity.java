package com.example.bluetoothmusic;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    BluetoothAdapter bluetoothAdapter;

    //---check if bluetooth is available on this device---

    private boolean BluetoothAvailable() {
        if (bluetoothAdapter == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);

        Intent music = new Intent("com.android.music.musicservicecommand");

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (BluetoothAvailable())
        {
             if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                BluetoothAdapter.getDefaultAdapter().disable();
                music.putExtra("command", "stop");
                sendBroadcast(music);
             } else if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                 BluetoothAdapter.getDefaultAdapter().enable();
                 music.putExtra("command", "play");
                 sendBroadcast(music);
             }
        }

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}