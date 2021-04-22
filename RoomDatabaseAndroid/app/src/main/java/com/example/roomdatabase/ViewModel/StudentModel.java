package com.example.roomdatabase.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.roomdatabase.Database.StudentRepo;
import com.example.roomdatabase.Entity.Student;
import java.util.List;
public class StudentModel extends AndroidViewModel {

    private StudentRepo srepo;
    private LiveData<List<Student>> AllStudent;
    public StudentModel(Application application) {
        super(application);
        srepo = new StudentRepo(application);
        AllStudent = srepo.getAllStudents();
    }
    public LiveData<List<Student>> getmAllStudent() { return AllStudent; }
    public void insert(Student student) { srepo.insert(student); }
    public void update(Student student) { srepo.update(student); }
    public void delete(Student student) { srepo.delete(student); }
}