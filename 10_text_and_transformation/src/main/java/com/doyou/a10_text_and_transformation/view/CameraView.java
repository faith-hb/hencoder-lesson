package com.doyou.a10_text_and_transformation.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.doyou.a10_text_and_transformation.Utils;

import androidx.annotation.Nullable;

public class CameraView extends View {

    public static final int IMAGE_WIDTH = (int) Utils.dp2px(180);
    public static final int IMAGE_PADDING = (int) Utils.dp2px(100);

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap image;
    Camera mCamera = new Camera();
    float topFlip = 0f;
    float bottomFlip = 0f;
    float flipRotation = 0f;

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        image = Utils.getAvatar(getResources(), IMAGE_WIDTH);
        mCamera.setLocation(0, 0, Utils.getZForCamera());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 上半部分
        canvas.save();
        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2, IMAGE_PADDING + IMAGE_WIDTH / 2);
        canvas.rotate(-flipRotation);
        mCamera.save();
        mCamera.rotateX(topFlip);
        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        canvas.clipRect(-IMAGE_WIDTH, -IMAGE_WIDTH, IMAGE_WIDTH, 0);
        canvas.rotate(flipRotation);
        canvas.translate(-(IMAGE_PADDING + IMAGE_WIDTH / 2), -(IMAGE_PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(image, IMAGE_PADDING, IMAGE_PADDING, mPaint);
        canvas.restore();

        // 下半部分
        canvas.save();
        canvas.translate(IMAGE_PADDING + IMAGE_WIDTH / 2, IMAGE_PADDING + IMAGE_WIDTH / 2);
        canvas.rotate(-flipRotation);
        mCamera.save();
        mCamera.rotateX(bottomFlip);
        mCamera.applyToCanvas(canvas);
        mCamera.restore();
        canvas.clipRect(-IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
        canvas.rotate(flipRotation);
        canvas.translate(-(IMAGE_PADDING + IMAGE_WIDTH / 2), -(IMAGE_PADDING + IMAGE_WIDTH / 2));
        canvas.drawBitmap(image, IMAGE_PADDING, IMAGE_PADDING, mPaint);
        canvas.restore();
    }
}
