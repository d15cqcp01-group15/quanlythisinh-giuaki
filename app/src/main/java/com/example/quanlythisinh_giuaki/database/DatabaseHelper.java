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
        db.execSQL(Score.CREATE_TABLE);
        db.execSQL(Department.CREATE_TABLE);
        db.execSQL(Subject.CREATE_TABLE);
        db.execSQL(Sinhvien.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Score.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public List<Score> getDiems(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("SELECT " + Sinhvien.COLUMN_HOTEN + ", " + Subject.COLUMN_TENMON
                        + ", " + Score.COLUMN_DIEM
                        + " FROM " + Score.TABLE_NAME + " INNER JOIN " + Subject.TABLE_NAME + " ON " +
                        Score.COLUMN_MAMON + " = " + Subject.COLUMN_MAMON + Score.TABLE_NAME + " INNER JOIN " + Sinhvien.TABLE_NAME + " ON " +
                        Score.COLUMN_MAMON + " = " + Subject.COLUMN_MAMON + Score.TABLE_NAME , new String[] {});

        if (result != null)

            result.close();
        return new ArrayList<Score>();
    }
}