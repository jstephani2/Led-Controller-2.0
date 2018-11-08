package com.example.jstephani2.ledcontroller2;

import io.realm.Realm;

public class SettingDataSource {
    private static final String TAG = SettingDataSource.class.getSimpleName();
    private Realm realm;

    public void open() {
        realm = Realm.getDefaultInstance();

    }
    public void close() {
        realm.close();
    }

    public void createSetting (final LedSetting setting) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(setting);
            }
        });
    }
}
