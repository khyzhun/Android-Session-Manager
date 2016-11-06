package com.sashakhyzhun.androidsessionmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(getApplicationContext());
        Intent i;
        if (sessionManager.isUserLoggedIn()) {
            i = new Intent(this, MainActivity.class);
        } else {
            i = new Intent(this, LoginActivity.class);
        }
        startActivity(i);

    }
}
