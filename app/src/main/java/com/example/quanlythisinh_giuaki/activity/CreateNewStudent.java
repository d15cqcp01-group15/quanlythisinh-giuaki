package com.example.quanlythisinh_giuaki.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.DatePickerDialog;
import java.util.Calendar;
import android.widget.DatePicker;

import com.example.quanlythisinh_giuaki.Fragment.CustomSpinnerAdapter;
import com.example.quanlythisinh_giuaki.R;
import com.example.quanlythisinh_giuaki.database.DatabaseHelper;
import com.example.quanlythisinh_giuaki.database.Department;
import com.example.quanlythisinh_giuaki.database.Student;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class CreateNewStudent extends AppCompatActivity implements OnClickListener {

    private TextView tvDep, edtName, edtGender, edtBirthDay, edtAddress, edtPhoneNumber;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Spinner department;
    DatePickerDialog picker;

    private int currDep_id = 0;
    private Button btnSaveStudent;

    private DatabaseHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_student);

        addControls();
        addEvents();
        handleDatepipicker(edtBirthDay);
    }

    private void handleDatepipicker(final TextView textview){
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(CreateNewStudent.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                textview.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    private void addControls(){
        edtAddress = findViewById(R.id.edtAddress);
        radioGroup = (RadioGroup) findViewById(R.id.radioGender);
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
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                Student st = new Student();
                st.setHoten(edtName.getText().toString());
                st.setDiachi(edtAddress.getText().toString());
                st.setNgaysinh(edtBirthDay.getText().toString());
                st.setPhai(radioButton.getText().toString());
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
