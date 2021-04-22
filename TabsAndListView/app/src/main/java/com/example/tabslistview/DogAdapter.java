package com.example.tabslistview;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DogAdapter extends BaseAdapter {
    List<Dog> DogsList = new ArrayList<Dog>();
    ArrayAdapter<String> adapter ;
    DogAdapter dogAdapter;

    int CurrentPlayingInd = -1;
    Context context;

    public DogAdapter(List<Dog> DogsList) {
        this.DogsList = DogsList;
    }

    @Override
    public int getCount() {
        return DogsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return DogsList.get(i).getId();
    }

    public void ChangeData(List<Dog> newtuneList){
        this.DogsList = newtuneList;
        CurrentPlayingInd = -1;
        notifyDataSetChanged();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.layout_external_listitems,parent,false);
        }

        TextView txtViewId = convertView.findViewById(R.id.dogId);
        TextView txtViewName = convertView.findViewById(R.id.dogName);
        TextView txtViewBreed = convertView.findViewById(R.id.dogBreed);
        TextView txtViewDOB = convertView.findViewById(R.id.dogDOB);

        ImageView imgViewDogPic = convertView.findViewById(R.id.imgDog);
        imgViewDogPic.setImageResource(DogsList.get(position).getDogpicDrawable());
        txtViewId.setText(String.valueOf(DogsList.get(position).getId()));
        txtViewName.setText(String.valueOf(DogsList.get(position).getDogName()));
        txtViewBreed.setText(String.valueOf(DogsList.get(position).getDogBreed()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        txtViewDOB.setText(formatter.format(DogsList.get(position).getDob()));

        return convertView;
    }
}
