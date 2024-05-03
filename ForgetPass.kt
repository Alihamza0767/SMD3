package com.example.i21_0846

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ForgetPass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pass)


        var btn1 = findViewById<TextView>(R.id.buttonlogin)
        btn1.setOnClickListener {
            startActivity(
                Intent(this,
                    secondpage::class.java)
            );

        }

        var btn2 = findViewById<TextView>(R.id.buttonupload)
        btn2.setOnClickListener {
            startActivity(
                Intent(this,
                    Verification::class.java)
            );


            var btn2 = findViewById<TextView>(R.id.textViewForgotPassword)
            btn2.setOnClickListener {
                startActivity(
                    Intent(this,
                        call1::class.java)
                );

            }

        }
    }

}