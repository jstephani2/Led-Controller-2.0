package com.example.jstephani2.ledcontroller2;

import android.app.AppComponentFactory;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class NewAnimationActivity extends AppCompatActivity
//implements LoaderManager.LoaderCallbacks<Cursor>
{
    private Button saveAnimationButton;
    private Button addSettingButton;
    private EditText nameField;
    private EditText codeField;
    private EditText paramsField;
    private AnimationDataSource dataSource;
    private LedAnimation animation;
   // private CursorAdapter cursorAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_animation);

        dataSource = new AnimationDataSource();
        dataSource.open();
        nameField = findViewById(R.id.name_text);
        codeField = findViewById(R.id.code_text);
        paramsField = findViewById(R.id.setting_text);


        Intent intent = getIntent();
        if (intent.getParcelableExtra("animation") != null) {
            animation = intent.getParcelableExtra("animation");
            nameField.setText(animation.getName());
            codeField.setText(animation.getCode());
            paramsField.setText(animation.getSetting_vals());
        }

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
                if (animation == null) {
                    animation = new LedAnimation(nameField.getText().toString(), codeField.getText().toString(), "1", paramsField.getText().toString());
                } else {
                    animation.setName(nameField.getText().toString());
                    animation.setCode(codeField.getText().toString());
                    animation.setSetting_vals(paramsField.getText().toString());
                }
                dataSource.createAnimation(animation);

                Toast.makeText(NewAnimationActivity.this, "Animation " + animation.getName() +  " created with code " + animation.getCode() + "," + animation.getSetting_vals(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewAnimationActivity.this, MainActivity.class);
                startActivity(intent);
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
