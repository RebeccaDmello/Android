package com.example.roomdatabaseandroid.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.roomdatabaseandroid.R;
import com.example.roomdatabaseandroid.adapter.ItemClickListenerpro;
import com.example.roomdatabaseandroid.adapter.ListAdapterpro;
import com.example.roomdatabaseandroid.entity.Student;
import com.example.roomdatabaseandroid.model.VModel;
import com.example.roomdatabaseandroid.utils.AppStr;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_WORD_ACTIVITY_REQUEST_CODE = 2;

    private VModel vModel;
    private Button editBtn, delBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ListAdapterpro adapter = new ListAdapterpro(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        vModel = ViewModelProviders.of(this).get(VModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        vModel.getmAllProduct().observe(this, (products) ->{
                adapter.setmProducts(products);
        });

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });


        recyclerView.addOnItemTouchListener(
                new ItemClickListenerpro(this, new ItemClickListenerpro.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, final int position) {
                        editBtn = v.findViewById(R.id.button_edit);
                        //Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
                        delBtn = v.findViewById(R.id.button_delete);

                        delBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Delete button is called"+adapter.getProductAt(position).getStudentName() , Toast.LENGTH_SHORT).show();

                                vModel.delete(adapter.getProductAt(position));
                            }
                        });

                        editBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Edit button is called"+adapter.getProductAt(position).getStudentName() , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                                intent.putExtra(AppStr.EXTRA_REPLY_STUDENT_ID, adapter.getProductAt(position).getStudentId());
                                intent.putExtra(AppStr.EXTRA_REPLY_STUDENT_NAME, adapter.getProductAt(position).getStudentName());
                                intent.putExtra(AppStr.EXTRA_REPLY_STUDENT_NO, adapter.getProductAt(position).getStudentNo());
                                startActivityForResult(intent, EDIT_WORD_ACTIVITY_REQUEST_CODE);
                            }
                        });
                    }
                })
        );


    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String productName = data.getStringExtra(AppStr.EXTRA_REPLY_STUDENT_NAME);
            String productQuantity = data.getStringExtra(AppStr.EXTRA_REPLY_STUDENT_NO);
            int quantity = Integer.parseInt(productQuantity);

            Student product = new Student(productName,System.currentTimeMillis(),quantity);
            vModel.insert(product);
        } else if (requestCode == EDIT_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            int id = data.getIntExtra(AppStr.EXTRA_REPLY_STUDENT_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String productName = data.getStringExtra(AppStr.EXTRA_REPLY_STUDENT_NAME);
            String productQuantity = data.getStringExtra(AppStr.EXTRA_REPLY_STUDENT_NO);
            int quantity = Integer.parseInt(productQuantity);

            Student product = new Student(productName,System.currentTimeMillis(),quantity);
            product.setStudentId(id);
            vModel.update(product);

            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }

}