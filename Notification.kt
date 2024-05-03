package com.example.i21_0846

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Notification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)


        var btn1 = findViewById<TextView>(R.id.textView3)
        btn1.setOnClickListener {
            startActivity(
                Intent(this,
                    Home::class.java)
            );

        }
    }
}