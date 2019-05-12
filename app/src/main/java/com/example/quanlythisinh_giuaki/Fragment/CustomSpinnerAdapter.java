package com.example.quanlythisinh_giuaki.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.quanlythisinh_giuaki.R;

import java.util.ArrayList;
import java.util.List;

public class CustomSpinnerAdapter<T> extends BaseAdapter {

    private ArrayList<T> mItems;
    private Context mContext;
    private ISpinnerCallback mCallback;

    public CustomSpinnerAdapter(Context context, ArrayList<T> items, ISpinnerCallback callback){
        this.mItems = items;
        this.mContext = context;
        this.mCallback = callback;
    }

    @Override
    public int getCount() {
        return mItems== null ?0 : mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_spinner, parent, false);

        TextView name = convertView.findViewById(R.id.tvName);
        View divider = convertView.findViewById(R.id.divider);
        name.setText(mItems.get(position).toString());

        if (position >= mItems.size() -1){
            divider.setVisibility(View.INVISIBLE);
        } else {
            divider.setVisibility(View.VISIBLE);
        }

        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onItemClicked(mItems.get(position));
            }
        });

        return convertView;
    }

    public interface ISpinnerCallback<T>{
        public void onItemClicked(T t);
    }
}
