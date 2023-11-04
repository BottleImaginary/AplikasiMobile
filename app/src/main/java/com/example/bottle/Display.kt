package com.example.bottle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Display : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val btnkeluar: ImageButton = findViewById(R.id.btnkembali3)
        btnkeluar.setOnClickListener {
            val intent = Intent(this, menu::class.java)
            startActivity(intent)
        }
    }
}