package com.example.recyclerviewemptytemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView textViewDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewDetail = findViewById(R.id.textViewDetail);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        String name = sharedPreferences.getString("name","Name is not available");

        textViewDetail.setText(name);
    }
}