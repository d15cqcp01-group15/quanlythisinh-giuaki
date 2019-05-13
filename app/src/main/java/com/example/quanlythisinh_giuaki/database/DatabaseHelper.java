package com.example.quanlythisinh_giuaki.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract.StatusUpdates;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "quanlythisinh.db";


    public
    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Student.CREATE_TABLE);
        db.execSQL(Score.CREATE_TABLE);
        db.execSQL(Department.CREATE_TABLE);
        db.execSQL(Subject.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        // Create tables again
        onCreate(db);
    }

    public ArrayList<Score> getDiems(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("SELECT " + Student.TABLE_NAME +"."+ Student.COLUMN_HOTEN + ", " + Subject.TABLE_NAME +"." + Subject.COLUMN_TENMON
                        + ", " +Score.TABLE_NAME +"."+Score.COLUMN_DIEM + ", " + Student.TABLE_NAME +"." + Student.COLUMN_MASV + ", " + Subject.TABLE_NAME +"."+Subject.COLUMN_MAMON
                        + " FROM " + Score.TABLE_NAME + " INNER JOIN " + Subject.TABLE_NAME + " ON " +
                        Score.TABLE_NAME +"."+Score.COLUMN_MAMON + " = " + Subject.TABLE_NAME +"."+Subject.COLUMN_MAMON + " and " + Score.TABLE_NAME + " INNER JOIN " + Student.TABLE_NAME + " ON " +
                        Score.TABLE_NAME +"."+Score.COLUMN_MASV + " = " + Student.TABLE_NAME +"."+Student.COLUMN_MASV , new String[] {});

        ArrayList scores = new ArrayList<>();
        result.moveToFirst();
        while(result.isAfterLast() == false){
            Score sc = new Score();
            sc.setMamon(result.getInt(result.getColumnIndex(Score.COLUMN_MAMON)));
            sc.setMasv(result.getInt(result.getColumnIndex(Score.COLUMN_MASV)));
            sc.setDiem(result.getFloat(result.getColumnIndex(Score.COLUMN_DIEM)));
            sc.setTenMonhoc(result.getString(result.getColumnIndex(Subject.COLUMN_TENMON)));
            sc.setTenSv(result.getString(result.getColumnIndex(Student.COLUMN_HOTEN)));
            scores.add(sc);
            result.moveToNext();
        }
        result.close();
        return scores;

    }

    public ArrayList<Department> getDepartments(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Department> deps = new ArrayList<Department>();
        Cursor result = db.rawQuery("SELECT * from " +  Department.TABLE_NAME, new String[] {});

        result.moveToFirst();
        while(result.isAfterLast() == false){
            Department dep = new Department();
            dep.setMakhoa(result.getInt(result.getColumnIndex(Department.COLUMN_MAKHOA)));
            dep.setTenkhoa(result.getString(result.getColumnIndex(Department.COLUMN_TENKHOA)));
            deps.add(dep);
            result.moveToNext();
        }
        result.close();
        return deps;
    }

    public List<Student> getStudents(int makhoa){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Student> students = new ArrayList<Student>();
        Cursor result = db.rawQuery("SELECT " + Student.COLUMN_HOTEN + ", " + Student.COLUMN_MASV
                + " FROM " + Student.TABLE_NAME
                + " WHERE " + Student.COLUMN_MAKHOA + " = " + String.valueOf(makhoa), new String[] {});

        result.moveToFirst();

        while(result.isAfterLast() == false){
            Student st = new Student();
            st.setHoten(result.getString(result.getColumnIndex(Student.COLUMN_HOTEN)));
            st.setMasv(result.getInt(result.getColumnIndex(Student.COLUMN_MASV)));
            st.setMakhoa(result.getInt(result.getColumnIndex(Student.COLUMN_MASV)));
            students.add(st);
            result.moveToNext();
        }
        result.close();
        return students;
    }

    public List<Subject> getSubjects(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        Cursor result = db.rawQuery("SELECT " + Subject.COLUMN_MAMON + ", " + Subject.COLUMN_TENMON + ", " + Subject.COLUMN_SOTIET
                + " FROM " + Subject.TABLE_NAME, new String[] {});
    

        result.moveToFirst();

        while(result.isAfterLast() == false){
            Subject sub = new Subject();
            sub.setMamon(result.getInt(result.getColumnIndex(Subject.COLUMN_MAMON)));
            sub.setTenmon(result.getString(result.getColumnIndex(Subject.COLUMN_TENMON)));
            sub.setSotiet(result.getInt(result.getColumnIndex(Subject.COLUMN_SOTIET)));
            subjects.add(sub);
            result.moveToNext();
        }
        result.close();
        return subjects;
    }

    public ArrayList<Score> getScores(int maMon){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Score> scores = new ArrayList<Score>();
        Cursor result = db.rawQuery("SELECT *"
                + " FROM " + Score.TABLE_NAME
                + " WHERE " + Score.COLUMN_MAMON + " = " + String.valueOf(maMon), new String[] {});



        result.moveToFirst();
        while(result.isAfterLast() == false){
            Score sc = new Score();
            sc.setMamon(result.getInt(result.getColumnIndex(Score.COLUMN_MAMON)));
            sc.setMasv(result.getInt(result.getColumnIndex(Score.COLUMN_MASV)));
            sc.setDiem(result.getFloat(result.getColumnIndex(Score.COLUMN_DIEM)));
            scores.add(sc);
            result.moveToNext();
        }
        result.close();
        return scores;
    }

    public void insertStudent(Student st){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Student.COLUMN_HOTEN, st.getHoten());
        values.put(Student.COLUMN_DIACHI, st.getDiachi());
        values.put(Student.COLUMN_NGAYSINH, st.getNgaysinh());
        values.put(Student.COLUMN_MAKHOA, st.getMakhoa());

        db.insert(Student.getTableName(), null, values);


        // Đóng kết nối database.
        db.close();
    }

    public void insertDep(Department dep){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Department.COLUMN_TENKHOA, dep.getTenkhoa());
        db.insert(Department.TABLE_NAME, null, values);
        // Đóng kết nối database.
        db.close();
    }

    public void insertScore(Score sc){
        SQLiteDatabase db = this.getWritableDatabase();

//        ContentValues values = new ContentValues();
//        values.put(Score.COLUMN_MASV, sc.getMasv());
//        values.put(Score.COLUMN_DIEM, sc.getDiem());
//        values.put(Score.COLUMN_MAMON, sc.getMamon());
//        long b = db.insert(Score.TABLE_NAME, null, values);
//        // Đóng kết nối database.
        String sql = "INSERT INTO " + Score.TABLE_NAME + "("+Score.COLUMN_MASV + ", " + Score.COLUMN_MAMON  + ", " + Score.COLUMN_DIEM +")";
        sql = sql + " VALUES("+ sc.getMasv() + ", " + sc.getMamon() + ", " + sc.getDiem() +");";
        db.execSQL(sql);
        db.close();
    }

    public void updateScore(Score sc){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + Score.TABLE_NAME + " SET " + Score.COLUMN_DIEM + " = " + String.valueOf(sc.getDiem());
        sql = sql + " where "+ Score.COLUMN_MASV + " = " + String.valueOf(sc.getMasv()) + " and " + Score.COLUMN_MAMON + " = " + String.valueOf(sc.getMamon()) ;
        db.execSQL(sql);
//        ContentValues values = new ContentValues();
//        values.put(Score.COLUMN_DIEM, sc.getDiem());
//        db.update(Score.TABLE_NAME, values, Student.COLUMN_MASV+"=?" + sc.getMasv() +" AND " +
//                Subject.COLUMN_MAMON + "=?", new String[]{String.valueOf(sc.getMasv()) , String.valueOf(sc.getMamon())});
//        // Đóng kết nối database.
        db.close();
    }

    public void insertSubject(Subject sub){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Subject.COLUMN_TENMON, sub.getTenmon());
        values.put(Subject.COLUMN_SOTIET, sub.getSotiet());
        db.insert(Subject.TABLE_NAME, null, values);
        // Đóng kết nối database.
        db.close();
    }

    public void deleteAllScore(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE diem");
        // Đóng kết nối database.
        db.close();
    }

    public void createTableDiem(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(Score.CREATE_TABLE);
        db.close();
    }

}