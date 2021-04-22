package com.example.recyclerfileoperation;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements StudentAdapter.ItemClickListener {

    private RecyclerView recyclerView;
//    List<String> AllStudentNames = new ArrayList<>(Arrays.asList("Rebecca","Moses","Dmello"));
//    List<Integer> AllImgView = new ArrayList<>(Arrays.asList(R.drawable.play, R.drawable.pause, R.drawable.ozark));


    List<Student> studentList = new ArrayList<>();
//    String stdName;
//    int stdId;
//    TextView txtStdName, txtStdDOB;

    Context context;

    @RequiresApi(api = Build.VERSION_CODES.O)
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

        studentList = ReadData();
//        AddData();


        StudentAdapter studentAdapter = new StudentAdapter(studentList, this);
        recyclerView.setAdapter(studentAdapter);

//        txtStdName = view.findViewById(R.id.txtStdName);
//        txtStdDOB = view.findViewById(R.id.txtStdDOB);
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
//    private void AddData(){
//        Log.d("Student", AllStudentNames.size()+"");
//        for(int i =0 ; i < AllStudentNames.size(); i++){
//            Student eachStudent = new Student(AllStudentNames.get(i), AllImgView.get(i));
//            studentList.add(eachStudent);
//        }
//    }

    @Override
    public void onItemClick(Student std) {
        String stdName = std.getStdName();
        Bundle bundle = new Bundle();
        bundle.putString("STUDENTNAME", stdName);
        NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<Student> ReadData(){
        List<Student> StudentList = new ArrayList<>();

        InputStream inputStream = getResources().openRawResource(R.raw.studentinfo);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String csvLine;
            //if you have header line,
            // you must read it first before any loop for parsing is set up
            String headerLine = reader.readLine();
            while((csvLine = reader.readLine()) != null){
                String[] row = csvLine.split(","); //comma separated String[]
                String id = row[0];
                String studentName = row[1]; //this is the drawable resource name
                String DOB = row[2];
                String stdImg = row[3];
//                int stdDraw =
                int stdDrawable = getResources().getIdentifier(stdImg, "drawable", getResources().getResourcePackageName(R.id.imgStd));
                

                //Log.d("D", DOB);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
                LocalDate dob = LocalDate.parse(DOB,formatter);

                Student eachStudent = new Student(studentName, id, dob, stdDrawable);
                StudentList.add(eachStudent);

            }
        } catch (IOException ex){
            Log.d("ADOPT",ex.getMessage());
        } catch (NumberFormatException ex){
            Log.d("ADOPT",ex.getMessage());
        } catch(DateTimeException ex){
            Log.d("ADOPT",ex.getMessage());
        } finally{
            //close the input stream and throw an exception if there is an issue
            try{
                inputStream.close();
            } catch (IOException ex){
                throw new RuntimeException("Error while closing stream...");
            }
        }


        return StudentList;
    }
}