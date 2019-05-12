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
import com.example.quanlythisinh_giuaki.database.Student;
import com.example.quanlythisinh_giuaki.database.Subject;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder> {
    private List<Subject> subjects;
    private ItemClickListener callback;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView maMon, tenMon, sTiet;


        public MyViewHolder(View view) {
            super(view);
            maMon = (TextView) view.findViewById(R.id.txtMamon);
            tenMon = (TextView) view.findViewById(R.id.txtMon);
            sTiet = (TextView) view.findViewById(R.id.txtTiet);
        }

    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.callback = itemClickListener;
    }

    public SubjectAdapter(List<Subject> subjects) {
        this.subjects = subjects;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Subject sb = subjects.get(position);
        holder.tenMon.setText(sb.getTenmon());
        holder.sTiet.setText(String.valueOf(sb.getSotiet()));
        holder.maMon.setText(String.valueOf(sb.getMamon()));
//        holder.itemView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callback.onClick(position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return this.subjects.size();
    }

}
