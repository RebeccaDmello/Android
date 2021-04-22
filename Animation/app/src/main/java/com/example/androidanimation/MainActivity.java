package com.example.androidanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import static com.example.androidanimation.R.transition.animate;

public class MainActivity extends AppCompatActivity {

    private Button btnanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnanim = findViewById(R.id.btnAnim);
        btnanim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animate = AnimationUtils.loadAnimation(MainActivity.this, R.transition.animate);
                btnanim.startAnimation(animate);
            }
        });
    }
}