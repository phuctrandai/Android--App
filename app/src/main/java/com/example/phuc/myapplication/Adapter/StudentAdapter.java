package com.example.phuc.myapplication.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.phuc.myapplication.Entity.Student;
import com.example.phuc.myapplication.R;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {
    private Activity acitivity;
    private ArrayList<Student> list;

    public StudentAdapter(Activity activity, ArrayList<Student> list) {
        this.acitivity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = acitivity.getLayoutInflater();
        convertView = inflater.inflate(R.layout.student_item, parent, false);

        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvName = convertView.findViewById(R.id.tvName);

        tvId.append(list.get(position).getId());
        tvName.append(list.get(position).getName());

        return convertView;
    }
}
