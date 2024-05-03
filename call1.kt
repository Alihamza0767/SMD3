package com.example.i21_0846

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent

class call1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call1)

        var btn1 = findViewById<TextView>(R.id.camera)
        btn1.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    call2::class.java
                )
            );
        }
    }
}