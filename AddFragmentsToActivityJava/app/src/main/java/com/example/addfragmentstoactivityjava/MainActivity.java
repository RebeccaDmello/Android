package com.example.rebecca.addfragmenttoanactivityusingjava;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.reza.addfragmenttoanactivityusingjava.HelloFragment;
import com.example.reza.addfragmenttoanactivityusingjava.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HelloFragment helloFragment = new HelloFragment();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //The last argument to the method add is optional (the tag)
        transaction.add(R.id.container, helloFragment, "helloFragment" );
        transaction.commit() ;
    }
}
