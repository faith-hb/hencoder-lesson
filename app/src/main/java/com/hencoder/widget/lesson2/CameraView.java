package com.hencoder.widget.lesson2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.Utils;

/**
 * @autor hongbing
 * @date 2019/4/14
 */
public class CameraView extends View {

    private static final int IMAGE_WIDTH = (int) Utils.dp2Px(120);
    public static final int IMAGE_PADDING = (int) Utils.dp2Px(44);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap mBitmap;
    Camera mCamera = new Camera();

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mBitmap = Utils.getAvatar(getResources(),IMAGE_WIDTH);
        mCamera.rotateX(30);
        mCamera.setLocation(0,0,Utils.getZForCamera());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.save();
//        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2,IMAGE_PADDING + IMAGE_WIDTH / 2);
//        canvas.rotate(-30);
//        canvas.clipRect(-IMAGE_WIDTH,-IMAGE_WIDTH,IMAGE_WIDTH,0);
//        canvas.rotate(30);
//        canvas.translate(-(IMAGE_PADDING + IMAGE_WIDTH / 2),-(IMAGE_PADDING+IMAGE_WIDTH / 2));
//        canvas.drawBitmap(mBitmap,IMAGE_PADDING,IMAGE_PADDING,mPaint);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2,IMAGE_PADDING + IMAGE_WIDTH / 2);
//        canvas.rotate(-30);
//        mCamera.applyToCanvas(canvas);
//        canvas.clipRect(-IMAGE_WIDTH,0,IMAGE_WIDTH,IMAGE_WIDTH);
//        canvas.rotate(30);
//        canvas.translate(-(IMAGE_PADDING + IMAGE_WIDTH / 2),-(IMAGE_PADDING+IMAGE_WIDTH / 2));
//        canvas.drawBitmap(mBitmap,IMAGE_PADDING,IMAGE_PADDING,mPaint);
//        canvas.restore();

        canvas.save();
        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2,IMAGE_PADDING + IMAGE_WIDTH / 2);
        canvas.rotate(-30);
        canvas.clipRect(-IMAGE_WIDTH,-IMAGE_WIDTH,IMAGE_WIDTH,0);
        canvas.rotate(30);
        canvas.translate(-(IMAGE_PADDING + IMAGE_WIDTH / 2),-(IMAGE_PADDING+IMAGE_WIDTH / 2));
        canvas.drawBitmap(mBitmap,IMAGE_PADDING,IMAGE_PADDING,mPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2,IMAGE_PADDING + IMAGE_WIDTH / 2);
        canvas.rotate(-30);
        mCamera.applyToCanvas(canvas);
        canvas.clipRect(-IMAGE_WIDTH,0,IMAGE_WIDTH,IMAGE_WIDTH);
        canvas.rotate(30);
        canvas.translate(-(IMAGE_PADDING + IMAGE_WIDTH / 2),-(IMAGE_PADDING+IMAGE_WIDTH / 2));
        canvas.drawBitmap(mBitmap,IMAGE_PADDING,IMAGE_PADDING,mPaint);
        canvas.restore();
    }
}
