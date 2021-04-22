package com.example.sharedpreferenceapplicationlevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edName, edCourse;
    TextView txtName, txtCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.edName);
        edCourse = findViewById(R.id.edCourse);

        txtName = findViewById(R.id.txtName);
        txtCourse = findViewById(R.id.txtCourse);
    }

    public void saveData(View view) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name",edName.getText().toString());
        editor.putString("course", edCourse.getText().toString());
        editor.apply();
    }

    public void loadData(View view) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        String name = sharedPreferences.getString("name","Name is not available");
        String course = sharedPreferences.getString("course","course is not available");

        txtName.setText(name);
        txtCourse.setText(course);
    }

    //Add on other buttons to function
    public void clearData(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("my_pref_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply(); //or editor.commit

    }

    public void removesStudentMajor(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("my_pref_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("major");
        editor.apply();
    }
}