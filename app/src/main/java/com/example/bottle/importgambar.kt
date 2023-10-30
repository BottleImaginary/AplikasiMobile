@file:Suppress("DEPRECATION")

package com.example.bottle

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView


class importgambar : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 1
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_importgambar)

        val btnkeluar: ImageButton = findViewById(R.id.btnkembali)
        btnkeluar.setOnClickListener {
            val intent = Intent(this, menu::class.java)
            startActivity(intent)
        }
        imageView = findViewById(R.id.hasilimport)

        val pickImage = Intent(Intent.ACTION_PICK)
        pickImage.type = "image/*"
        startActivityForResult(pickImage, 1)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            // Gambar telah dipilih
            val selectedImageUri = data?.data

            if (selectedImageUri != null) {
                imageView.setImageURI(selectedImageUri)
            }
        }
    }
}