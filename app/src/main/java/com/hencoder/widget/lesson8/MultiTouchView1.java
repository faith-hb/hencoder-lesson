package com.hencoder.widget.lesson8;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hencoder.Utils;

/**
 * 触摸反馈-view1
 * @autor hongbing
 * @date 2019/5/2
 */
public class MultiTouchView1 extends View {
    private static final float IMAGE_WIDTH = Utils.dp2Px(180);
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap mBitmap;
    float downX, downY;
    float offsetX, offsetY;
    float imgOffsetX, imgOffsetY;
    int trackingPointId;

    public MultiTouchView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mBitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, offsetX, offsetY, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                trackingPointId = event.getPointerId(0);
                downX = event.getX();
                downY = event.getY();
                imgOffsetX = offsetX;
                imgOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                int index = event.findPointerIndex(trackingPointId);
                offsetX = imgOffsetX + event.getX(index) - downX;
                offsetY = imgOffsetY + event.getY(index) - downY;
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                int actionIndex = event.getActionIndex();
                trackingPointId = event.getPointerId(actionIndex);
                downX = event.getX(actionIndex);
                downY = event.getY(actionIndex);
                imgOffsetX = offsetX;
                imgOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                if (pointerId == trackingPointId) {
                    int newIndex;
                    if(actionIndex == event.getPointerCount() - 1){
                        newIndex = event.getPointerCount() - 2;
                    }else{
                        newIndex = event.getPointerCount() - 1;
                    }
                    trackingPointId = event.getPointerId(newIndex);
                    downX = event.getX(newIndex);
                    downY = event.getY(newIndex);
                    imgOffsetX = offsetX;
                    imgOffsetY = offsetY;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
