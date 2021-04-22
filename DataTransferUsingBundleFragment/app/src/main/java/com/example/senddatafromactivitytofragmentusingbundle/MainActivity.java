package com.example.senddatafromactivitytofragmentusingbundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnFrag;
    private EditText edName, edCourse;

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
                FirstFragment firstFragment = new FirstFragment();
                FragmentManager manager = getSupportFragmentManager();

                String input1 = edName.getText().toString();
                String input2 = edCourse.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("name", input1);
                bundle.putString("course", input2);

                firstFragment.setArguments(bundle);
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.container, firstFragment, "firstFragment");
                transaction.commit();
            }
        });


    }
}