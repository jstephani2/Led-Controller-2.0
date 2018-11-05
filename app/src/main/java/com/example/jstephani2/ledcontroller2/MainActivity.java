package com.example.jstephani2.ledcontroller2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Set;

public class MainActivity extends AppCompatActivity
implements LoaderManager.LoaderCallbacks<Cursor>
{

    private OutputStream outputStream;
    private InputStream inStream;
    private TextView mTextMessage;
    private Button homeAnimationButton;
    private LedSetting currAnimation;
    private CursorAdapter cursorAdapter;
    private Button createNewAnimationButton;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    homeAnimationButton.setVisibility(View.VISIBLE);
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    homeAnimationButton.setVisibility(View.INVISIBLE);
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    homeAnimationButton.setVisibility(View.INVISIBLE);
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    private void initBluetooth(int position) throws IOException {
        BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();
        if (blueAdapter != null) {
            Set<BluetoothDevice> bondedDevices = blueAdapter.getBondedDevices();

            if (bondedDevices.size() > 0) {
                Object[] devices = (Object []) bondedDevices.toArray();
                BluetoothDevice device = (BluetoothDevice) devices[devices.length-3];
                Log.d("devices", Arrays.toString(devices));
                ParcelUuid[] uuids = device.getUuids();
                BluetoothSocket socket = device.createRfcommSocketToServiceRecord(uuids[0].getUuid());
                socket.connect();
                outputStream = socket.getOutputStream();
                inStream = socket.getInputStream();
                Log.d("uuid", uuids[0].toString());
            } else {
                Log.e("error", "No appropriate paired devices.");
            }
        } else {
            Log.e("error", "Bluetooth is disabled");
        }
    }

    public void write(String s) throws IOException {
        outputStream.write(s.getBytes());
        Log.d("hi", s);
    }

    public void run() {
        final int BUFFER_SIZE = 1024;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytes = 0;
        int b = BUFFER_SIZE;

        while (true) {
            try {
                bytes = inStream.read(buffer, bytes, BUFFER_SIZE - bytes);
            } catch (IOException e) {
                 e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] from = {DBOpenHelper.SETTING_TEXT};
        int[] to = {android.R.id.text1};

        cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                null, from, to, 0);

        homeAnimationButton = (Button) findViewById(R.id.button);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        createNewAnimationButton = findViewById(R.id.new_animation_btn);
        createNewAnimationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                switchActivity(v);
            }
        });

//        try {
//            initBluetooth(0);
//            write("#r");
//        } catch (IOException e) {}
    }

    public void switchActivity (View view) {
        Intent intent = new Intent(this, NewAnimationActivity.class);
        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, SettingsProvider.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}
