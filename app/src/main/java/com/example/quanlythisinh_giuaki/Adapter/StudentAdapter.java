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

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    private List<Student> students;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tenSv, masv, stt;


        public MyViewHolder(View view) {
            super(view);
            tenSv = (TextView) view.findViewById(R.id.txtName);
            masv = (TextView) view.findViewById(R.id.txtMasv);
            stt = (TextView) view.findViewById(R.id.txtStt);
        }

    }

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Student st = students.get(position);
        holder.tenSv.setText(st.getHoten());
        holder.masv.setText(String.valueOf(st.getMasv()));
        holder.stt.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return this.students.size();
    }

}
