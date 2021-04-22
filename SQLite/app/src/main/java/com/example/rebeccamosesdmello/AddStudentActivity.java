package com.example.rebeccamosesdmello;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {
    private EditText nameedTxt;
    private EditText stdIdedTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
    }

    public void addRecords(View view) {
        nameedTxt = findViewById(R.id.nameEditText);
        stdIdedTxt = findViewById(R.id.stdIdEditText);

        String name = nameedTxt.getText().toString();
        String stdId = stdIdedTxt.getText().toString();
        addData(name, stdId);
        nameedTxt.setText("");
        stdIdedTxt.setText("");
    }


    private void addData(String name, String mobile) {
        DatabaseHandler.addData(name, mobile);
        Toast.makeText(this, "Record Inserted Successfully", Toast.LENGTH_SHORT).show();
    }
}
