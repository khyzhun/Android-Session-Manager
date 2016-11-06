package com.sashakhyzhun.androidsessionmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    private static final String PREFER_NAME = "PreferName"; // Shared pref file name
    private static final String IS_USER_LOGIN = "IsUserLoggedIn"; // All Shared Preferences Keys
    public static final String KEY_NAME = "name"; // User name (make variable public to access from outside)
    public static final String KEY_EMAIL = "email"; // Email address (make variable public to access from outside)
    private int PRIVATE_MODE = 0; // Shared pref mode
    private SharedPreferences pref; // Shared Preferences reference
    private SharedPreferences.Editor editor; // Editor reference for Shared preferences
    private Context context; // Context

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserLoginSession(String name, String email) {
        editor.putBoolean(IS_USER_LOGIN, true); // Storing login value as TRUE
        editor.putString(KEY_NAME, name); // Storing name in pref
        editor.putString(KEY_EMAIL, email); // Storing email in pref
        editor.commit();
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, ""));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, ""));
        return user;
    }

    public boolean checkLogin() {
        // Check login status
        if (!this.isUserLoggedIn()) {
            Intent i = new Intent(context, LoginActivity.class); // user is not logged in redirect him
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Closing all the Activities from stack
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Add new Flag to start new Activity
            context.startActivity(i);
            return true;
        }
        return false;
    }

    public void logoutUser(){
        editor.clear();  // Clearing all user data from Shared Preferences
        editor.commit();

        Intent i = new Intent(context, LoginActivity.class); // After logout redirect user to Login Activity
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Closing all the Activities
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Add new Flag to start new Activity
        context.startActivity(i);   // Staring Login Activity
    }

    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }



}
