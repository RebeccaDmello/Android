package com.example.roomdatabase.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.roomdatabase.Adapter.EListener;
import com.example.roomdatabase.Adapter.StudentAdapter;
import com.example.roomdatabase.Entity.Student;
import com.example.roomdatabase.R;
import com.example.roomdatabase.Utils.ProStr;
import com.example.roomdatabase.ViewModel.StudentModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    public static final int NEW_CODE = 1;
    public static final int EDIT_CODE = 2;
    private StudentModel sModel;
    private Button edBtn, delBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final StudentAdapter adapter = new StudentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sModel = ViewModelProviders.of(this).get(StudentModel.class);
        sModel.getmAllStudent().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable final List<Student> students) {
                adapter.setStudents(students);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                startActivityForResult(intent, NEW_CODE);
            }
        });


        recyclerView.addOnItemTouchListener(
                new EListener(this, new EListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, final int position) {
                        edBtn = v.findViewById(R.id.btnEdit);
                        delBtn = v.findViewById(R.id.btnDel);
                        delBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sModel.delete(adapter.getStudentAt(position));
                            }
                        });

                        edBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                                intent.putExtra(ProStr.STUDENT_ID, adapter.getStudentAt(position).getStudentId());
                                intent.putExtra(ProStr.STUDENT_NAME, adapter.getStudentAt(position).getStudentName());
                                intent.putExtra(ProStr.STUDENT_DEPT, adapter.getStudentAt(position).getStudentDept());
                                intent.putExtra(ProStr.STUDENT_SID,  Integer.toString(adapter.getStudentAt(position).getSID()));
                                startActivityForResult(intent, EDIT_CODE);
                            }
                        });
                    }
                })
        );
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_CODE && resultCode == RESULT_OK) {
            String studentName = data.getStringExtra(ProStr.STUDENT_NAME);
            String studentDept = data.getStringExtra(ProStr.STUDENT_DEPT);
            String studentID = data.getStringExtra(ProStr.STUDENT_SID);
            int stdIDN = Integer.parseInt(studentID);
            Student student = new Student(studentName,studentDept, stdIDN);
            sModel.insert(student);
        } else if (requestCode == EDIT_CODE && resultCode == RESULT_OK) {
            int id = data.getIntExtra(ProStr.STUDENT_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Student info can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String studentName = data.getStringExtra(ProStr.STUDENT_NAME);
            String studentDept = data.getStringExtra(ProStr.STUDENT_DEPT);
            String stdID = data.getStringExtra(ProStr.STUDENT_SID);
            int stdN = Integer.parseInt(stdID);
            Student student = new Student(studentName,studentDept, stdN);
            student.setStudentId(id);
            sModel.update(student);
        } else {
            Toast.makeText(this, "Student info not saved", Toast.LENGTH_SHORT).show();
        } }
}
