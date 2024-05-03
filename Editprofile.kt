package com.example.i21_0846

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import com.example.i21_0846.databinding.ActivityEditprofileBinding
import com.example.i21_0846.databinding.ActivityMyprofileBinding
import com.example.i21_0846.databinding.ActivitySecondpageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Editprofile : AppCompatActivity() {

    private lateinit var binding: ActivityEditprofileBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditprofileBinding.inflate(layoutInflater)
        setContentView(binding.root)





        var btn1 = findViewById<TextView>(R.id.textview)
        btn1.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    Myprofile::class.java
                )
            );
        }

        val uname = intent.getStringExtra("username")

        binding.buttonUpdate.setOnClickListener{
            updateUser(uname)
        }


    }




    private fun updateUser(username: String?) {

        val name = binding.editTextName.text.toString()
        val email = binding.editTextEmail.text.toString()
        val number = binding.editTextContactNumber.text.toString()
        val country = binding.editTextCountry.text.toString()

        val database = FirebaseDatabase.getInstance().getReference("User")


        val query = database.orderByChild("username").equalTo(username)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {
                    userSnapshot.ref.child("name").setValue(name)
                    userSnapshot.ref.child("email").setValue(email)
                    userSnapshot.ref.child("number").setValue(number)
                    userSnapshot.ref.child("country").setValue(country)
                }

                val EXTRA_UPDATED_NAME = "extra_updated_name"
                val EXTRA_UPDATED_COUNTRY = "extra_updated_country"
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_UPDATED_NAME, name)
                resultIntent.putExtra(EXTRA_UPDATED_COUNTRY, country)
                setResult(RESULT_OK, resultIntent)
                finish()


            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Failed to update user: ${databaseError.message}")
            }




        })


    }
}