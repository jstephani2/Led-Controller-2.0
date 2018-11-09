package com.example.jstephani2.ledcontroller2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class SettingsProvider {
    public static List<LedSetting> settingList;

    static
    {
        settingList = new ArrayList<>();


    }

    private static void addSetting(LedSetting setting) {
        settingList.add(setting);
    }
}
