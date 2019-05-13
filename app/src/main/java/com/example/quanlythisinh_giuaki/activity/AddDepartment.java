package com.example.quanlythisinh_giuaki.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quanlythisinh_giuaki.R;
import com.example.quanlythisinh_giuaki.database.DatabaseHelper;
import com.example.quanlythisinh_giuaki.database.Department;
import com.example.quanlythisinh_giuaki.database.Subject;


public class AddDepartment extends AppCompatActivity {

    private Button btnSaveDepartment;
    private TextView edtName;
    private DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);
        mydb = new DatabaseHelper(getApplicationContext());
        addControls();
        addEvent();
    }

    public static void start(Context context){
        Intent intent = new Intent(context, AddDepartment.class);
        context.startActivity(intent);
    }

    public void addControls(){
        edtName = findViewById(R.id.edtName);
    }

    public void addEvent(){
        Button btnSaveDepartment = findViewById(R.id.btnSaveDepartment);
        btnSaveDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Department dp = new Department();
                dp.setTenkhoa((edtName.getText()).toString());
                mydb.insertDep(dp);
                finish();
            }
        });
    }
}

