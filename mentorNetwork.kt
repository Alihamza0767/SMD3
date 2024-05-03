package com.example.i21_0846

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class mentorNetwork : Application()
{
    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

    }
}