package com.hencoder.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.Utils;

/**
 * @autor hongbing
 * @date 2019/4/11
 */
public class DashBoard extends View {

    private static final float RADIUS = Utils.dp2Px(100);
    private static final float ANGLE = 120;
    private static final int LENGTH = 120;
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path mPath = new Path();
    Path dash = new Path();
    private PathDashPathEffect mPathEffect;
    private PathMeasure mPathMeasure;

    public DashBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(Utils.dp2Px(3));
//        dash.addRect(0, 0, Utils.dp2Px(2), Utils.dp2Px(10), Path.Direction.CCW);
        // 参数3：刻度的厚度  参数4：刻度的长度
        dash.addRect(0, 0, Utils.dp2Px(2), Utils.dp2Px(10), Path.Direction.CCW);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        mPath.addCircle(getWidth() / 2,getHeight() / 2,RADIUS,Path.Direction.CCW);
//        mPath.addRect(getWidth() / 2- RADIUS,getHeight() / 2,getWidth() / 2 + RADIUS,getHeight() / 2 + RADIUS * 2,Path.Direction.CCW);
//        mPath.addCircle(getWidth() / 2,getHeight() / 2,RADIUS * 2,Path.Direction.CCW);
//        mPath.setFillType(Path.FillType.EVEN_ODD);

        mPath.addArc(getWidth() / 2 - RADIUS,getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE);
        mPathMeasure = new PathMeasure(mPath,false);
        // // 参数2 = 刻度之间间距  参数3 = 偏移 ,分割成20个刻度
        mPathEffect = new PathDashPathEffect(dash, (mPathMeasure.getLength() - Utils.dp2Px(2)) / 20, 0, PathDashPathEffect.Style.ROTATE);
        // 第二个参数是刻度之间的间距
//        mPathEffect = new PathDashPathEffect(dash, 100, 0, PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawPath(mPath,mPaint);

        // 画原图形
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE, false, mPaint);

        // 画刻度
        mPaint.setPathEffect(mPathEffect);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE, false, mPaint);

        // 画指针
//        canvas.drawLine(getWidth() / 2, getHeight() / 2,
//                getWidth() / 2 + (float) Math.cos(Math.toRadians(getAngleForMask(5))) * LENGTH,
//                getHeight() / 2 + (float) Math.sin(Math.toRadians(getAngleForMask(5))) * LENGTH,
//                mPaint);
    }

    private float getAngleForMask(int mask){
        return 90 + ANGLE / 2 + (360 - ANGLE) / 20 * mask;
    }
}
