package com.example.jstephani2.ledcontroller2;

import android.util.Log;

import java.util.List;

import io.realm.Realm;

public class AnimationDataSource {
    private static final String TAG = AnimationDataSource.class.getSimpleName();
    private Realm realm;

    public void open() {
        realm = Realm.getDefaultInstance();
        Log.d(TAG, "Open: database opened");
    }
    public void close() {
        realm.close();
        Log.d(TAG, "Close: database closed");
    }

    public void createAnimation (final LedAnimation animation) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(animation);
            }
        });
    }

    List<LedAnimation> getAllAnimations() {
        return realm.where(LedAnimation.class).findAll();
    }

    public void modifySettingVal(String id, String name, int val)
    {
        final LedAnimation animation = realm.where(LedAnimation.class)
                                            .equalTo("id", id)
                                            .findFirst();
        final String[] setting_names = animation.getSetting_names().split(",");
        final String[] setting_vals = animation.getSetting_vals().split(",");
        for (int i = 0; i < setting_names.length; i++) {
            if (setting_names[i].equals(name)) {
                setting_vals[i] = Integer.toString(val);
            }
        }
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                animation.setSetting_vals(setting_vals.toString());
            }
        });
    }

    public void modifyName(String code, String name)
    {

    }

    public void deleteAnimation(LedAnimation animation) {
        final LedAnimation animationManaged  = realm.where(LedAnimation.class).equalTo(LedAnimationFields.ID, animation.getId()).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                animationManaged.deleteFromRealm();
            }
        });
    }
}
