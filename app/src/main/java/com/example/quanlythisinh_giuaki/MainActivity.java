package com.example.quanlythisinh_giuaki;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.quanlythisinh_giuaki.Fragment.DepartmentFragment;
import com.example.quanlythisinh_giuaki.Fragment.NhapdiemFragment;
import com.example.quanlythisinh_giuaki.Fragment.StudentFragment;
import com.example.quanlythisinh_giuaki.Fragment.SubjectFragment;
import com.example.quanlythisinh_giuaki.database.DatabaseHelper;
import com.example.quanlythisinh_giuaki.database.Department;
import com.example.quanlythisinh_giuaki.database.Score;
import com.example.quanlythisinh_giuaki.database.Student;
import com.example.quanlythisinh_giuaki.database.Subject;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigation;
    private ViewPagerAdapter pagerAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_student:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_subject:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_department:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_score:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFrag(new StudentFragment());
        pagerAdapter.addFrag(new SubjectFragment());
        pagerAdapter.addFrag(new DepartmentFragment());
        pagerAdapter.addFrag(new NhapdiemFragment());
        viewPager.setAdapter(pagerAdapter);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        DatabaseHelper mydb = new DatabaseHelper(getBaseContext());
//        mydb.deleteAllScore();
//        mydb.createTableDiem();
        mydb.close();
    }

    public void fake_data(){
        DatabaseHelper mydb = new DatabaseHelper(getBaseContext());
        mydb.insertScore(new Score(2,1, 9));
//        mydb.insertSubject(new Subject("toan roi rac", 60));
//        mydb.insertSubject(new Subject("vat ly", 60));
//        mydb.insertSubject(new Subject("lap trinh di dong", 60));
//        mydb.insertSubject(new Subject("lap trinh may bay",60));
//
//        mydb.insertDep(new Department("Khoa dien tu"));
//        mydb.insertDep(new Department("Khoa cong nghe thong tin"));
//
//        mydb.insertStudent(new Student("Vu duc tai", 1));
//        mydb.insertStudent(new Student("Nguyen ha minh huy", 2));
//        mydb.insertStudent(new Student("Duong dinh hanh", 1));
//        mydb.insertStudent(new Student("Tran huu the", 2));
    }


}
