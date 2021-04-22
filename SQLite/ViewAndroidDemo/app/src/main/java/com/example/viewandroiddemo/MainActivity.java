package com.example.viewandroiddemo;

import androidx.annotation.Dimension;
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
    TextView txtViewWelcomeMsg;
    boolean bigger = false; //maintain current font size state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtViewWelcomeMsg = (TextView)findViewById(R.id.txtViewWelcome);
        txtViewWelcomeMsg = findViewById(R.id.txtViewWelcome);

        ImageView imgView = (ImageView) findViewById(R.id.imgViewSample);

        Drawable img = getResources().getDrawable(R.drawable.border);
        img.setBounds(0,0,0+img.getIntrinsicWidth(),0+img.getIntrinsicHeight());
        txtViewWelcomeMsg.setCompoundDrawables(img,null, img, null);
        txtViewWelcomeMsg.setCompoundDrawablePadding(8);
        txtViewWelcomeMsg.setAlpha(1.0f); //alpha refers to transparency of the view, alpha 1 is fully opaque , 0 for fully transparent

        Button btnText = findViewById(R.id.btnText);
        btnText.setOnClickListener((View view)->{
            if(btnText.getText().equals("Show Text")){
                imgView.setVisibility(View.GONE);
                txtViewWelcomeMsg.setVisibility(View.VISIBLE);
                btnText.setText("Show Image");
            }else{
                imgView.setVisibility(View.VISIBLE);
                txtViewWelcomeMsg.setVisibility(View.GONE);
                btnText.setText("Show Text");
            }
        });

        Button btnBothObj = findViewById(R.id.btnBoth);
        btnBothObj.setOnClickListener((View view) ->{
            imgView.setVisibility(View.VISIBLE);
            txtViewWelcomeMsg.setVisibility(View.VISIBLE);
        });

        txtViewWelcomeMsg.setOnTouchListener(new CustomTouchListener(this){

            @Override
            public void onSwipeUp() {
                Toast.makeText(context,"Swiped up",Toast.LENGTH_SHORT).show();
                int horGravity = txtViewWelcomeMsg.getGravity() & Gravity.HORIZONTAL_GRAVITY_MASK;
                txtViewWelcomeMsg.setGravity(horGravity | Gravity.TOP);
                super.onSwipeUp();
            }

            @Override
            public void onSwipeDown() {
                Toast.makeText(context,"Swiped Down",Toast.LENGTH_SHORT).show();
                int horGravity = txtViewWelcomeMsg.getGravity() & Gravity.HORIZONTAL_GRAVITY_MASK;
                txtViewWelcomeMsg.setGravity(horGravity | Gravity.BOTTOM);
                super.onSwipeDown();
            }

            @Override
            public void onSwipeLeft() {
                Toast.makeText(context,"Swiped Left",Toast.LENGTH_SHORT).show();
                int verGravity = txtViewWelcomeMsg.getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
                txtViewWelcomeMsg.setGravity(verGravity | Gravity.LEFT);
                super.onSwipeLeft();
            }

            @Override
            public void onSwipeRight() {
                Toast.makeText(context,"Swiped Right",Toast.LENGTH_SHORT).show();
//                txtViewWelcomeMsg.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                int verGravity = txtViewWelcomeMsg.getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
                txtViewWelcomeMsg.setGravity(verGravity | Gravity.RIGHT);   //bitwise OR operator combines hori and verti operator
                super.onSwipeRight();
            }

            @Override
            public void onDoubleClick(){
                super.onDoubleClick();
                Log.d("GESTUREDEMO","Detected Double click of TextView");
                if(!bigger){
                    txtViewWelcomeMsg.setTextSize(txtViewWelcomeMsg.getTextSize()/getResources().getDisplayMetrics().density + 10);
                    bigger = true;
                }else{
                    txtViewWelcomeMsg.setTextSize(txtViewWelcomeMsg.getTextSize()/getResources().getDisplayMetrics().density - 10);
                    bigger = false;
                }
            }

            @Override
            public void onSingleClick(){
                super.onSingleClick();
                Log.d("GESTUREDEMO","");
                if(txtViewWelcomeMsg.getCurrentTextColor() != getResources().getColor(R.color.teal_200)){
                    txtViewWelcomeMsg.setTextColor(getResources().getColor(R.color.teal_200));
                }else{
                    txtViewWelcomeMsg.setTextColor(Color.WHITE);
                }
            }

            @Override
            public void onLongClick() {
                super.onLongClick();
                Log.d("GESTUREDEMO","Detected long click on the TextView");
                //functionality you want for the long click of the textview
                Toast.makeText(context,"Long click on the text view detected", Toast.LENGTH_LONG);
                if((txtViewWelcomeMsg.getPaintFlags() & Paint.STRIKE_THRU_TEXT_FLAG) == Paint.STRIKE_THRU_TEXT_FLAG){
                    //this means textview currently has strike through
                    txtViewWelcomeMsg.setPaintFlags(txtViewWelcomeMsg.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    //takes the complement of STRIKE_TRHOUGH which is 11110111
                    //and bitwise AND with current flag
                    //Copies all other flag bits as is
                    //except make STRIKETHROUGH 0 (no strike through)
                }else{
                    txtViewWelcomeMsg.setPaintFlags(txtViewWelcomeMsg.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    //takes current flags and ORs it with the strikethrough
                }
            }

            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                Log.d("GESTUREDEMO","Detected onTouch on the TextView");
                return super.onTouch(v, motionEvent);
            }
        });

        imgView.setOnTouchListener(new CustomTouchListener(this){
            @Override
            public void onDoubleClick() {
                super.onDoubleClick();
                //Here, we are going to change scale type
                //FIT_XY - stretch image along X and Y of ImageView
                //FIT_CENTER - means resize image to fit in ImageView
                if(imgView.getScaleType() != ImageView.ScaleType.FIT_XY){
                    imgView.setScaleType(ImageView.ScaleType.FIT_XY);
                }else{
                    imgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }
            }

            @Override
            public void onSingleClick() {
                super.onSingleClick();
                //on single click, change image back nd forth to bird and fire
                if(imgView.getDrawable().getConstantState() != getResources().getDrawable(R.drawable.bird).getConstantState()){
                    imgView.setImageResource(R.drawable.bird);
                }else{
                    imgView.setImageResource(R.drawable.fire);
                }
            }

            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                Log.d("GESTUREDEMO","Detected onTouch on ImageView...");
                return super.onTouch(v, motionEvent);
//                return false; //useful to test if set to false, other gestures might not be detected
                //if you return false
                //you can instantiate the constraint layout object in the onCreate method
                //and set up a click listner for it..
                //false means the event is not consumed and can be passed to the containing view listners
            }
        });
    }
}