package atc.android.dialogs.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import atc.android.dialogs.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}