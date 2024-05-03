package com.example.i21_0846

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.i21_0846.databinding.ActivityEditprofileBinding
import com.example.i21_0846.databinding.ActivityNewMentorBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class NewMentor : AppCompatActivity() {
    private lateinit var binding: ActivityNewMentorBinding
    private lateinit var databse:DatabaseReference
    private lateinit var ImageUri : Uri


    private var picname: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewMentorBinding.inflate(layoutInflater)
        setContentView(binding.root)









        var btn1 = findViewById<TextView>(R.id.textview235)
        btn1.setOnClickListener {
            startActivity(
                Intent(this,
                    Home::class.java)
            );

        }

        var btn2 = findViewById<TextView>(R.id.textview234)
        btn2.setOnClickListener {
            startActivity(
                Intent(this,
                    SearchResult::class.java)
            );

        }

        var btn3 = findViewById<TextView>(R.id.textview233)
        btn3.setOnClickListener {
            startActivity(
                Intent(this,
                    Myprofile::class.java)
            );

        }

        var btn4 = findViewById<TextView>(R.id.text1)
        btn4.setOnClickListener {
            startActivity(
                Intent(this,
                    call2::class.java)
            );

        }

        var btn6 = findViewById<TextView>(R.id.textview)
        btn6.setOnClickListener {
            startActivity(
                Intent(this,
                    Home::class.java)
            );

        }

        var btn7 = findViewById<TextView>(R.id.buttonupload)
        btn7.setOnClickListener {
            startActivity(
                Intent(this,
                    Home::class.java)
            );

        }


        binding.buttonupload.setOnClickListener{
            val name = binding.editTextName.text.toString()
            val description = binding.editTextdescription.text.toString()
            val status = binding.editTextstatus.text.toString()
            val feild = binding.editTextfeild.text.toString()


            databse = FirebaseDatabase.getInstance().getReference("Mentors")
            val mentor = Mentor(name,description,status,feild)
            databse.child(name).setValue(mentor).addOnSuccessListener {
                binding.editTextName.text.clear()
                binding.editTextdescription.text.clear()
                binding.editTextstatus.text.clear()
                binding.editTextfeild.text.clear()

                Toast.makeText(this,"Successfully saved",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

            }

            ////////////////////////////////////////////////////

            val progressDialog = AlertDialog.Builder(this)
                .setMessage("Uploading File...")
                .setCancelable(false)
                .show()

            val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mmm_ss", Locale.getDefault())
            val now = Date()
            val filename = formatter.format(now)

            // Set the image name to the `uname` value
            val imageName = "$name.jpeg" // Assuming the image format is JPG

            val storageReference: StorageReference = FirebaseStorage.getInstance().reference.child("Mentor Images/$imageName")

            storageReference.putFile(ImageUri)
                .addOnSuccessListener {
                    binding.imageView.setImageURI(null)
                    Toast.makeText(this@NewMentor, "Successfully uploaded", Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this@NewMentor, "Failed to upload image", Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()
                }

        }

       // picname= binding.editTextName.text.toString()

        binding.imageView.setOnClickListener {

            selectImage()
        }

        //uploadImage()







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
        val imageName = "$picname.jpeg" // Assuming the image format is JPG

        val storageReference: StorageReference = FirebaseStorage.getInstance().reference.child("Mentor Images/$imageName")

        storageReference.putFile(ImageUri)
            .addOnSuccessListener {
                binding.imageView.setImageURI(null)
                Toast.makeText(this@NewMentor, "Successfully uploaded", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this@NewMentor, "Failed to upload image", Toast.LENGTH_SHORT).show()
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