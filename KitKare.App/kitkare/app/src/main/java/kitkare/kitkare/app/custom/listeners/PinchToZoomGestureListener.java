package kitkare.kitkare.app.custom.listeners;

import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TextView;

public class PinchToZoomGestureListener
        //extends ScaleGestureDetector.SimpleOnScaleGestureListener
{
    final static float STEP = 200;
    float mRatio = 1.0f;
    int mBaseDist;
    float mBaseRatio;
    float fontsize = 13;

    public boolean onTouchEvent(MotionEvent event, TextView textView) {
        if (event.getPointerCount() == 2) {
            int action = event.getAction();
            int pureaction = action & MotionEvent.ACTION_MASK;
            if (pureaction == MotionEvent.ACTION_POINTER_DOWN) {
                mBaseDist = getDistance(event);
                mBaseRatio = mRatio;
            } else {
                float delta = (getDistance(event) - mBaseDist) / STEP;
                float multi = (float) Math.pow(2, delta);
                mRatio = Math.min(1024.0f, Math.max(0.1f, mBaseRatio * multi));
                textView.setTextSize(mRatio + fontsize);
            }
        }
        return true;
    }

    int getDistance(MotionEvent event) {
        int dx = (int) (event.getX(0) - event.getX(1));
        int dy = (int) (event.getY(0) - event.getY(1));
        return (int) (Math.sqrt(dx * dx + dy * dy));
    }
//    public TextView view;
//    public PinchToZoomGestureListener(TextView view){
//        this.view = view;
//    }
//
//    @Override
//    public boolean onScale(ScaleGestureDetector detector)
//    {
//        float size = view.getTextSize();
//        Log.d("TextSizeStart", String.valueOf(size));
//
//        float factor = detector.getScaleFactor();
//        Log.d("Factor", String.valueOf(factor));
//
//        float product = size * factor;
//        Log.d("TextSize", String.valueOf(product));
//        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, product);
//
//        size = view.getTextSize();
//        Log.d("TextSizeEnd", String.valueOf(size));
//        return true;
//    }
}
