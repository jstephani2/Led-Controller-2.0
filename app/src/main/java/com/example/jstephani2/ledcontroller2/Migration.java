package com.example.jstephani2.ledcontroller2;


import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if (oldVersion == 1) {
            RealmObjectSchema animationSchema = schema.get("LedAnimation");

        }
    }
}
