package com.example.roomdatabaseandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roomdatabaseandroid.R;

public class StudentActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_STUDENT_ID = "EXTRA_ID";
    public static final String EXTRA_REPLY_STUDENT_NAME = "NAME";
    public static final String EXTRA_REPLY_STUDENT_NO = "NUMBER";

    private EditText mEditProductNameView, mEditProductDescriptionView, mEditProductQuantityView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        mEditProductNameView = findViewById(R.id.edit_student_name);
        mEditProductQuantityView = findViewById(R.id.edit_student_Id);

//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_REPLY_STUDENT_ID)) {
            setTitle("Edit Product");
            mEditProductNameView.setText(intent.getStringExtra(EXTRA_REPLY_STUDENT_NAME));
            mEditProductQuantityView.setText(intent.getStringExtra(EXTRA_REPLY_STUDENT_NO));

        } else {
            setTitle("Add Product");
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditProductNameView.getText()) || TextUtils.isEmpty(mEditProductDescriptionView.getText()) || TextUtils.isEmpty(mEditProductQuantityView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String productName = mEditProductNameView.getText().toString();
                    String productDescription = mEditProductDescriptionView.getText().toString();
                    String productQuantity = mEditProductQuantityView.getText().toString();

                    replyIntent.putExtra(EXTRA_REPLY_STUDENT_NAME, productName);
                    replyIntent.putExtra(EXTRA_REPLY_STUDENT_NO, productDescription);

                    int id = getIntent().getIntExtra(EXTRA_REPLY_STUDENT_ID, -1);
                    if (id != -1) {
                        replyIntent.putExtra(EXTRA_REPLY_STUDENT_ID, id);
                    }

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

}
