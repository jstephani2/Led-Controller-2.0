package com.example.jstephani2.ledcontroller2;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LedControllerApplication extends Application {

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
    }
}
