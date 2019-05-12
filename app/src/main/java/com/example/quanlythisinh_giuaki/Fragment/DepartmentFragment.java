package com.example.quanlythisinh_giuaki.Fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlythisinh_giuaki.Adapter.DepartmentAdapter;
import com.example.quanlythisinh_giuaki.Adapter.NhapdiemAdapter;
import com.example.quanlythisinh_giuaki.Adapter.StudentAdapter;
import com.example.quanlythisinh_giuaki.Adapter.SubjectAdapter;
import com.example.quanlythisinh_giuaki.R;
import com.example.quanlythisinh_giuaki.avtivity.AddDepartment;
import com.example.quanlythisinh_giuaki.avtivity.AddSubject;
import com.example.quanlythisinh_giuaki.database.DatabaseHelper;
import com.example.quanlythisinh_giuaki.database.Department;
import com.example.quanlythisinh_giuaki.database.Student;
import com.example.quanlythisinh_giuaki.database.Subject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepartmentFragment extends Fragment {

    private TextView tvDep;
    private Button btnAddDepartment;
    private RecyclerView recyclerView;
    private DepartmentAdapter mAdapter;
    List<Department> departments = new ArrayList<>();

    private DatabaseHelper mydb;

    public DepartmentFragment() {
        // Required empty public constructor
    }

    public static DepartmentFragment newInstance(String param1, String param2) {
        DepartmentFragment fragment = new DepartmentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.department_fragment, container, false);

        addControls(view);
        loadDepartment();
        addEvents();
        return view;
    }


    public void addControls(View view){
        recyclerView = view.findViewById(R.id.departmentRecycleview);
        mAdapter = new DepartmentAdapter(departments);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();

        btnAddDepartment = view.findViewById(R.id.btnAddDepartment);

    }

    public void addEvents(){
        btnAddDepartment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDepartment.start(getContext() );
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onResume(){
        super.onResume();
        loadDepartment();
    }

    private void loadDepartment(){
        mydb = new DatabaseHelper(getContext());
        departments.clear();
        departments.addAll(mydb.getDepartments());
        mAdapter.notifyDataSetChanged();
    }
}
