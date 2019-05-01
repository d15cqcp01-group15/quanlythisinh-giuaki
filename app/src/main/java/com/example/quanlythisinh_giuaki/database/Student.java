package com.example.quanlythisinh_giuaki.database;

public class Student {

    public static final String TABLE_NAME = "sinhvien";
    public static final String COLUMN_MASV = "masv";
    public static final String COLUMN_HOTEN = "hoten";
    public static final String COLUMN_PHAI = "phai";
    public static final String COLUMN_NGAYSINH = "ngaysinh";
    public static final String COLUMN_DIACHI = "diachi";
    public static final String COLUMN_MAKHOA = "makhoa";

    private int masv;
    private String hoten;
    private String phai ;
    private String ngaysinh ;
    private String diachi;
    private String makhoa;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_MASV + " INTEGER PRIMARY KEY,"
                    + COLUMN_HOTEN + " TEXT,"
                    + COLUMN_PHAI + " TEXT,"
                    + COLUMN_NGAYSINH + " TEXT,"
                    + COLUMN_DIACHI + " TEXT,"
                    + "CONSTRAINT fk_khoa,"
                    + "FOREIGN KEY (makhoa),"
                    + "REFERENCES khoa(makhoa),"
                    + ")";

    public Student() {
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public int getMasv() {
        return masv;
    }

    public void setMasv(int masv) {
        this.masv = masv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
        this.phai = phai;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getMakhoa() {
        return makhoa;
    }

    public void setMakhoa(String makhoa) {
        this.makhoa = makhoa;
    }
}
