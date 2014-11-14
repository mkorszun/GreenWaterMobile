package com.greenwatermobile;

import android.app.Activity;
import android.os.Bundle;
import com.greenwatermobile.fragments.SettingsFragment;

public class SettingsActivity extends Activity {

    public static final String ENABLED = "THRESHOLD_ENABLED";
    public static final String DAY_THRESHOLD = "DAILY_THRESHOLD";
    public static final String WEEK_THRESHOLD = "WEEKLY_THRESHOLD";
    public static final String MONTH_THRESHOLD = "MONTHLY_THRESHOLD";
    public static final String YEAR_THRESHOLD = "YEARLY_THRESHOLD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
            .replace(android.R.id.content, new SettingsFragment())
            .commit();
    }
}
