package com.example.quanlythisinh_giuaki.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quanlythisinh_giuaki.EditScoreListener;
import com.example.quanlythisinh_giuaki.ItemClickListener;
import com.example.quanlythisinh_giuaki.R;
import com.example.quanlythisinh_giuaki.database.Score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NhapdiemAdapter extends RecyclerView.Adapter<NhapdiemAdapter.MyViewHolder> {
    private ArrayList<Score> scores = new ArrayList<>();

    private EditScoreListener edit_score_callback;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tenSv, tenMonhoc, stt;
        public EditText diem;


        public MyViewHolder(View view) {
            super(view);
            tenSv = (TextView) view.findViewById(R.id.txtTen);
            stt = view.findViewById(R.id.txtStt);
            tenMonhoc = (TextView) view.findViewById(R.id.txtMon);
            diem = (EditText) view.findViewById(R.id.txtDiem);
        }

    }

    public void setItemClickListener(EditScoreListener editScoreListener)
    {
        this.edit_score_callback = editScoreListener;
    }

    public NhapdiemAdapter(ArrayList<Score> scores) {
        this.scores = scores;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diem_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Score sc = scores.get(position);
        holder.tenSv.setText(sc.getTenSv());
        holder.tenMonhoc.setText(sc.getTenMonhoc());
        if (sc.getDiem() != -1){
            holder.diem.setText(Float.toString(sc.getDiem()));
        }
        else{
            holder.diem.setText("");
        }
        holder.stt.setText(String.valueOf(position));
        holder.diem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                float diem = -1;
                if(!holder.diem.getText().toString().equals("")){
                    diem = Float.parseFloat(holder.diem.getText().toString());
                }
                edit_score_callback.onClick(String.valueOf(scores.get(position).getMasv()) +
                        String.valueOf(scores.get(position).getMamon()), diem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.scores.size();
    }


}
