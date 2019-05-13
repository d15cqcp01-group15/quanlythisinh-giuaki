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
    private int makhoa;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_MASV + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_HOTEN + " TEXT, "
                    + COLUMN_PHAI + " TEXT, "
                    + COLUMN_NGAYSINH + " TEXT, "
                    + COLUMN_DIACHI + " TEXT, "
                    + COLUMN_MAKHOA + " INTEGER NOT NULL, "
                    + "FOREIGN KEY(makhoa) "
                    + "REFERENCES "+ Department.TABLE_NAME+"(makhoa)"
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

    public int getMakhoa() {
        return makhoa;
    }

    public void setMakhoa(int makhoa) {
        this.makhoa = makhoa;
    }

    public Student(String hoten, int makhoa) {
        this.hoten = hoten;
        this.makhoa = makhoa;
    }

    public Student(String hoten, String phai, String ngaysinh, String diachi, int makhoa) {
        this.masv = masv;
        this.hoten = hoten;
        this.phai = phai;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.makhoa = makhoa;
    }


}
