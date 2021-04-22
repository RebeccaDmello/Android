package com.example.listinflatebasictemp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends BaseAdapter {

    List<Student> studentList = new ArrayList<>();

    public StudentAdapter(List<Student> studentList){
        this.studentList= studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.layout_external_item, parent, false);
        }
        TextView txtViewStdName = convertView.findViewById(R.id.txtViewStdName);
        txtViewStdName.setText(studentList.get(position).getStdName());

        TextView txtViewStdCourse = convertView.findViewById(R.id.txtViewStdCourse);
        txtViewStdCourse.setText(studentList.get(position).getStdCourse());

        return convertView;
    }
}
