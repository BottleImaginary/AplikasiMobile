package com.example.bottle

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "MyDatabase"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "MyTable"
        const val COLUMN_ID = "id"
        const val COLUMN_MERK = "merk_botol"
        const val COLUMN_LOKASI = "lokasi_lantai"
        const val COLUMN_TANGGAL = "tanggal"
        const val COLUMN_IMAGE = "image_data"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," + "$COLUMN_MERK TEXT," + "$COLUMN_LOKASI INTEGER," + "$COLUMN_TANGGAL DATE," + "$COLUMN_IMAGE BLOB" +
                    ")"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun insertData(merk: String, lokasi: String, tanggal: String, image: Bitmap): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_MERK, merk)
        contentValues.put(COLUMN_LOKASI, lokasi)
        contentValues.put(COLUMN_TANGGAL, tanggal)

        val byteArrayOutputStream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val imageInBytes = byteArrayOutputStream.toByteArray()
        contentValues.put(COLUMN_IMAGE, imageInBytes)

        return db.insert(TABLE_NAME, null, contentValues)
    }

    fun readData(): ArrayList<DataModel> {
        val data = ArrayList<DataModel>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor: Cursor = db.rawQuery(query, null)

        try {
            if (cursor.moveToFirst()) {
                do {
                    val idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID)
                    val merkIndex = cursor.getColumnIndexOrThrow(COLUMN_MERK)
                    val lokasiIndex = cursor.getColumnIndexOrThrow(COLUMN_LOKASI)
                    val tanggalIndex = cursor.getColumnIndexOrThrow(COLUMN_TANGGAL)
                    val imageIndex = cursor.getColumnIndexOrThrow(COLUMN_IMAGE)

                    val id = cursor.getInt(idIndex)
                    val merk = cursor.getString(merkIndex)
                    val lokasi = cursor.getString(lokasiIndex)
                    val tanggal = cursor.getString(tanggalIndex)
                    val imageInBytes = cursor.getBlob(imageIndex)

                    // Convert byte array to Bitmap
                    val imageBitmap = BitmapFactory.decodeByteArray(imageInBytes, 0, imageInBytes.size)

                    data.add(DataModel(id, merk, lokasi, tanggal, imageBitmap))
                } while (cursor.moveToNext())
            }
        } catch (e: SQLException) {
            // Handle exception
            e.printStackTrace()
        } finally {
            cursor.close()
        }

        return data
    }

}
