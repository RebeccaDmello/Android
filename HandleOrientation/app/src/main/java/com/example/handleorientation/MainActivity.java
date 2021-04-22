package com.example.handleorientation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private TextView txtResult;
    private EditText edName;
    private Button btn;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate()");

        edName = (EditText)findViewById(R.id.edName);
        btn = (Button)findViewById(R.id.btn);
        txtResult = (TextView)findViewById(R.id.txtResult);
        imageView = (ImageView)findViewById(R.id.imageView);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                txtResult.setText("Welcome "+edName.getText().toString());
                btn.setText("LOGOUT");
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfiguartionChanged()");

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            imageView.setImageResource(R.drawable.potrait);
        }else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            imageView.setImageResource(R.drawable.landscape);
        }
    }

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState){
//        super.onRestoreInstanceState(savedInstanceState);
//        Log.i(TAG, "onRestoreInstanceState()");
//        if(savedInstanceState != null){
//            btn.setText(savedInstanceState.getString("btn"));
//            txtResult.setText(savedInstanceState.getString("message"));
//        }
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        super.onRestoreInstanceState(outState);
//        Log.i(TAG, "onSaveInstanceState()");
//
//        outState.putString("message", txtResult.getText().toString());
//        outState.putString("btn", btn.getText().toString());
//    }


    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart()");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG, "onStop()");
    }

}