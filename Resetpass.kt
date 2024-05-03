package com.example.i21_0846

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Resetpass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpass)


        var btn2 = findViewById<TextView>(R.id.buttonlogin)
        btn2.setOnClickListener {
            startActivity(
                Intent(this,
                    secondpage::class.java)
            );

        }
        var btn3 = findViewById<TextView>(R.id.buttonupload)
        btn3.setOnClickListener {
            startActivity(
                Intent(this,
                    secondpage::class.java)
            );

        }



    }
}