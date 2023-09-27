package com.example.bottle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnkeluar: ImageButton = findViewById(R.id.btnkeluar)
        val btnambilgmbr: ImageButton = findViewById(R.id.btnambilgmbr)

        btnkeluar.setOnClickListener {
            val intent = Intent(this, depan::class.java)
            startActivity(intent)
        }

        btnambilgmbr.setOnClickListener {
            val intent = Intent(this, nyoba::class.java)
            startActivity(intent)
        }
    }
}