package com.example.i21_0846

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.i21_0846.databinding.ActivitySecondpageBinding
import com.example.i21_0846.databinding.ActivitySignupBinding
import com.example.i21_0846.secondpage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signup : AppCompatActivity() {
    lateinit var binding:ActivitySignupBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.buttonLogin.setOnClickListener{

            val intent = Intent(this, secondpage::class.java )
            startActivity(intent)
        }
        binding.buttonSignup.setOnClickListener{
            val name = binding.editTextName.text.toString()
            val username = binding.editTextusername.text.toString()
            val email = binding.editTextEmail.text.toString()
            val number = binding.editTextContactNumber.text.toString()
            val country = binding.editTextCountry.text.toString()
            val pass = binding.editTextPassword.text.toString()

            if(name.isNotEmpty() && username.isNotEmpty() && email.isNotEmpty() && number.isNotEmpty() && country.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this, secondpage::class.java )
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this , "Empty feilds are not allowed" , Toast.LENGTH_SHORT).show()
            }

            database = FirebaseDatabase.getInstance().getReference("User")
            val User = User(name,username,email,number,country)
            database.child(username).setValue(User).addOnSuccessListener {

                binding.editTextName.text.clear()
                binding.editTextEmail.text.clear()
                binding.editTextContactNumber.text.clear()
                binding.editTextCountry.text.clear()
                binding.editTextPassword.text.clear()
                binding.editTextusername.text.clear()

                Toast.makeText(this, "Successfully saved",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{
                Toast.makeText(this, "Failed",Toast.LENGTH_SHORT).show()

            }

        }

        //database.keepSynced(true)


    }



}