package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edNumber;
    Button btnCal;
    TextView txtRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edNumber = findViewById(R.id.edEnterNumber);
        btnCal = findViewById(R.id.btnCal);
        txtRes = findViewById(R.id.txtResult);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cal = edNumber.getText().toString();
                if(!cal.equals("")){
                    Float data =Float.valueOf(cal) * 0.85f;
                    txtRes.setText(data.toString());
                }else{
                    txtRes.setText("Please enter data into the text field");
                }
            }
        });
    }
}