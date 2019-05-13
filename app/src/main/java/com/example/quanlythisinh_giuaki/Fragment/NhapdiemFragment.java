package com.example.quanlythisinh_giuaki.Fragment;

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
import com.example.quanlythisinh_giuaki.EditScoreListener;
import com.example.quanlythisinh_giuaki.ItemClickListener;
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

    private Button btnSave;

    private RecyclerView recyclerView;
    private NhapdiemAdapter mAdapter;
    HashMap<String, Score> hash_scores = new HashMap<>();
    ArrayList<Score> scores = new ArrayList<>();
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

        mydb = new DatabaseHelper(getContext());

        loadSubs();
        loadDeps();

        tvDep= view.findViewById(R.id.tvDep);
        department = (Spinner) view.findViewById(R.id.cbDep);
        tvSubject= view.findViewById(R.id.tvSubject);
        subjects = (Spinner) view.findViewById(R.id.cbSub);
        btnSave = view.findViewById(R.id.btnSave);

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
                prepareScores();
            }
        });

        currentSub_id = subs.get(0).getMamon();
        tvSubject.setText(subs.get(0).toString());
        CustomSpinnerAdapter adapterSub = new CustomSpinnerAdapter(getActivity(), (ArrayList) subs, new CustomSpinnerAdapter.ISpinnerCallback<Subject>(){
            @Override
            public void onItemClicked(Subject sub) {
                hideSpinner(subjects);
                currentSub_id = sub.getMamon();
                tvSubject.setText(sub.toString());
                prepareScores();
            }
        });


        department.setAdapter(adapterDep);
        adapterDep.notifyDataSetChanged();

        subjects.setAdapter(adapterSub);
        adapterSub.notifyDataSetChanged();
        recyclerView = view.findViewById(R.id.score_recycleview);
        mAdapter = new NhapdiemAdapter(scores);
        mAdapter.setItemClickListener(new EditScoreListener() {
            @Override
            public void onClick(String score_id, float score) {
                Score sc = hash_scores.get(score_id);
                sc.setDiem(score);
                sc.setInsert(true);
                hash_scores.put(score_id, sc);
            }
        });
        recyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        prepareScores();

    }

    private void addEvents(){
        tvDep.setOnClickListener(this);
        tvSubject.setOnClickListener(this);

        btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean has_change = false;
                for(Score sc: scores){
                    if(sc.getDiem() != -1) {
                        if (sc.isHas_data() && sc.isInsert()) {
                            mydb.updateScore(sc);
                            has_change = true;
                        }
                        else if(sc.isInsert() && !sc.isHas_data()){
                            mydb.insertScore(sc);
                            has_change = true;
                        }
                    }
                }
                if(has_change) {
                    Toast.makeText(getContext(), "Lưu thành cônng!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loadStudent(int dep_id){
        List<Student> _students = mydb.getStudents(dep_id);
        students.clear();
        students.addAll(_students);
    }

    private void loadSubs(){
        List<Subject> _subs = mydb.getSubjects();
        subs.clear();
        subs.addAll(_subs);
    }

    private void loadDeps(){
        List<Department> _deps = mydb.getDepartments();
        deps.clear();
        deps.addAll(_deps);
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
        hash_scores.clear();
        for(Student st: students){
            Score sc = new Score();
            sc.setTenSv(st.getHoten());
            sc.setMasv(st.getMasv());
            sc.setDiem(-1);
            sc.setMamon(currentSub_id);
            sc.setTenMonhoc(tvSubject.getText().toString());
            hash_scores.put(String.valueOf(sc.getMasv()) + String.valueOf(sc.getMamon()), sc);
        }
        
        ArrayList<Score> real_hash_scores = mydb.getDiems();

        for(Score r_sc : real_hash_scores){
            String key = String.valueOf(r_sc.getMasv()) + String.valueOf(r_sc.getMamon());
            if(hash_scores.containsKey(key)){
                r_sc.setHas_data(true);
                hash_scores.put(key, r_sc);

            }
        }
        scores.clear();

        for(Score sc: hash_scores.values()){
            scores.add(sc);
        }
        mAdapter.notifyDataSetChanged();
    }

}
