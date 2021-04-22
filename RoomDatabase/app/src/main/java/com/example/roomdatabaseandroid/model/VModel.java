package com.example.roomdatabaseandroid.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdatabaseandroid.databaseRoom.StudentRepo;
import com.example.roomdatabaseandroid.entity.Student;

import java.util.List;

public class VModel extends AndroidViewModel {

    private StudentRepo mRepository;

    private LiveData<List<Student>> mAllStudent;

    public VModel (Application application) {
        super(application);
        mRepository = new StudentRepo(application);
        mAllStudent = mRepository.getAllProducts();
    }

    public LiveData<List<Student>> getAllStudent() { return mAllStudent; }

    public void insert(Student product) { mRepository.insert(product); }
    public void update(Student product) { mRepository.update(product); }
    public void delete(Student product) { mRepository.delete(product); }
}
