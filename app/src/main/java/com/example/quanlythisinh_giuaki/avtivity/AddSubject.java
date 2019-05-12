package com.example.quanlythisinh_giuaki.avtivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quanlythisinh_giuaki.R;
import com.example.quanlythisinh_giuaki.database.DatabaseHelper;
import com.example.quanlythisinh_giuaki.database.Subject;

public class AddSubject extends AppCompatActivity {

    private Button btnSaveSubject;
    private TextView edtName,edtTiet;
    private DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        mydb = new DatabaseHelper(getApplicationContext());
        addControls();
        addEvent();
    }

    public static void start(Context context){
        Intent intent = new Intent(context, AddSubject.class);
        context.startActivity(intent);
    }

    public void addControls(){
        edtName = findViewById(R.id.edtName);
        edtTiet = findViewById(R.id.edtSoTiet);
    }

    public void addEvent(){
        Button btnSaveSubject = findViewById(R.id.btnSaveSubject);
        btnSaveSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subject sb = new Subject();
                sb.setSotiet(Integer.parseInt(edtTiet.getText().toString()));
                sb.setTenmon((edtName.getText()).toString());
                mydb.insertSubject(sb);
                finish();
            }
        });
    }
}
