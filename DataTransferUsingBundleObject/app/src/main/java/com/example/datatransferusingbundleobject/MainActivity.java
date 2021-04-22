package com.example.datatransferusingbundleobject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edName, edCourse;
    Button btnFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edName = findViewById(R.id.edName);
        edCourse = findViewById(R.id.edCourse);
        btnFrag = findViewById(R.id.btnFrag);



        btnFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragData();
            }
        });
    }

    private void getFragData() {
        String name = edName.getText().toString();
        String course = edCourse.getText().toString();

        FirstFragment firstFragment = new FirstFragment();
        FragmentManager manager = getSupportFragmentManager();

        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putString("course", course);
        firstFragment.setArguments(bundle);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container,firstFragment, "first Fragment");
        transaction.commit();
    }
}