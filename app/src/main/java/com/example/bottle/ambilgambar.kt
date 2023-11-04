@file:Suppress("DEPRECATION")

package com.example.bottle

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class ambilgambar : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var editTextName: EditText
    private lateinit var editTextLokasi: EditText
    private lateinit var editTextDate: EditText
    private val REQUEST_IMAGE_CAPTURE = 1
    private val CAMERA_PERMISSION_REQUEST_CODE = 2
    private lateinit var imageView: ImageView
    private var selectedImage: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ambilgambar)

        val btnkeluar: ImageButton = findViewById(R.id.btnkembali3)
        btnkeluar.setOnClickListener {
            val intent = Intent(this, menu::class.java)
            startActivity(intent)
        }
        dbHelper = DatabaseHelper(this)
        editTextName = findViewById(R.id.editTextName2)
        editTextLokasi = findViewById(R.id.editTextLokasi2)
        editTextDate = findViewById(R.id.editTextDate2)
        imageView = findViewById(R.id.hasilfoto)


        // Memeriksa izin kamera

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Meminta izin kamera jika belum diberikan
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        } else {
            // Izin kamera sudah diberikan, langsung ambil foto

            takePhoto()
            val buttonlabelfoto = findViewById<Button>(R.id.btnlabelfoto)

            buttonlabelfoto.setOnClickListener {
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
                    Toast.makeText(this, "Data dan Gambar diperlukan!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun takePhoto() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Izin kamera diberikan, ambil foto
                takePhoto()
            } else {
                // Izin kamera tidak diberikan, berikan penanganan sesuai kebutuhan Anda
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
            selectedImage = imageBitmap
        }
    }
}