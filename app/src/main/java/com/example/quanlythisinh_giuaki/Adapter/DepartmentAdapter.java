package com.example.quanlythisinh_giuaki.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quanlythisinh_giuaki.ItemClickListener;
import com.example.quanlythisinh_giuaki.R;
import com.example.quanlythisinh_giuaki.database.Department;
import com.example.quanlythisinh_giuaki.database.Score;
import com.example.quanlythisinh_giuaki.database.Student;
import com.example.quanlythisinh_giuaki.database.Subject;

import java.util.List;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.MyViewHolder> {
    private List<Department> departments;
    private ItemClickListener callback;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView maKhoa, tenKhoa;


        public MyViewHolder(View view) {
            super(view);
            maKhoa = (TextView) view.findViewById(R.id.txtMakhoa);
            tenKhoa = (TextView) view.findViewById(R.id.txtKhoa);

        }

    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.callback = itemClickListener;
    }

    public DepartmentAdapter(List<Department> departments) {
        this.departments = departments;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.department_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Department db = departments.get(position);
        holder.tenKhoa.setText(db.getTenkhoa());
        holder.maKhoa.setText(String.valueOf(db.getMakhoa()));
//        holder.itemView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callback.onClick(position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return this.departments.size();
    }

}
