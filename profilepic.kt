package com.example.i21_0846

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

import com.example.i21_0846.databinding.ActivityHomeBinding
import com.example.i21_0846.databinding.ActivityProfilepicBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class profilepic : AppCompatActivity() {
    private lateinit var binding: ActivityProfilepicBinding
    private lateinit var ImageUri : Uri


    private var uname: String? = null // Declare the `uname` variable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilepicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        uname = intent.getStringExtra("username")


        binding.button2.setOnClickListener {

            selectImage()
        }

        binding.button3.setOnClickListener {

            uploadImage()
        }


    }

    private fun uploadImage() {
        val progressDialog = AlertDialog.Builder(this)
            .setMessage("Uploading File...")
            .setCancelable(false)
            .show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mmm_ss", Locale.getDefault())
        val now = Date()
        val filename = formatter.format(now)

        // Set the image name to the `uname` value
        val imageName = "$uname.jpeg" // Assuming the image format is JPG

        val storageReference: StorageReference = FirebaseStorage.getInstance().reference.child("Images/$imageName")

        storageReference.putFile(ImageUri)
            .addOnSuccessListener {
                binding.imageView.setImageURI(null)
                Toast.makeText(this@profilepic, "Successfully uploaded", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this@profilepic, "Failed to upload image", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            }

    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100 && resultCode == RESULT_OK){
            ImageUri = data?.data!!
            binding.imageView.setImageURI(ImageUri)
        }

    }

}


