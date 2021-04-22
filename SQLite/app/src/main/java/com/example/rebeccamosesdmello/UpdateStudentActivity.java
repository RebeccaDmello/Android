package com.example.rebeccamosesdmello;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateStudentActivity extends AppCompatActivity {

    private EditText oldNameET , newNameET, stdIdET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
    }

    public void updateButtonPressed(View view) {
        oldNameET = findViewById(R.id.oldName);
        newNameET = findViewById(R.id.newName);
        stdIdET = findViewById(R.id.StdId);

        String oldName = oldNameET.getText().toString();
        String newName = newNameET.getText().toString();
        String newStdId = stdIdET.getText().toString();

        DatabaseHandler.updateUsingName(oldName, newName, newStdId);
        Toast.makeText(this, "Record updated", Toast.LENGTH_SHORT).show();

        oldNameET.setText("");
        newNameET.setText("");
        stdIdET.setText("");
    }
}
