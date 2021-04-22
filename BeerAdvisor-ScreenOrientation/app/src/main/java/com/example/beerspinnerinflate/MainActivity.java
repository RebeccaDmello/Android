package com.example.beerspinnerinflate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private BeerList beer = new BeerList();
    private Spinner spinBeer;
    private TextView txtBeer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinBeer = (Spinner)findViewById(R.id.spinnerBeer);
        txtBeer = (TextView)findViewById(R.id.txtBeerResult);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.beer_colors,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinBeer.setAdapter(adapter);
        spinBeer.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String beerType = String.valueOf(spinBeer.getSelectedItem());
        List<String> brandsList = beer.getList(beerType);
        StringBuilder brandsFormatted = new StringBuilder();
        for(String brand: brandsList){
            brandsFormatted.append(brand).append("\n");
        }
        txtBeer.setText(brandsFormatted);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}