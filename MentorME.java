package com.example.i21_0846;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class MentorME extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /* Enable disk persistence  */
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
