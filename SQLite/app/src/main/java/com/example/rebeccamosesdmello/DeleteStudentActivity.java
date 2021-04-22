package com.example.rebeccamosesdmello;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteStudentActivity extends AppCompatActivity {

    private EditText delTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student);
    }

    public void deleteStudent(View view) {
        delTxt = findViewById(R.id.deleteText);
        String txt = delTxt.getText().toString();
        int delCnt = DatabaseHandler.deleteByName(txt);
        Toast.makeText(this, delCnt +" Records Deleted Successfully", Toast.LENGTH_SHORT).show();
        delTxt.setText("");
    }
}
