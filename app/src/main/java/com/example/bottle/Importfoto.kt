@file:Suppress("DEPRECATION")

package com.example.bottle

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast

class Importfoto : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var editTextName: EditText
    private lateinit var editTextLokasi: EditText
    private lateinit var editTextDate: EditText
    private lateinit var imageView: ImageView
    private var selectedImage: Bitmap? = null

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_importfoto)

        val btnkeluar: ImageButton = findViewById(R.id.btnkembali3)
        btnkeluar.setOnClickListener {
            val intent = Intent(this, menu::class.java)
            startActivity(intent)
        }

        dbHelper = DatabaseHelper(this)
        editTextName = findViewById(R.id.editTextName)
        editTextLokasi = findViewById(R.id.editTextLokasi)
        editTextDate = findViewById(R.id.editTextDate)
        imageView = findViewById(R.id.imageView)
        val buttonAddData = findViewById<Button>(R.id.buttonAdd)

        openImagePicker()
        buttonAddData.setOnClickListener {
            val merk = editTextName.text.toString().trim()
            val lokasi = editTextLokasi.text.toString().trim()
            val tanggal = editTextDate.text.toString().trim()
            if (merk.isNotEmpty() && lokasi.isNotEmpty() && tanggal.isNotEmpty() && selectedImage != null){
                val intent = Intent(this, Display::class.java)
                startActivity(intent)
                val insertedRowId = dbHelper.insertData(merk, lokasi, tanggal, selectedImage!!)
                if (insertedRowId != -1L) {
                    Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Gagal menambahkan Data", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, "Name and image are required", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK) {
            val imageUri = data?.data
            val selectedImageBitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            imageView.setImageBitmap(selectedImageBitmap)
            selectedImage = selectedImageBitmap
        }
    }
}