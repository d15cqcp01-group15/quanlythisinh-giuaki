package com.example.quanlythisinh_giuaki.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.quanlythisinh_giuaki.Adapter.NhapdiemAdapter;
import com.example.quanlythisinh_giuaki.R;
import com.example.quanlythisinh_giuaki.database.Score;

import java.util.ArrayList;
import java.util.List;

public class NhapdiemFragment extends Fragment {

    private TextView tvKhoa;
    private Spinner city;

    private TextView tvMon;
    private Spinner district;

    private RecyclerView recyclerView;
    private NhapdiemAdapter mAdapter;
    List<Score> scores = new ArrayList<>();

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
        return inflater.inflate(R.layout.fragment_nhapdiem, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
