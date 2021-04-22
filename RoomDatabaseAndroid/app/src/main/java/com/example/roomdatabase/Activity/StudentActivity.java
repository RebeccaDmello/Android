package com.example.roomdatabase.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.roomdatabase.R;
public class StudentActivity extends AppCompatActivity {
    public static final String STUDENT_ID = "ID";
    public static final String STUDENT_NAME = "NAME";
    public static final String STUDENT_DEPT = "DEPT";
    public static final String STUDENT_SID = "SID";
    private EditText edStdName, edStdDes, edStdID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);
        edStdName = findViewById(R.id.edit_student_name);
        edStdDes = findViewById(R.id.edit_student_dept);
        edStdID = findViewById(R.id.edit_student_sId);
        Intent intent = getIntent();
        if (intent.hasExtra(STUDENT_ID)) {
            edStdName.setText(intent.getStringExtra(STUDENT_NAME));
            edStdDes.setText(intent.getStringExtra(STUDENT_DEPT));
            edStdID.setText(intent.getStringExtra(STUDENT_SID));
        }
        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(edStdName.getText()) || TextUtils.isEmpty(edStdDes.getText())
                        || TextUtils.isEmpty(edStdID.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String studentName = edStdName.getText().toString();
                    String studentDept = edStdDes.getText().toString();
                    String stdID = edStdID.getText().toString();
                    replyIntent.putExtra(STUDENT_NAME, studentName);
                    replyIntent.putExtra(STUDENT_DEPT, studentDept);
                    replyIntent.putExtra(STUDENT_SID, stdID);
                    int id = getIntent().getIntExtra(STUDENT_ID, -1);
                    if (id != -1) {
                        replyIntent.putExtra(STUDENT_ID, id);
                    }
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
