package com.example.bottle

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.recyclerview.widget.RecyclerView

class Display : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var imageView: ImageView
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val btnkeluar: ImageButton = findViewById(R.id.btnkembali3)
        btnkeluar.setOnClickListener {
            val intent = Intent(this, menu::class.java)
            startActivity(intent)
        }
        dbHelper = DatabaseHelper(this)
        listView = findViewById(R.id.listview)

        // Load and display data from SQLite
        loadListView()

    }
    private fun loadListView() {
        val data = dbHelper.readData()

        if (data.isNotEmpty()) {
            val adapter = CustomListAdapter(this, data)
            listView.adapter = adapter
        } else {
            // Handle case when there is no data to display
        }
    }
}