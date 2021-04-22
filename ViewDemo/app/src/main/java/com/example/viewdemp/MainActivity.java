package com.example.viewdemp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtWelMsg;
    ImageView imgBird;
    Button btnText, btnBoth;
    boolean bigger = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtWelMsg = findViewById(R.id.txtWelMsg);
        imgBird = findViewById(R.id.imgBird);
        btnText = findViewById(R.id.btnText);
        btnBoth = findViewById(R.id.btnBoth);

        //setting image from Java
        Drawable img = getResources().getDrawable(R.drawable.border);
        img.setBounds(0,0,0 + img.getIntrinsicWidth(), 0 + img.getIntrinsicHeight());
        txtWelMsg.setCompoundDrawables(img,null,img,null);
        txtWelMsg.setCompoundDrawablePadding(8);
        txtWelMsg.setAlpha(1.0f);

        btnText.setOnClickListener((View v) -> {
            if (btnText.getText() == "Show Text") {
                imgBird.setVisibility(View.GONE);
                txtWelMsg.setVisibility(View.VISIBLE);
                btnText.setText("Show Image");
            } else {
                imgBird.setVisibility(View.VISIBLE);
                txtWelMsg.setVisibility(View.GONE);
                btnText.setText("Show Text");
            }
        });

        btnBoth.setOnClickListener((View v) -> {
            imgBird.setVisibility(View.VISIBLE);
            txtWelMsg.setVisibility(View.VISIBLE);
        });

        txtWelMsg.setOnTouchListener(new CustomTouchListener(this){
            @Override
            public void onSwipeUp() {
                Toast.makeText(context, "Swiped up", Toast.LENGTH_SHORT).show();
                int horzGravity = txtWelMsg.getGravity() & Gravity.HORIZONTAL_GRAVITY_MASK;
                txtWelMsg.setGravity(horzGravity | Gravity.TOP);
                super.onSwipeUp();
            }

            @Override
            public void onSwipeDown() {
                Toast.makeText(context, "Swiped Dowb", Toast.LENGTH_SHORT).show();
                int horzGravity = txtWelMsg.getGravity() & Gravity.HORIZONTAL_GRAVITY_MASK;
                txtWelMsg.setGravity(horzGravity | Gravity.BOTTOM);
                super.onSwipeDown();
            }

            @Override
            public void onSwipeLeft() {
                Toast.makeText(context, "Swiped Left", Toast.LENGTH_SHORT).show();
                int verGravity = txtWelMsg.getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
                txtWelMsg.setGravity(verGravity | Gravity.LEFT);
                super.onSwipeLeft();
            }

            @Override
            public void onSwipeRight() {
                Toast.makeText(context, "Swiped right", Toast.LENGTH_SHORT).show();
               // txtViewWelcomeMsg.setGravity(Gravity.TOP | Gravity.LEFT);
                int verGravity = txtWelMsg.getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
                txtWelMsg.setGravity(verGravity | Gravity.RIGHT); //bitwise OR operator combines horz and vertical
                super.onSwipeRight();
            }

            @Override
            public void onDoubleClick() {
                super.onDoubleClick();
                Log.d("GESTURE","Detected double click of TextView");
                if (!bigger){
                    txtWelMsg.setTextSize(
                            txtWelMsg.getTextSize()/getResources().getDisplayMetrics().density + 10);
                    bigger = true;
                } else{
                    txtWelMsg.setTextSize(
                            txtWelMsg.getTextSize()/getResources().getDisplayMetrics().density - 10);
                    bigger = false;
                }
            }

            @Override
            public void onSingleClick() {
                super.onSingleClick();
                Log.d("GESTURE","Detected single click on the TextView");
                if (txtWelMsg.getCurrentTextColor()
                        != getResources().getColor(R.color.teal_200)){
                    txtWelMsg.setTextColor(getResources().getColor(R.color.teal_200));
                } else {
                    txtWelMsg.setTextColor(Color.rgb(255,255,255));
                }
            }

            @Override
            public void onLongClick() {
                super.onLongClick();
                Log.d("GESTURE","Detected long click on the TextView");
                Toast.makeText(context, "Long Click on the text view detected", Toast.LENGTH_SHORT).show();
                if ((txtWelMsg.getPaintFlags() & Paint.STRIKE_THRU_TEXT_FLAG)
                        == Paint.STRIKE_THRU_TEXT_FLAG){
                    txtWelMsg.setPaintFlags(txtWelMsg.getPaintFlags()
                            & ~Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    txtWelMsg.setPaintFlags(txtWelMsg.getPaintFlags()
                            | Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("GESTURE","Detected onTouch on the TextView...");
                return super.onTouch(view, motionEvent);
            }
        });


        imgBird.setOnTouchListener(new CustomTouchListener(this){
            @Override
            public void onDoubleClick(){
                super.onDoubleClick();
                if (imgBird.getScaleType() != ImageView.ScaleType.FIT_XY) {
                    imgBird.setScaleType(ImageView.ScaleType.FIT_XY);
                } else {
                    imgBird.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }
            }
            @Override
            public void onSingleClick() {
                super.onSingleClick();
                //On single click, change image back and forth to bird and fire
                if (imgBird.getDrawable().getConstantState()
                        != getResources().getDrawable(R.drawable.bird).getConstantState()) {
                    imgBird.setImageResource(R.drawable.bird);
                } else {
                    imgBird.setImageResource(R.drawable.fire);
                }
            }
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("GESTURE","Detected onTouch on ImageView...");
                return super.onTouch(view, motionEvent);
            }
        });

    }
}