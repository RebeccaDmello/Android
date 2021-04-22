package com.example.rebeccamosesdmello;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler.getAllData(this);
    }

    public void buttonPressed(View view) {
        String tag = view.getTag().toString().toLowerCase();
        switch (tag) {
            case "viewbutton": {
                Intent intent = new Intent(MainActivity.this, ViewStudentsActivity.class);
                startActivity(intent);
                break;
            }
            case "addbutton": {
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivity(intent);
                break;
            }
            case "deletebutton": {
                Intent intent = new Intent(MainActivity.this, DeleteStudentActivity.class);
                startActivity(intent);
                break;
            }
            case "updatebutton": {
                Intent intent = new Intent(MainActivity.this, UpdateStudentActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
