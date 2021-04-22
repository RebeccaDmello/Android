package com.example.recyclerinflatebasictemplate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstFragment extends Fragment implements StudentAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    List<String> AllStudentNames = new ArrayList<>(Arrays.asList("Rebecca","Moses","Dmello"));
    List<Integer> AllImgView = new ArrayList<>(Arrays.asList(R.drawable.play, R.drawable.pause, R.drawable.ozark));

    List<Student> studentList = new ArrayList<>();

    TextView txtStdName;

    Context context;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_first, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lm);

        AddData();

        StudentAdapter studentAdapter = new StudentAdapter(studentList, this);
        recyclerView.setAdapter(studentAdapter);

        txtStdName = view.findViewById(R.id.txtStdName);
        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });

    }
    private void AddData(){
        Log.d("Student", AllStudentNames.size()+"");
        for(int i =0 ; i < AllStudentNames.size(); i++){
            Student eachStudent = new Student(AllStudentNames.get(i), AllImgView.get(i));
            studentList.add(eachStudent);
        }
    }

    @Override
    public void onItemClick(Student std) {
        String stdName = std.getStdName();
        Bundle bundle = new Bundle();
        bundle.putString("STUDENTNAME", stdName);
        NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
    }
}