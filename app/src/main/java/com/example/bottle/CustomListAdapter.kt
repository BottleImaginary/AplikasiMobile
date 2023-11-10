package com.example.bottle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomListAdapter(context: Context, private val data: List<DataModel>) : ArrayAdapter<DataModel>(context, R.layout.list_item, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.list_item, parent, false)

        val merkTextView = rowView.findViewById<TextView>(R.id.merkTextView)
        val lokasiTextView = rowView.findViewById<TextView>(R.id.lantaiTextView)
        val tanggalTextView = rowView.findViewById<TextView>(R.id.tanggalTextView2)
        val imageView = rowView.findViewById<ImageView>(R.id.imageView)

        val currentItem = data[position]
        merkTextView.text =  "Merk: ${currentItem.merk}"
        lokasiTextView.text = "Lantai: ${currentItem.loksai}"
        tanggalTextView.text = "Tanggal: ${currentItem.tanggal}"

        // Set the image bitmap to the ImageView
        imageView.setImageBitmap(currentItem.imageData)

        return rowView
    }
}