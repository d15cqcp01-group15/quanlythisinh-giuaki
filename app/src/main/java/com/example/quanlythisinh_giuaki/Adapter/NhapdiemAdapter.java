package com.example.quanlythisinh_giuaki.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quanlythisinh_giuaki.ItemClickListener;
import com.example.quanlythisinh_giuaki.R;
import com.example.quanlythisinh_giuaki.database.Score;

import java.util.List;

public class NhapdiemAdapter extends RecyclerView.Adapter<NhapdiemAdapter.MyViewHolder> {
    private List<Score> scores;
    private ItemClickListener callback;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tenSv, tenMonhoc, diem, stt;


        public MyViewHolder(View view) {
            super(view);
            tenSv = (TextView) view.findViewById(R.id.txtTen);
            tenMonhoc = (TextView) view.findViewById(R.id.txtMon);
            diem = (TextView) view.findViewById(R.id.txtDiem);
        }

    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.callback = itemClickListener;
    }

    public NhapdiemAdapter(List<Score> scores) {
        this.scores = scores;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diem_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Score cl = scores.get(position);
        holder.tenSv.setText(cl.getTenSv());
        holder.tenMonhoc.setText(cl.getTenMonhoc());
        if (cl.getDiem() != -1){
            holder.diem.setText(Float.toString(cl.getDiem()));
        }
        holder.stt.setText(position);
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.scores.size();
    }


}
