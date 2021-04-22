package com.example.viewandroiddemo;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;

public class CustomTouchListener implements View.OnTouchListener {
    Context context;
    GestureDetectorCompat gestureDetectorCompat;//declaring a gesture detector object

    public CustomTouchListener(Context context) {
        this.context = context;
        //need to create GestureDetectorCompat object and set it to gestureDetectorCompat
        //this needs two things - one is context, the other CustomOnGesture
        gestureDetectorCompat = new GestureDetectorCompat(context, new CustomOnGestureListener());
    }

    public class CustomOnGestureListener extends GestureDetector.SimpleOnGestureListener{
    private static final int SWIPE_DIST_THRESHOLD = 10;
    private static final int SWIPE_VEL_THRESHOLD = 75;

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            Log.d("GESTUREDEMO","Long Press Detected");
            //method to execte when LongPress is detected
            onLongClick();
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //return super.onFling(e1, e2, velocityX, velocityY);
            float distX = e2.getX() - e1.getX();
            float distY = e2.getY() - e1.getY();
            Log.d("SWIPE","Entered on fling...");
            Log.d("SWIPE","distX = " + distX +" ,VelX = " + velocityX + "distY ="+distY+ ", VelocityY = "+velocityY);

            if(Math.abs(distX) > Math.abs(distY) && Math.abs(distX) > SWIPE_DIST_THRESHOLD && Math.abs(velocityX) > SWIPE_VEL_THRESHOLD){
                   //this means it is a horizontal swipe
                if(distX > 0){
                    //Swipe Right
                    onSwipeRight();
                }else{
                    onSwipeLeft();
                }
                return true;
            }else if(Math.abs(distX) < Math.abs(distY) && Math.abs(distY) > SWIPE_DIST_THRESHOLD && Math.abs(velocityY) > SWIPE_VEL_THRESHOLD){
                if(distY > 0){
                    onSwipeDown();
                }else{
                    onSwipeUp();
                }
                return true;
            }
            return false; //If no left, right, up or down is detected, we return false
        }

        @Override
        public boolean onDown(MotionEvent e) {
//            return super.onDown(e);
            return true;
        }

        //Remember to make it public
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d("GESTUREDMEO","Double tab confirmed");
            onDoubleClick(); //added as a public method
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d("GESTUREDMEO","single tab");
            onSingleClick(); //method stub needs to be created in Custom TouchListener
            return super.onSingleTapConfirmed(e);
        }
    }

    public void onSwipeUp() {
        Log.d("SWIPE","Swipe Up Detected in the custom Touch Listener");
    }

    public void onSwipeDown() {
        Log.d("SWIPE","Swipe Down Detected in the custom Touch Listener");
    }

    public void onSwipeLeft() {
        Log.d("SWIPE","Swipe Left Detected in the custom Touch Listener");
    }

    public void onSwipeRight() {
        Log.d("SWIPE","Swipe Right Detected in the custom Touch Listener");
    }

    public void onDoubleClick() {
        Log.d("GESTUREDEMO","Double click method inside custom touch listener");
    }

    public void onSingleClick() {
        Log.d("GESTUREDEMO","Single click method inside custom touch listener");
    }

    //make it public to be available
    public void onLongClick() {
        Log.d("GESTUREDEMO","Long click methd inside custome touch listener");
        //here you do not want view specific changes
    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
//        return false; // if this returns false, the execution flow stops here
        return gestureDetectorCompat.onTouchEvent(motionEvent);
    }
}
