package com.example.quanlythisinh_giuaki.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class Score {

    public static final String TABLE_NAME = "diem";
    public static final String COLUMN_MASV = "masv";
    public static final String COLUMN_MAMON = "mamon";
    public static final String COLUMN_DIEM = "diem";

    private int masv;
    private String tenSv;
    private String tenMonhoc;
    private int mamon;
    private float diem;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_MASV + "INT,"
                    + COLUMN_MAMON + "INT,"
                    + COLUMN_DIEM + "FLOAT,"
                    + "PRIMARY KEY AUTOINCREMENT (" + COLUMN_MASV + "," + COLUMN_MAMON + ")"
                    + ")";

    public Score() {
    }

    public Score(int masv, int mamon, float diem) {
        this.mamon = mamon;
        this.masv = masv;
        this.diem = diem;
    }

    public int getMasv() {
        return masv;
    }

    public void setMasv(int masv) {
        this.masv = masv;
    }

    public int getMamon() {
        return mamon;
    }

    public void setMamon(int mamon) {
        this.mamon = mamon;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }

    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public String getTenMonhoc() {
        return tenMonhoc;
    }

    public void setTenMonhoc(String tenMonhoc) {
        this.tenMonhoc = tenMonhoc;
    }

}
