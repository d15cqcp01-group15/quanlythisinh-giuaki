package com.example.quanlythisinh_giuaki.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.quanlythisinh_giuaki.Fragment.CustomSpinnerAdapter;
import com.example.quanlythisinh_giuaki.R;
import com.example.quanlythisinh_giuaki.database.DatabaseHelper;
import com.example.quanlythisinh_giuaki.database.Department;
import com.example.quanlythisinh_giuaki.database.Student;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class CreateNewStudent extends AppCompatActivity implements OnClickListener {

    private TextView tvDep, edtName, edtGender, edtBirthDay, edtAddress, edtPhoneNumber;
    private Spinner department;

    private int currDep_id = 0;
    private Button btnSaveStudent;

    private DatabaseHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_student);
        addControls();
        addEvents();
    }


    private void addControls(){
        edtAddress = findViewById(R.id.edtAddress);
        edtGender = findViewById(R.id.editGender);
        edtName = findViewById(R.id.edtName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtBirthDay = findViewById(R.id.edtBirthday);
        btnSaveStudent = findViewById(R.id.btnSaveStudent);

        mydb = new DatabaseHelper(getApplicationContext());
        tvDep= findViewById(R.id.tvDep);
        department = (Spinner) findViewById(R.id.cbDep);
        ArrayList<Department> deps = mydb.getDepartments();

        if(deps.size() != 0) {
            currDep_id = deps.get(0).getMakhoa();
            tvDep.setText(deps.get(0).toString());
            CustomSpinnerAdapter adapterDep = new CustomSpinnerAdapter(CreateNewStudent.this, deps, new CustomSpinnerAdapter.ISpinnerCallback<Department>() {
                @Override
                public void onItemClicked(Department dep) {
                    hideSpinner(department);
                    currDep_id = dep.getMakhoa();
                    tvDep.setText(dep.toString());
                }
            });
            department.setAdapter(adapterDep);
            adapterDep.notifyDataSetChanged();
        }
    }


    private void addEvents(){
        tvDep.setOnClickListener(this);
        btnSaveStudent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Student st = new Student();
                st.setHoten(edtName.getText().toString());
                st.setDiachi(edtAddress.getText().toString());
                st.setNgaysinh(edtBirthDay.getText().toString());
                st.setPhai(edtGender.getText().toString());
                st.setMakhoa(currDep_id);
                mydb.insertStudent(st);
                finish();
            }
        });
    }

    public static void start(Context context){
        Intent intent = new Intent(context, CreateNewStudent.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == tvDep){
            department.performClick();
        }
    }

    private void hideSpinner(Spinner sp){
        try {
            Method method = Spinner.class.getDeclaredMethod("onDetachedFromWindow");
            method.setAccessible(true);
            method.invoke(sp);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
