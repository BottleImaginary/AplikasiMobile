package com.example.bottle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnkeluar: ImageButton = findViewById(R.id.btnkembali3)
        val btnambilgmbr: ImageButton = findViewById(R.id.btnambilgmbr)
        val btnimportgmbr: ImageButton = findViewById(R.id.btnimportgmbr)
        val btndisplaygmbr: ImageButton = findViewById(R.id.btn_display)
        btnkeluar.setOnClickListener {
            val intent = Intent(this, depan::class.java)
            startActivity(intent)
        }

        btnambilgmbr.setOnClickListener {
            val intent = Intent(this, ambilgambar::class.java)
            startActivity(intent)
        }
        btnimportgmbr.setOnClickListener {
            val intent = Intent(this, Importfoto::class.java)
            startActivity(intent)
        }
        btndisplaygmbr.setOnClickListener {
            val intent = Intent(this, Display::class.java)
            startActivity(intent)
        }
    }
}