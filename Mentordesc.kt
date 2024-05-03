package com.example.i21_0846

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.i21_0846.databinding.ActivityMentordescBinding
import com.example.i21_0846.databinding.ActivitySearchResultBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Mentordesc : AppCompatActivity() {
    private lateinit var binding: ActivityMentordescBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMentordescBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val query = intent.getStringExtra("query")
        readData(query)

        val imageView = findViewById<ImageView>(R.id.backtohome)
        imageView.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        var btn2 = findViewById<Button>(R.id.booksession)
        btn2.setOnClickListener {
            val intent = Intent(this, Booking::class.java )
            intent.putExtra("query", query)
            startActivity(intent)


        }

    }



    private fun readData(query: String?) {
        database = FirebaseDatabase.getInstance().getReference("Mentors")
        database.child(query.toString()).get().addOnSuccessListener { dataSnapshot ->

            if (dataSnapshot.exists()) {
                val mentor = dataSnapshot.getValue(Mentor::class.java)
                val name = mentor?.name
                val des = mentor?.descr
                val status = mentor?.status

                // Display the status in the TextView
                binding.menname.text = "$name"
                //binding.textview123.text = "$des"
                //binding.textview15.text = "$status"
            }
        }
    }
}