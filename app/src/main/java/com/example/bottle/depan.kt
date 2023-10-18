package com.example.bottle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import kotlin.system.exitProcess

class depan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_depan)

        val button: Button = findViewById(R.id.btnmasuk)

        button.setOnClickListener {
            val intent = Intent(this, menu::class.java)
            startActivity(intent)
        }
        val exitButton = findViewById<ImageButton>(R.id.btnkelaurapk)
        exitButton.setOnClickListener {
            finish() // Menutup aktivitas dan keluar dari aplikasi
        }
    }
}