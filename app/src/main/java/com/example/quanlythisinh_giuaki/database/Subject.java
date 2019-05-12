package com.example.quanlythisinh_giuaki.database;

public class Subject {

    public static final String TABLE_NAME = "mon";
    public static final String COLUMN_TENMON = "tenmon";
    public static final String COLUMN_MAMON = "mamon";
    public static final String COLUMN_SOTIET = "sotiet";

    private String tenmon;
    private int mamon;

    public static String getCreateTable() {
        return CREATE_TABLE;
    }

    private int sotiet;

    public Subject(String tenmon, int sotiet) {
        this.tenmon = tenmon;
        this.sotiet = sotiet;
    }

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_MAMON + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TENMON + " TEXT,"
                    + COLUMN_SOTIET + " INTEGER"
                    + ")";
    public Subject(){

    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public int getMamon() {
        return mamon;
    }

    public void setMamon(int mamon) {
        this.mamon = mamon;
    }

    public int getSotiet() {
        return sotiet;
    }

    public void setSotiet(int sotiet) {
        this.sotiet = sotiet;
    }
}
