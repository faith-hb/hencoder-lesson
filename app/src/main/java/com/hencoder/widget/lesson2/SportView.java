package com.hencoder.widget.lesson2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.Utils;

/**
 * 圆环+文字居中对齐
 * @autor hongbing
 * @date 2019/4/14
 */
public class SportView extends View {

    private int CIRLE_COLOR = Color.GRAY;
    private static final int HIGHLIGHT_COLOR = Color.parseColor("#ff4084");
    private static final float RING_WIDTH = Utils.dp2Px(12);
    private static final float RADIUS = Utils.dp2Px(120);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Rect mBounds = new Rect();

    public SportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(Utils.dp2Px(44));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制环
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(CIRLE_COLOR);
        mPaint.setStrokeWidth(RING_WIDTH);
        canvas.drawCircle(getWidth() / 2,getHeight() / 2,RADIUS,mPaint);

        // 绘制进度条
        mPaint.setColor(HIGHLIGHT_COLOR);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(getWidth() / 2 - RADIUS,getHeight() / 2 - RADIUS,getWidth() / 2 + RADIUS,getHeight() / 2 + RADIUS,
                0,240,false,mPaint);
        mPaint.setStrokeCap(Paint.Cap.BUTT);

        String text = "abab";
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.getTextBounds(text,0,text.length(),mBounds);

        float offset = (mBounds.top + mBounds.bottom) / 2;
        canvas.drawText(text,getWidth() / 2,getHeight() / 2 - offset,mPaint);
    }
}
