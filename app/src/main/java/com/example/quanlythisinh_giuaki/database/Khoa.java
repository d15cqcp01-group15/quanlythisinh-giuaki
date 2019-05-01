package com.example.quanlythisinh_giuaki.database;

public class Khoa {

    public static final String TABLE_NAME = "khoa";
    public static final String COLUMN_MAKHOA = "id";
    public static final String COLUMN_TENKHOA = "tenkhoa";

    private String tenkhoa;
    private int makhoa;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_MAKHOA + " INTEGER PRIMARY KEY,"
                    + COLUMN_TENKHOA + " TEXT,"
                    + ")";

    public Khoa() {
    }

    public Khoa(int makhoa, String tenkhoa) {
        this.tenkhoa = tenkhoa;
        this.makhoa = makhoa;
    }

    public String getTenkhoa() {
        return tenkhoa;
    }

    public void setTenkhoa(String tenkhoa) {
        this.tenkhoa = tenkhoa;
    }

    public int getMakhoa() {
        return makhoa;
    }

    public void setMakhoa(int makhoa) {
        this.makhoa = makhoa;
    }
}
