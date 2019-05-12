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


    public DatabaseHelper(Context context) {
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
        db.execSQL("DROP TABLE IF EXISTS " + Score.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public List<Score> getDiems(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("SELECT " + Student.COLUMN_HOTEN + ", " + Subject.COLUMN_TENMON
                        + ", " + Score.COLUMN_DIEM
                        + " FROM " + Score.TABLE_NAME + " INNER JOIN " + Subject.TABLE_NAME + " ON " +
                        Score.COLUMN_MAMON + " = " + Subject.COLUMN_MAMON + Score.TABLE_NAME + " INNER JOIN " + Student.TABLE_NAME + " ON " +
                        Score.COLUMN_MAMON + " = " + Subject.COLUMN_MAMON + Score.TABLE_NAME , new String[] {});

        if (result != null)

            result.close();
        return new ArrayList<Score>();
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

    public void insertSubject(Subject sub){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Subject.COLUMN_TENMON, sub.getTenmon());
        values.put(Subject.COLUMN_SOTIET, sub.getSotiet());
        db.insert(Subject.TABLE_NAME, null, values);
        // Đóng kết nối database.
        db.close();
    }

}