package com.greenwatermobile.fragments;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.greenwatermobile.R;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}