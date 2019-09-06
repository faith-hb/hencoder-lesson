package com.hencoder.widget.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;
import android.os.Build;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 画布功能熟悉测试类
 * @autor hongbing
 * @date 2019-05-07
 */
public class TestCanvasView extends View {

    private Paint mPaint;

    public TestCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(30f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            canvas.clipOutRect(120,120,300,600);
        }
        canvas.clipRect(100,100,350,600, Region.Op.INTERSECT);
        canvas.drawColor(Color.RED);
        canvas.drawCircle(100,100,100,mPaint);




    }
}
