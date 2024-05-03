package com.example.i21_0846

import android.app.DownloadManager.Query
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.i21_0846.databinding.ActivityHomeBinding
import com.example.i21_0846.databinding.ActivitySearchBinding
import com.example.i21_0846.databinding.ActivitySearchResultBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SearchResult : AppCompatActivity() {
    private lateinit var binding: ActivitySearchResultBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val query = intent.getStringExtra("query")
        readData(query)


        val btn = findViewById<Button>(R.id.bookmen)
        btn.setOnClickListener {
            val intent = Intent(this, Mentordesc::class.java)
            intent.putExtra("query", query)
            startActivity(intent)
        }

        val btn1 = findViewById<Button>(R.id.bookedmen)
        btn1.setOnClickListener {
            val intent = Intent(this, Bookedsession::class.java)
            intent.putExtra("query", query)
            startActivity(intent)
        }

      /*  var btn2 = findViewById<TextView>(R.id.textview235)
        btn2.setOnClickListener {
            startActivity(
                Intent(this,
                    NewMentor::class.java)
            );

        }

        var btn1 = findViewById<TextView>(R.id.textview236)
        btn1.setOnClickListener {
            startActivity(
                Intent(this,
                    Home::class.java)
            );

        }

        var btn4 = findViewById<TextView>(R.id.textview11)
        btn4.setOnClickListener {
            startActivity(
                Intent(this,
                    Home::class.java)
            );

        }

        var btn5 = findViewById<TextView>(R.id.textview233)
        btn5.setOnClickListener {
            startActivity(
                Intent(this,
                    Myprofile::class.java)
            );

        }

       */


        var btn4 = findViewById<TextView>(R.id.textview11)
        btn4.setOnClickListener {
            startActivity(
                Intent(this,
                    Home::class.java)
            );

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
                binding.textview1.text = "$name"
                binding.textview123.text = "$des"
                binding.textview15.text = "$status"
            }
        }
    }


}