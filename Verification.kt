package com.example.i21_0846

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Verification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        var btn1 = findViewById<TextView>(R.id.forwar)
        btn1.setOnClickListener {
            startActivity(
                Intent(this,
                    Resetpass::class.java)
            );

        }

        var btn2 = findViewById<TextView>(R.id.textview2)
        btn2.setOnClickListener {
            startActivity(
                Intent(this,
                    ForgetPass::class.java)
            );

        }
    }
}