package com.example.rebeccamosesdmello;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewStudentsActivity extends AppCompatActivity {
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);
        dispStudData();
    }

    public void dispStudData(){
        cursor = DatabaseHandler.readData();
        LinearLayout parent = findViewById(R.id.LinearLayoutId);

        int count = 0;
        if(!cursor.moveToFirst()){
            Toast.makeText(this, "Students Not Found. Database is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        do{
            count++;
            String nameDB = cursor.getString(1);
            String numberDB = cursor.getString(2);

            LinearLayout subLayout = new LinearLayout(this);
            subLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            subLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView name = new TextView(this);
            TextView stdId = new TextView(this);

            name.setTextColor(Color.BLACK);
            stdId.setTextColor(Color.BLACK);

            TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f);
            name.setLayoutParams(params);
            stdId.setLayoutParams(params);

            name.setText(nameDB);
            stdId.setText("\t\t\t"+numberDB);

            parent.addView(subLayout);
            subLayout.addView(name);
            subLayout.addView(stdId);
        }while(cursor.moveToNext());
        
        Log.d("DEBUG", "COUNT: "+count);

    }
}
