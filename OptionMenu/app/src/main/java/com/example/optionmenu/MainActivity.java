package com.example.optionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMain = findViewById(R.id.txtMain);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.profile){
            txtMain.setText("Profile");
        }else if(item.getItemId() == R.id.settings){
            txtMain.setText("Settings");
        }else if(item.getItemId() == R.id.One){
            txtMain.setText("One");
        }else if(item.getItemId() == R.id.Two){
            txtMain.setText("Two");
        }else if(item.getItemId() == R.id.Search){
            txtMain.setText("Search");
        }else if(item.getItemId() == R.id.Delete){
            txtMain.setText("Delete");
        }else if(item.getItemId() == R.id.ActivityOne){
            Intent i = new Intent(MainActivity.this, Activity1.class);
            startActivity(i);
        }else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}