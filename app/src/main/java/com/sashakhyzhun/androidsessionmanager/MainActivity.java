package com.sashakhyzhun.androidsessionmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SessionManager session;
    Button buttonLogout;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        session = new SessionManager(getApplicationContext());

        TextView tvUserName = (TextView)findViewById(R.id.tvUserName);
        TextView tvUserEmail = (TextView)findViewById(R.id.tvUserEmail);
        buttonLogout = (Button)findViewById(R.id.buttonLogut);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isUserLoggedIn(), Toast.LENGTH_LONG).show();

        if (session.checkLogin()) { finish(); }

        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(SessionManager.KEY_NAME);
        String email = user.get(SessionManager.KEY_EMAIL);

        tvUserName.setText(name);
        tvUserEmail.setText(email);

        buttonLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        session.logoutUser();
    }
}
