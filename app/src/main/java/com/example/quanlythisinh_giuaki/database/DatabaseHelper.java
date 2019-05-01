package com.example.quanlythisinh_giuaki.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "quanlythisinh_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Diem.CREATE_TABLE);
        db.execSQL(Khoa.CREATE_TABLE);
        db.execSQL(Mon.CREATE_TABLE);
        db.execSQL(Sinhvien.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Diem.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public List<Diem> getDiems(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("SELECT " + Sinhvien.COLUMN_HOTEN + ", " + Mon.COLUMN_TENMON
                        + ", " + Diem.COLUMN_DIEM
                        + " FROM " + Diem.TABLE_NAME + " INNER JOIN " + Mon.TABLE_NAME + " ON " +
                        Diem.COLUMN_MAMON + " = " + Mon.COLUMN_MAMON + Diem.TABLE_NAME + " INNER JOIN " + Sinhvien.TABLE_NAME + " ON " +
                        Diem.COLUMN_MAMON + " = " + Mon.COLUMN_MAMON + Diem.TABLE_NAME , new String[] {});

        if (result != null)

            result.close();
        return new ArrayList<Diem>();
    }
}