package com.example.i21_0846

import android.app.ProgressDialog
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import com.example.i21_0846.databinding.ActivityBookedsessionBinding
import com.example.i21_0846.databinding.ActivityBookingBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class Bookedsession : AppCompatActivity() {
    private lateinit var binding: ActivityBookedsessionBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookedsessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val query = intent.getStringExtra("query")
        readData(query)

        retrieveImageFromDatabase(query)



        var btn1 = findViewById<TextView>(R.id.textView3)
        btn1.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    Myprofile::class.java
                )
            );
        }
    }

    private fun retrieveImageFromDatabase(query: String?) {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Fetching....")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val storageRef = FirebaseStorage.getInstance().reference.child("Mentor Images/$query.jpeg")
        val localFile = File.createTempFile("tempImage", "jpeg")

        storageRef.getFile(localFile).addOnSuccessListener {
            // File download success
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            binding.imageView3.setImageBitmap(bitmap)
            if (progressDialog.isShowing)
                progressDialog.dismiss()
        }.addOnFailureListener { exception ->
            // File download failed
            if (progressDialog.isShowing)
                progressDialog.dismiss()
            Log.e(ContentValues.TAG, "Error downloading file: ${exception.message}")
        }
    }

    private fun readData(query: String?) {
        database = FirebaseDatabase.getInstance().getReference("Booking")
        database.child(query.toString()).get().addOnSuccessListener { dataSnapshot ->

            if (dataSnapshot.exists()) {
                val booking = dataSnapshot.getValue(MentorBooking::class.java)
                val name = booking?.name
                val date = booking?.date
                val time = booking?.time

                // Display the status in the TextView
                binding.textView4.text = "$name"
                binding.textView5.text = "$date"
                binding.textView6.text = "$time"
            }
        }
    }
}