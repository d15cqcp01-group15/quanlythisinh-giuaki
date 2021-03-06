package com.example.quanlythisinh_giuaki.database;

import java.util.Objects;

public class Department extends Object {

    public static final String TABLE_NAME = "khoa";
    public static final String COLUMN_MAKHOA = "makhoa";
    public static final String COLUMN_TENKHOA = "tenkhoa";

    private String tenkhoa;
    private int makhoa;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_MAKHOA + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TENKHOA + " TEXT"
                    + ")";

    public Department() {
    }

    public Department(String tenkhoa) {
        this.tenkhoa = tenkhoa;
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

    @Override
    public String toString() {
        return tenkhoa;
    }


}
