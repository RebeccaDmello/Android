package com.example.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    List<ColorSpec> ColorList = new ArrayList<>();

    public CustomAdapter(List<ColorSpec> colorList){
        ColorList = colorList;
    }

    @Override
    public int getCount() {
        return ColorList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_external_listview,
                    parent, false);
        }
        TextView txtViewItem = convertView.findViewById(R.id.txtViewItem);
        txtViewItem.setText(ColorList.get(position).getColorDesc());
        txtViewItem.setTextColor(ColorList.get(position).getColorVal());
        return convertView;
    }
}
