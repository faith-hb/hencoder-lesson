package com.hencoder.widget.lesson3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoder.R;

/**
 * @autor hongbing
 * @date 2019/4/18
 */
public class FlipboardView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Camera mCamera = new Camera();
    private Bitmap mBitmap;
    private int turnoverDegreeFirst;
    private int turnoverDegreeLast;
    private int degree;

    public FlipboardView(Context context) {
        super(context);
    }

    public FlipboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FlipboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hb);
    }

    public void clearCanvas() {
        degree = 0;
        turnoverDegreeFirst = 0;
        turnoverDegreeLast = 0;
        invalidate();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int bitmapWidth = mBitmap.getWidth();
        int bitmapHeight = mBitmap.getHeight();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int x = centerX - bitmapWidth / 2;
        int y = centerY - bitmapHeight / 2;


        //绘制上半部分 | 或者左半部分
        canvas.save();
        canvas.rotate(-degree, centerX, centerY);
        canvas.clipRect(0, 0, centerX, getHeight());
        canvas.rotate(degree, centerX, centerY);

        //结束，翻折画布
        mCamera.save(); // 保存Camera的状态
        mCamera.rotateX(-turnoverDegreeLast); // 旋转Camera的三维空间
        canvas.translate(centerX, centerY); // 将绘制内容的中心点移动到原点(即旋转的轴心)
        mCamera.applyToCanvas(canvas); // 把旋转投影到Canvas
        canvas.translate(-centerX, -centerY); // 旋转之后把投影移动回去
        mCamera.restore(); // 恢复Camera的状态

        canvas.drawBitmap(mBitmap, x, y, mPaint);
        canvas.restore();

        //绘制右边部分,并沿着中心点逆时针翻转
        canvas.save();
        canvas.rotate(-degree, centerX, centerY);
        canvas.clipRect(centerX, 0, getWidth(), getHeight());

        //起始，翻折画布
        mCamera.save();
        mCamera.rotateY(-turnoverDegreeFirst);
        canvas.translate(centerX, centerY);
        mCamera.applyToCanvas(canvas);
        canvas.translate(-centerX, -centerY);
        mCamera.restore();
        canvas.rotate(degree, centerX, centerY);

        //向画布绘制内容
        canvas.drawBitmap(mBitmap, x, y, mPaint);
        canvas.restore();

    }

    @SuppressWarnings("unused")
    public void setDegree(int degree) {
        this.degree = degree;
        invalidate();
    }

    @SuppressWarnings("unused")
    public void setTurnoverDegreeLast(int turnoverDegreeLast) {
        this.turnoverDegreeLast = turnoverDegreeLast;
        invalidate();
    }

    @SuppressWarnings("unused")
    public void setTurnoverDegreeFirst(int turnoverDegreeFirst) {
        this.turnoverDegreeFirst = turnoverDegreeFirst;
        invalidate();
    }
}
