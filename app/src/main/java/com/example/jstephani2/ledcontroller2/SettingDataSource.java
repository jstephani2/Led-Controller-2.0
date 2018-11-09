package com.example.jstephani2.ledcontroller2;

import android.util.Log;

import java.util.List;

import io.realm.Realm;

public class SettingDataSource {
    private static final String TAG = SettingDataSource.class.getSimpleName();
    private Realm realm;

    public void open() {
        realm = Realm.getDefaultInstance();
        Log.d(TAG, "Open: database opened");
    }
    public void close() {
        realm.close();
        Log.d(TAG, "Close: database closed");
    }

    public void createSetting (final LedSetting setting) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(setting);
            }
        });
    }

    List<LedSetting> getAllSettings() {
        return realm.where(LedSetting.class).findAll();
    }
}
