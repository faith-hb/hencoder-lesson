package com.hencoder.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.Utils;

/**
 * @autor hongbing
 * @date 2019/4/11
 */
public class PieChart extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public static final float RADIUS = Utils.dp2Px(150);
    RectF bounds = new RectF();
    int[] COLORS = {
            Color.rgb(255, 182, 193),
            Color.rgb(255, 105, 180),
            Color.rgb(216, 191, 216),
            Color.rgb(123, 104, 238),
    };
    int[] ANGLES = {60, 160, 80, 60};
//    int[] ANGLES = {60, 100, 120, 80};
    public static final int PULL_INDEX = 1;
    public static final int PULL_LENGTH = 24;

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bounds.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int currAngle = 0;
        for (int i = 0; i < COLORS.length; i++) {
            mPaint.setColor(COLORS[i]);
            if (i == PULL_INDEX) {
                canvas.save();
                canvas.translate((float) Math.cos(Math.toRadians(currAngle + ANGLES[i] / 2)) * PULL_LENGTH,
                        (float) Math.sin(Math.toRadians(currAngle + ANGLES[i] / 2)) * PULL_LENGTH);
            }
            canvas.drawArc(bounds, currAngle, ANGLES[i], true, mPaint);
            if(i == PULL_INDEX){
                canvas.restore();
            }
            currAngle += ANGLES[i];
        }
    }
}
