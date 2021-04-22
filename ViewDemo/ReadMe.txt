View Demo

1.Create a xml file in drawable and name it back.xml
This file will be used for giving shapes to different view components

```
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <stroke android:width="4dp" android:color="@color/teal_700"/>
    <solid android:color="@color/cardview_dark_background"/>
    <corners android:radius="10dp"/>
</shape>
```

2. Setting onClick Listeners on Button to perform toggle between image

3. Adding single click, double click and long press on View Components

- Create a CustomTouchListener java class and implement View.OnTouchListener
- GestureDetector for holding the actions
- CustomOnGestureListener class for holding onLongPress, onFling, onDown, onDoubleTap, onSingleTapConfirmed
-Gestures like onSwipeUp, onSwipeDown, onSwipeLeft, onSwipeRight on textview 




