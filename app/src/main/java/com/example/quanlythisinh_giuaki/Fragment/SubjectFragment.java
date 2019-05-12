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

import com.example.quanlythisinh_giuaki.Adapter.NhapdiemAdapter;
import com.example.quanlythisinh_giuaki.Adapter.StudentAdapter;
import com.example.quanlythisinh_giuaki.Adapter.SubjectAdapter;
import com.example.quanlythisinh_giuaki.R;
import com.example.quanlythisinh_giuaki.avtivity.AddSubject;
import com.example.quanlythisinh_giuaki.database.DatabaseHelper;
import com.example.quanlythisinh_giuaki.database.Department;
import com.example.quanlythisinh_giuaki.database.Student;
import com.example.quanlythisinh_giuaki.database.Subject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubjectFragment extends Fragment {

    private TextView tvDep;
    private Button btnAddSubject;
    private RecyclerView recyclerView;
    private SubjectAdapter mAdapter;
    List<Subject> subjects = new ArrayList<>();

    private DatabaseHelper mydb;

    public SubjectFragment() {
        // Required empty public constructor
    }

    public static SubjectFragment newInstance(String param1, String param2) {
        SubjectFragment fragment = new SubjectFragment();
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
        View view =  inflater.inflate(R.layout.subject_fragment, container, false);

        addControls(view);
        loadSubject();
        addEvents();
        return view;
    }


    public void addControls(View view){
        recyclerView = view.findViewById(R.id.subjectRecycleview);
        mAdapter = new SubjectAdapter(subjects);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();

        btnAddSubject = view.findViewById(R.id.btnAddSubject);

    }

    public void addEvents(){
        btnAddSubject.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSubject.start(getContext() );
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onResume(){
        super.onResume();
        loadSubject();
    }

    private void loadSubject(){
        mydb = new DatabaseHelper(getContext());
        subjects.clear();
        subjects.addAll(mydb.getSubjects());
        mAdapter.notifyDataSetChanged();
    }
}
