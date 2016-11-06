package com.sashakhyzhun.androidsessionmanager;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements View.OnClickListener {

    Button buttonLogin;
    EditText etUserName, etUserPassword;
    SessionManager session;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());
        etUserName = (EditText)findViewById(R.id.etUserName);
        etUserPassword = (EditText)findViewById(R.id.etUserPassword);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isUserLoggedIn(), Toast.LENGTH_LONG).show();

        buttonLogin.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        String userName = etUserName.getText().toString();
        String userPass = etUserPassword.getText().toString();

        if (userName.trim().length() > 0 && userPass.trim().length() > 0) {

            if (userName.equals("admin") && userPass.equals("admin")) {
                session.createUserLoginSession(userName, "support123@company.com");

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();

            } else {
                Toast.makeText(this, "Incorrect", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "Please enter data", Toast.LENGTH_LONG).show();
        }

    }


}
