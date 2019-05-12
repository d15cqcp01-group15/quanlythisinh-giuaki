package com.example.quanlythisinh_giuaki.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.quanlythisinh_giuaki.Adapter.NhapdiemAdapter;
import com.example.quanlythisinh_giuaki.R;
import com.example.quanlythisinh_giuaki.database.DatabaseHelper;
import com.example.quanlythisinh_giuaki.database.Department;
import com.example.quanlythisinh_giuaki.database.Score;
import com.example.quanlythisinh_giuaki.database.Student;
import com.example.quanlythisinh_giuaki.database.Subject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NhapdiemFragment extends Fragment implements OnClickListener {

    private TextView tvDep;
    private Spinner department;
    private int currentDep_id;

    private TextView tvSubject;
    private Spinner subjects;
    private int currentSub_id;

    private RecyclerView recyclerView;
    private NhapdiemAdapter mAdapter;
    HashMap<String, Score> scores = new HashMap<>();
    List<Department> deps = new ArrayList<>();
    List<Student> students = new ArrayList<>();
    List<Subject> subs = new ArrayList<>();

    private DatabaseHelper mydb;


    public NhapdiemFragment() {
        // Required empty public constructor
    }

    public static NhapdiemFragment newInstance(String param1, String param2) {
        NhapdiemFragment fragment = new NhapdiemFragment();
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
        return inflater.inflate(R.layout.review_list_point, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControls(view);
        addEvents();
    }

    private void addControls(View view){


        tvDep= view.findViewById(R.id.tvDep);
        department = (Spinner) view.findViewById(R.id.cbDep);
        deps = mydb.getDepartments();

        if(deps.size() == 0)
            return;

        currentDep_id = deps.get(0).getMakhoa();
        tvDep.setText(deps.get(0).toString());
        CustomSpinnerAdapter adapterDep = new CustomSpinnerAdapter(getActivity(), (ArrayList) deps, new CustomSpinnerAdapter.ISpinnerCallback<Department>(){
            @Override
            public void onItemClicked(Department dep) {
                hideSpinner(department);
                currentDep_id = dep.getMakhoa();
                tvDep.setText(dep.toString());
                loadStudent(dep.getMakhoa());
            }
        });

        department.setAdapter(adapterDep);
        adapterDep.notifyDataSetChanged();

        recyclerView = view.findViewById(R.id.score_recycleview);

//        mAdapter = new NhapdiemAdapter(students);
        recyclerView.setAdapter(mAdapter);
    }

    private void addEvents(){

    }

    private void loadStudent(int dep_id){
        List<Student> _students = mydb.getStudents(dep_id);
        students.clear();
        students.addAll(_students);
        mAdapter.notifyDataSetChanged();
    }

    private void loadSubs(){
        List<Subject> _subs = mydb.get();
        students.clear();
        students.addAll(_students);
        mAdapter.notifyDataSetChanged();
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
        else if (v == tvSubject){
            subjects.performClick();
        }
    }

    private void prepareScores(){
        loadStudent(currentDep_id);
        scores.clear();
        for(Student st: students){
            Score sc = new Score();
            sc.setTenSv(st.getHoten());
            sc.setDiem(-1);
            sc.setMamon(currentSub_id);
            sc.setTenMonhoc(tvSubject.getText().toString());
            scores.put(String.valueOf(sc.getMasv()) + String.valueOf(sc.getMamon()), sc);
        }


        ArrayList<Score> real_scores = mydb.getScores(currentSub_id);

        for(Score r_sc : real_scores){
            String key = String.valueOf(r_sc.getMasv()) + String.valueOf(r_sc.getMamon());
            if(scores.containsKey(key)){
                scores.put(key, r_sc);
            }
        }

    }

}
