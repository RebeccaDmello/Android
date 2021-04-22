package com.example.tabslistview;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;

public class CustomTouchListener implements View.OnTouchListener {

    Context context;
    //an object to detect simple gestures
    GestureDetectorCompat gestureDetectorCompat;

    public CustomTouchListener(Context context){
        this.context = context;
        gestureDetectorCompat = new GestureDetectorCompat(context, new CustomOnGestureListener());
    }

    public class CustomOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_DIST_THRESHOLD = 10;
        private static final int SWIPE_VEL_THRESHOLD = 75;

        @Override
        public void onLongPress(MotionEvent e){
            super.onLongPress(e);
            onLongClick();
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
            float distX = e2.getX() - e1.getX();
            float distY = e2.getY() - e1.getY();

            Log.d("SWIPE","Entered on fling...");
            Log.d("SWIPE","distX = " + distX + " ,VelX = " + velocityX +
                    " distY = "+ distY + " , VelocityY = " + velocityY);
            if (Math.abs(distX) > Math.abs(distY)
                    && Math.abs(distX) > SWIPE_DIST_THRESHOLD
                    && Math.abs(velocityX) > SWIPE_VEL_THRESHOLD){
                //this means it is a horizontal swipe
                if (distX > 0){
                    //Swipe Right
                    onSwipeRight();
                } else{
                    onSwipeLeft();
                }
                return true;
            } else if (Math.abs(distX) < Math.abs(distY)
                    && Math.abs(distY) > SWIPE_DIST_THRESHOLD
                    && Math.abs(velocityY) > SWIPE_VEL_THRESHOLD){
                //this means a vertical swipe has occurred
                if (distY > 0){
                    onSwipeDown();
                } else {
                    onSwipeUp();
                }
                return true;
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e){
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e){
            onDoubleClick();
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e){
            onSingleClick();
            return  super.onSingleTapConfirmed(e);
        }
    }



    public void onSwipeUp() {
        Log.d("SWIPE","Swipe Up Detected in the Custom Touch Listener");
    }

    public void onSwipeDown() {
        Log.d("SWIPE","Swipe Down Detected in the Custom Touch Listener");
    }

    public void onSwipeLeft() {
        Log.d("SWIPE","Swipe Left Detected in the Custom Touch Listener");
    }

    public void onSwipeRight() {
        Log.d("SWIPE","Swipe Right Detected in the Custom Touch Listener");
    }


    void onDoubleClick() {
        Log.d("GESTURE","Double Click Detected");
    }

    void onSingleClick() {
        Log.d("GESTURE","Single Click Detected");
    }

    void onLongClick() {
        Log.d("GESTURE","Long Press Detected");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetectorCompat.onTouchEvent(event);
    }
}
