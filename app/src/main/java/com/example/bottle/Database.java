package com.example.bottle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class Database extends SQLiteOpenHelper {

    public static final String DB_NAME = "db_coba.db";
    public static final String TB_NAME = "tb_coba";
    public static final String COL_1 = "merk_botol";
    public static final String COL_2 = "tanggal";

    //buat constractor baru
    public Database(Context context){super(context,DB_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TB_NAME +"("+COL_1 + " text primary key, " + COL_2 + " date not null);";
        sqLiteDatabase.execSQL(sql);
        String query = "INSERT INTO " + TB_NAME + "("+COL_1+", "+COL_2+") VALUES ('Aqua', '29-10-2023')";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
