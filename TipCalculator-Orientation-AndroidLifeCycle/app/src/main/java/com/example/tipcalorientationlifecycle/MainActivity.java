package com.example.tipcalorientationlifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener, View.OnClickListener{


    private EditText billAmountEditText;
    private TextView percentTextView;
    private Button btnAdd;
    private Button btnSub;
    private TextView tipTextView;
    private TextView totalTextView;
    private String billAmountString = "";
    private float tipPercent = 0.15f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        billAmountEditText = (EditText)findViewById(R.id.billAmountEditText);
        percentTextView = findViewById(R.id.percentTextView);
        btnSub = findViewById(R.id.btnSub);
        btnAdd = findViewById(R.id.btnAdd);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.TotalTextView);

        billAmountEditText.setOnEditorActionListener(this);
        btnSub.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("billAmountString", billAmountString);
        outState.putFloat("tipPercent", tipPercent);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            billAmountString = savedInstanceState.getString("billAmountString","");
            tipPercent =  savedInstanceState.getFloat("tipPercent", 0.15f);

            billAmountEditText.setText(billAmountString);
            calculateAndDisplay();
        }
    }


    public void calculateAndDisplay(){
        //getting bill Amount
        billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        if(billAmountString.equals("")){
            billAmount = 0;
        }else {
            billAmount = Float.parseFloat(billAmountString);
        }

        //Calculate Tip and Total
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        //display the results after formattig them
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSub:
                tipPercent = tipPercent - 0.01f;
                calculateAndDisplay();
                break;
            case R.id.btnAdd:
                tipPercent = tipPercent + 0.01f;
                calculateAndDisplay();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        calculateAndDisplay();
        return false;
    }
}