package com.example.i21_0846

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class chat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)


        var btn1 = findViewById<ImageView>(R.id.opencamera)
        btn1.setOnClickListener {
            startActivity(Intent(this, call1::class.java))
        }

        var btn2 = findViewById<ImageView>(R.id.callperson)
        btn1.setOnClickListener {
            startActivity(Intent(this, callpage3::class.java))
        }

        var btn3 = findViewById<ImageView>(R.id.videocallperson)
        btn1.setOnClickListener {
            startActivity(Intent(this, call4::class.java))
        }

        var btn4 = findViewById<ImageView>(R.id.backtoChatsFromChat)
        btn1.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }
    }
}

class call4 {

}
