package com.example.jstephani2.ledcontroller2;

import android.app.AppComponentFactory;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

public class NewAnimationActivity extends AppCompatActivity
//implements LoaderManager.LoaderCallbacks<Cursor>
{
    private Button saveAnimationButton;
    private Button addSettingButton;
   // private CursorAdapter cursorAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_animation);

        //cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
         //       null, from, to, 0);

        addSettingButton = findViewById(R.id.new_setting_btn);
        addSettingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {

            }
        });
        saveAnimationButton = findViewById(R.id.save_btn);
        saveAnimationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {

            }
        });

    }
//
//    @Override
//    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
//        return new CursorLoader(this, SettingsProvider.CONTENT_URI, null, null, null, null);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//       // cursorAdapter.swapCursor(data);
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//       // cursorAdapter.swapCursor(null);
//    }
}
