package com.example.recyclerfileoperation;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class StudentSpecViewModel extends ViewModel {

    private MutableLiveData<List<Student>> StudentList = new MutableLiveData<>();

    public LiveData<List<Student>> getStudents(){
        if(StudentList == null){
            StudentList = new MutableLiveData<>();
        }
        return StudentList;
    }

    public void loadStudents(List<Student> anyList){
        Handler handler = new Handler();
        handler.postDelayed(()->{
            StudentList.setValue(anyList);
        },1000);
    }
}
