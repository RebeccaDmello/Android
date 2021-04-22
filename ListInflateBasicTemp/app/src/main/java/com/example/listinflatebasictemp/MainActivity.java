package com.example.listinflatebasictemp;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    StudentSpecViewModel studentSpecViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Setting up the List of Student objects
        List<Student> Student = new ArrayList<>();
        List<String> stdName = new ArrayList<>(Arrays.asList("Rebecca","Moses","Dmello"));
        List<String> stdCourse = new ArrayList<>(Arrays.asList("CSIS1","CSIS2","CSIS3"));
        List<Integer> ColorVals = new ArrayList<>(Arrays.asList(
                R.color.black,
                Color.rgb(255,165,0),
                Color.parseColor("#800080")));

        for(int i = 0; i < stdCourse.size() ; i++){
            Student eachStd = new Student(stdCourse.get(i), stdName.get(i), ColorVals.get(i));
            Student.add(eachStd);
        }

        studentSpecViewModel = new ViewModelProvider(this).get(StudentSpecViewModel.class);
        studentSpecViewModel.loadStudents(Student);

        //Load this color data into the ViewModel

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this,"Clicked on Settings", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.action_profile){
            Toast.makeText(this, "Clicked on Profile", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.action_search){
            Toast.makeText(this, "Clicked on Search", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}