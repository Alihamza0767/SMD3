package com.example.i21_0846

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.i21_0846.databinding.ActivityHomeBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var database: DatabaseReference
    private lateinit var sharedPrefs: SharedPreferences


    private var uname: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Fetch the stored username from shared preferences
        uname = sharedPrefs.getString("username", "")
        if (uname.isNullOrEmpty()) {
            // Username not found in shared preferences, handle accordingly
        } else {
            // Username found in shared preferences, update the UI
            binding.textname.text = uname
            readData(uname)
        }


        var btn1 = findViewById<ImageView>(R.id.noti)
        btn1.setOnClickListener {
            startActivity(Intent(this, Notification::class.java))
        }

        var btn2 = findViewById<TextView>(R.id.textview233)
        btn2.setOnClickListener {
            val intent = Intent(this, Myprofile::class.java )
            intent.putExtra("username", uname)
            startActivity(intent)

        }

        var btn3 = findViewById<TextView>(R.id.textview234)
        btn3.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    Search::class.java
                )
            );

        }

        var btn4 = findViewById<TextView>(R.id.textview235)
        btn4.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    NewMentor::class.java
                )
            );

        }



    }


    override fun onResume() {
        super.onResume()

        // Update the text view with the latest username value
        binding.textname.text = uname
    }

    private fun readData(uname: String?) {
        database = FirebaseDatabase.getInstance().getReference("User")
        database.child(uname.toString()).get().addOnSuccessListener {
            if (it.exists()) {
                val name = it.child("name").value
                binding.textname.text = name.toString()
            }
        }
    }


}

