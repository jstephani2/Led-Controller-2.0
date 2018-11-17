package com.example.jstephani2.ledcontroller2;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.ParcelUuid;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Set;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LedControllerApplication extends Application {

    public static OutputStream outputStream;
    private InputStream inStream;

    public static final int SCHEMA_VERSION = 2;
    @Override
    public void onCreate() {
        super.onCreate();

        // Realm setup
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .schemaVersion(SCHEMA_VERSION)
                .migration(new Migration())
                .name("animation.realm")
                .build();
        Realm.setDefaultConfiguration(configuration);

        try {
            initBluetooth(0);
        } catch (IOException e) {
            Log.d("thisbroke",e.toString());
        }
    }

    private void initBluetooth(int position) throws IOException {
        BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();
        if (blueAdapter != null) {
            Set<BluetoothDevice> bondedDevices = blueAdapter.getBondedDevices();

            if (bondedDevices.size() > 0) {
                Log.i("bluetooth", Arrays.toString(bondedDevices.toArray()));
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
}
