package com.greenwatermobile;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.greenwatermobile.fragments.LoginFragment;

public class LoginActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new LoginFragment())
                .commit();
        }
    }
}
