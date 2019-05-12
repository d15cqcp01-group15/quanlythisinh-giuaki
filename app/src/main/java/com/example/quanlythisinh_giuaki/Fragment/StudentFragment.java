package com.example.quanlythisinh_giuaki.Fragment;

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
import com.example.quanlythisinh_giuaki.R;
import com.example.quanlythisinh_giuaki.database.DatabaseHelper;
import com.example.quanlythisinh_giuaki.database.Department;
import com.example.quanlythisinh_giuaki.database.Student;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StudentFragment extends Fragment implements OnClickListener {

    private TextView tvDep;
    private Spinner department;

    private Button btnAddStudent;

    private RecyclerView recyclerView;
    private StudentAdapter mAdapter;
    List<Student> students = new ArrayList<>();

    private DatabaseHelper mydb;

    public StudentFragment() {
        // Required empty public constructor
    }

    public static StudentFragment newInstance(String param1, String param2) {
        StudentFragment fragment = new StudentFragment();
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
        View view =  inflater.inflate(R.layout.student_fragment, container, false);

        addControls(view);
        addEvents();
        loadStudent(1);
        return view;
    }

    public void addControls(View view){
        mydb = new DatabaseHelper(getContext());

        recyclerView = view.findViewById(R.id.studentRecycleview);
        mAdapter = new StudentAdapter(students);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();

        tvDep= view.findViewById(R.id.tvDep);
        department = (Spinner) view.findViewById(R.id.cbDep);
        ArrayList<Department> deps = mydb.getDepartments();

//        List<String> depNames = new ArrayList<>();
//        for(Department d :deps){
//            depNames.add(d.toString());
//        }
        tvDep.setText(deps.get(0).toString());
        CustomSpinnerAdapter adapterDep = new CustomSpinnerAdapter(getActivity(), deps, new CustomSpinnerAdapter.ISpinnerCallback<Department>(){
            @Override
            public void onItemClicked(Department dep) {
                hideSpinner(department);
                tvDep.setText(dep.toString());
                loadStudent(dep.getMakhoa());
            }
        });

        department.setAdapter(adapterDep);
        adapterDep.notifyDataSetChanged();

    }

    public void addEvents(){
        tvDep.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    @Override
    public void onClick(View v) {
        if (v == tvDep){
            department.performClick();
        }
    }

    private void loadStudent(int dep_id){
        List<Student> _students = mydb.getStudents(dep_id);
        students.clear();
        students.addAll(_students);
        mAdapter.notifyDataSetChanged();
    }


}
