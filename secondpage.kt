package com.example.i21_0846

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.i21_0846.databinding.ActivitySecondpageBinding
import com.example.i21_0846.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class secondpage : AppCompatActivity() {

    private lateinit var binding: ActivitySecondpageBinding
    private lateinit var firebaseAuth: FirebaseAuth


    private fun saveUsernameToSharedPrefs(uname: String) {
        val sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        sharedPrefs.edit().putString("username", uname).apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.buttonSignup.setOnClickListener {
            val intent = Intent(this, signup::class.java)
            startActivity(intent)
        }

        binding.textViewForgotPassword.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ForgetPass::class.java
                )
            )
        }

        binding.button.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val pass = binding.editTextTextPassword.text.toString()
            val uname = binding.editTextTextun.text.toString()


            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    // Inside the signInWithEmailAndPassword() callback when the user is signed in successfully

                    if (task.isSuccessful) {
                        val intent = Intent(this, Home::class.java)
                        saveUsernameToSharedPrefs(uname)
                        startActivity(intent)
                    } else {
                        // User sign-in failed, handle the error or display a message
                        Toast.makeText(
                            this,
                            "Sign-in failed: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
            }


        }


    }
}

