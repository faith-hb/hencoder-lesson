package com.hencoder.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.Utils;
import com.hencoder.hencoder.R;

/**
 * @autor hongbing
 * @date 2019/4/11
 */
public class AvatarView extends View {

    private static final float WIDTH = Utils.dp2Px(240);
    private float mRaduis = WIDTH / 2;
    private Bitmap avatar;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 显示相交部分
    private Xfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private RectF mRectF = new RectF();

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        avatar = getAvatar((int) WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(getWidth() / 2,getHeight() / 2,mRaduis,mPaint);

//        mPaint.setColor(0);
        mRectF.set(getWidth() / 2 - mRaduis,getHeight() / 2 - mRaduis,getWidth() / 2 + mRaduis,getHeight() / 2 + mRaduis);
        int saved = canvas.saveLayer(mRectF,mPaint);
        // 先绘制蒙版
        canvas.drawCircle(getWidth() / 2,getHeight() / 2,mRaduis - Utils.dp2Px(4),mPaint);
        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(avatar,getWidth() / 2 - avatar.getWidth() / 2,getHeight() / 2 - avatar.getHeight() / 2,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(saved);
    }

    private Bitmap getAvatar(int width){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.hb,options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(),R.drawable.hb,options);
    }
}
