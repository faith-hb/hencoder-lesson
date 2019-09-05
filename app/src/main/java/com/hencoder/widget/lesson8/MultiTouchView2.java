package com.hencoder.widget.lesson8;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.hencoder.Utils;

/**
 * @autor hongbing
 * @date 2019-05-09
 */
public class MultiTouchView2 extends View {

    private static final float IMAGE_WIDTH = Utils.dp2Px(220);
    // 最大放大系数
    private static final float MAX_SCALE_COE = 5f;

    private Bitmap mBitmap;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mOriginOffsetX, mOriginOffsetY;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float mScalePx, mScalePy;
    // 上次缩放值
    private float mPreScale = 1f;
    // 当前缩放值
    private float mCurrScale = 1f;
    // 放大阈值
    private float mScaleMaxSize;
    // 缩小阈值
    private float mScaleMinSize;
    private float mDownX, mDownY;
    private float mBmpOffsetX,mBmpOffsetY;
    private float mOffsetX,mOffsetY;

    public MultiTouchView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitmap = Utils.getAvatar(getResources(), (int) IMAGE_WIDTH);
        mScaleGestureDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                mScalePx = detector.getFocusX();
                mScalePy = detector.getFocusY();
                return true;
            }

            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                // 方法一：return true
//                mCurrScale = mPreScale * detector.getScaleFactor(); // 当前的伸缩值 = 伸缩因子 * 上次的伸缩值 保持连续性
//                Log.d("201905091118", "缩放中心点X = " + detector.getFocusX() + "->缩放中心点Y = " + detector.getFocusY() + "->缩放因子 = " + detector.getScaleFactor());
//                mPreScale = mCurrScale;
//                if (mCurrScale * mBitmap.getHeight() > mScaleMaxSize) { // 最大不能超过图片的三倍
//                    mPreScale = MAX_SCALE_COE;
//                    return true;
//                }
//                if (mCurrScale * mBitmap.getHeight() < mScaleMinSize) { // 最小不能小于初始值
//                    mPreScale = 1f;
//                    return true;
//                }
//                return true; // 返回true会释放缩放事件，前面的缩放值都会被重置，

                // 方法二：return false
                Log.d("201905091118", "缩放因子 = " + detector.getScaleFactor());
                mCurrScale = mPreScale * detector.getScaleFactor();
                if (mCurrScale * mBitmap.getHeight() > mScaleMaxSize) { // 最大不能超过图片的三倍
                    mPreScale = MAX_SCALE_COE;
                    return true;
                }
                if (mCurrScale * mBitmap.getHeight() < mScaleMinSize) { // 最小不能小于初始值
                    mPreScale = 1f;
                    return true;
                }
                return false; // 返回false，会记录前面的缩放值，并在之前的缩放值上继续进行计算
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {
                mPreScale = mCurrScale;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 图片居中
        mOriginOffsetX = (getWidth() - mBitmap.getWidth()) / 2;
        mOriginOffsetY = (getHeight() - mBitmap.getHeight()) / 2;
        mScaleMaxSize = mBitmap.getHeight() * MAX_SCALE_COE;
        mScaleMinSize = mBitmap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(mOffsetX,mOffsetY);
//        canvas.scale(mCurrScale, mCurrScale); // 默认缩放中心点是左上角
        canvas.scale(mCurrScale, mCurrScale, getWidth() / 2, getHeight() / 2);
        canvas.drawBitmap(mBitmap, mOriginOffsetX, mOriginOffsetY, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();

                mBmpOffsetX = mOffsetX;
                mBmpOffsetY = mOffsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                mOffsetX = mBmpOffsetX + event.getX() - mDownX;
                mOffsetY = mBmpOffsetY + event.getY() - mDownY;
                invalidate();
                break;
            default:
                break;
        }
        return mScaleGestureDetector.onTouchEvent(event);
    }
}
