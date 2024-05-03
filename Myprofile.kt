package com.example.i21_0846

import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.i21_0846.databinding.ActivityMyprofileBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.io.FileOutputStream

class Myprofile : AppCompatActivity() {

    private lateinit var binding:ActivityMyprofileBinding
    private lateinit var database:DatabaseReference

    companion object {
        private const val EDIT_PROFILE_REQUEST_CODE = 1
        private const val EXTRA_UPDATED_NAME = "extra_updated_name"
        private const val EXTRA_UPDATED_COUNTRY = "extra_updated_country"
    }


    // Handle the result from the Editprofile activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Inside the onActivityResult() function
        if (requestCode == EDIT_PROFILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val updatedName = data?.getStringExtra(EXTRA_UPDATED_NAME)
            val updatedCountry = data?.getStringExtra(EXTRA_UPDATED_COUNTRY)
            if (!updatedName.isNullOrEmpty() && !updatedCountry.isNullOrEmpty()) {
                // Update the UI with the updated data
                binding.editTextName.text = updatedName
                binding.editTextlocation.text = updatedCountry
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyprofileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val uname = intent.getStringExtra("username")


        readdata(uname)



        var btn1 = findViewById<TextView>(R.id.textview222)
        btn1.setOnClickListener {
            startActivity(
                Intent(this,
                    Bookedsession::class.java)
            );

        }

        var btn2 = findViewById<TextView>(R.id.edittextview)
        btn2.setOnClickListener {
            val intent = Intent(this, Editprofile::class.java )
            intent.putExtra("username", uname)
            startActivity(intent)

        }

        var btn3 = findViewById<TextView>(R.id.textview1)
        btn3.setOnClickListener {
            startActivity(
                Intent(this,
                    Home::class.java)
            );

        }

        var btn4 = findViewById<TextView>(R.id.textview2341)
        btn4.setOnClickListener {
            startActivity(
                Intent(this,
                    Home::class.java)
            );

        }

        var btn5 = findViewById<TextView>(R.id.textview2342)
        btn5.setOnClickListener {
            startActivity(
                Intent(this,
                    NewMentor::class.java)
            );

        }

        var btn6 = findViewById<TextView>(R.id.textview234)
        btn6.setOnClickListener {
            startActivity(
                Intent(this,
                    SearchResult::class.java)
            );

        }

        val imageView = findViewById<ImageView>(R.id.editpic)
        imageView.setOnClickListener {
            val intent = Intent(this, profilepic::class.java)
            intent.putExtra("username", uname)
            startActivity(intent)
        }

        // Launch the Editprofile activity with a request code
        btn2.setOnClickListener {
            val intent = Intent(this, Editprofile::class.java)
            intent.putExtra("username", uname)
            startActivityForResult(intent, EDIT_PROFILE_REQUEST_CODE)
        }

        retrieveImageFromDatabase(uname)



        /*binding.dp.setOnClickListener {
            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Fetching....")
            progressDialog.setCancelable(false)
            progressDialog.show()
            val storageRef = FirebaseStorage.getInstance().reference.child("Images/$uname.jpeg")

            val localFile = File.createTempFile("tempImage","jpeg")
            storageRef.getFile(localFile).addOnSuccessListener {
                // File download success

                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                binding.dp.setImageBitmap(bitmap)
                if(progressDialog.isShowing)
                    progressDialog.dismiss()
            }.addOnFailureListener { exception ->
                // File download failed
                if(progressDialog.isShowing)
                    progressDialog.dismiss()
                Log.e(TAG, "Error downloading file: ${exception.message}")
            }
        }*/

    }



    private fun readdata(uname: String?){
        database = FirebaseDatabase.getInstance().getReference("User")
        database.child(uname.toString()).get().addOnSuccessListener {

            if(it.exists()){
                val name = it.child("name").value
                val location = it.child("country").value
                binding.editTextName.text = name.toString()
                binding.editTextlocation.text = location.toString()

            }
        }
    }

    private fun isImageFileExists(): Boolean {
        val localFile = File.createTempFile("tempImage", "jpeg")
        return localFile.exists()
    }

    private fun retrieveImageFromDatabase(uname: String?) {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Fetching....")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val storageRef = FirebaseStorage.getInstance().reference.child("Images/$uname.jpeg")
        val localFile = File.createTempFile("tempImage", "jpeg")

        storageRef.getFile(localFile).addOnSuccessListener {
            // File download success
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            binding.dp.setImageBitmap(bitmap)
            if (progressDialog.isShowing)
                progressDialog.dismiss()
        }.addOnFailureListener { exception ->
            // File download failed
            if (progressDialog.isShowing)
                progressDialog.dismiss()
            Log.e(TAG, "Error downloading file: ${exception.message}")
        }
    }



}
