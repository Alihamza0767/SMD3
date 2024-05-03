package com.example.i21_0846

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.i21_0846.databinding.ActivityBookedsessionBinding
import com.example.i21_0846.databinding.ActivityBookingBinding
import com.example.i21_0846.databinding.ActivityMentordescBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Booking : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val query = intent.getStringExtra("query")
        readData(query)

        /*




        var btn4 = findViewById<Button>(R.id.button2)
        btn4.setOnClickListener {
            val intent = Intent(this, Bookedsession::class.java )
            intent.putExtra("query", query)
            startActivity(intent)


        }
        */

        binding.button2.setOnClickListener{

            val date = binding.date.text.toString()
            val time = binding.time.text.toString()



            database = FirebaseDatabase.getInstance().getReference("Booking")
            val booking = MentorBooking(query,date,time)
            if (query != null) {
                database.child(query).setValue(booking).addOnSuccessListener {
                    binding.date.text.clear()
                    binding.time.text.clear()

                    Toast.makeText(this,"Successfully saved", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Bookedsession::class.java )
                    intent.putExtra("query", query)
                    startActivity(intent)

                }.addOnFailureListener{
                    Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()

                }
            }
        }

       /* var btn5 = findViewById<ImageView>(R.id.back)
        btn4.setOnClickListener {
            startActivity(
                Intent(this,
                    Mentordesc::class.java)
            );

        }*/
    }

    private fun readData(query: String?) {
        database = FirebaseDatabase.getInstance().getReference("Mentors")
        database.child(query.toString()).get().addOnSuccessListener { dataSnapshot ->

            if (dataSnapshot.exists()) {
                val mentor = dataSnapshot.getValue(Mentor::class.java)
                val name = mentor?.name

                // Display the status in the TextView
                binding.textView202.text = "$name"
                //binding.textview123.text = "$des"
                //binding.textview15.text = "$status"
            }
        }
    }
}