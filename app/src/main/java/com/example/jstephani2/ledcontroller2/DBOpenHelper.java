package com.example.jstephani2.ledcontroller2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    // Constants for db name and version
    private static final String DATABASE_NAME = "lights.db";
    private static final int DATABASE_VERSION = 1;

    // Constants for identifying table and columns
    public static final String TABLE = "lights";
    public static final String SETTING_ID = "_id";
    public static final String SETTING_TEXT = "noteText";
    public static final String SETTING_CREATED = "noteCreated";

    public static final String[] ALL_COLUMNS =
            {SETTING_ID, SETTING_TEXT, SETTING_CREATED};

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE + " (" +
                    SETTING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SETTING_TEXT + " TEXT, " +
                    SETTING_CREATED + " TEXT default CURRENT_TIMESTAMP" +
                    ")";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
