package com.hencoder.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.Utils;

/**
 * @autor hongbing
 * @date 2019/4/11
 */
public class ImageTextView extends View {

    private static final int IMAGE_WIDTH = (int) Utils.dp2Px(140);
    public static final int IMAGE_PADDING = (int) Utils.dp2Px(44);
    private String text = "Save the date! Android Dev Summit is coming to Sunnyvale, CA on Oct 23-24, 2019.The Android App Bundle speaks Duolingos language, reducing its app size by 56%\n" +
            "Since 2011, Duolingo has made language learning fun for millions of people worldwide. Offering free King is a leading interactive entertainment company, with popular mobile games " +
            "such as Candy Crush Saga, Farm Heroes Saga and Bubble Witch 3 Saga. In March 2018, King implemented Google Play Instant and was excited to see the potential impact on removing user " +
            "acquisition friction, targeting audiences more efficiently, and increasing the effectiveness of game cross-promotion.Saga and Bubble Witch 3 Saga. In March 2018, King implemented Google Play Instant and was excited to see the potential impact on removing user" +
            "acquisition friction, targeting audiences more efficiently, and increasing the effectiveness of game cross-promotion";

    TextPaint mTextPaint = new TextPaint();
    Paint mPaint = new Paint();
    float[] measureWidth = new float[1];
    Paint.FontMetrics mMetrics = new Paint.FontMetrics();
    Bitmap mBitmap;

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        mTextPaint.setTextSize(Utils.dp2Px(15));
        mPaint.setTextSize(Utils.dp2Px(16));
        mBitmap = Utils.getAvatar(getResources(),IMAGE_WIDTH);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        StaticLayout

//        int start = 0;
//        int count = mPaint.breakText(text,true,getWidth(),measureWidth);
//        canvas.drawText(text,start,start + count,0,mPaint.getFontSpacing(),mPaint);
//        start = count;
//
//        count = mPaint.breakText(text,start,text.length(),true,getWidth(),measureWidth);
//        canvas.drawText(text,start,start + count,0,mPaint.getFontSpacing() * 2,mPaint);

//        int length = text.length();
//        float yOffset = mPaint.getFontSpacing();
//        for (int start = 0, count = 0; start < length; start += count,yOffset += mPaint.getFontSpacing()) {
//            count = mPaint.breakText(text, start, length, true, getWidth(), measureWidth);
//            canvas.drawText(text, start, start + count, 0, yOffset, mPaint);
//        }

        canvas.drawBitmap(mBitmap,getWidth() - IMAGE_WIDTH,IMAGE_PADDING,mPaint);

        int length = text.length();
        float yOffset = mPaint.getFontSpacing();
        int usableWidth;
        for (int start = 0, count = 0; start < length; start += count,yOffset += mPaint.getFontSpacing()) {
            float textTop = yOffset + mMetrics.top;
            float textBottom = yOffset + mMetrics.bottom;
            if (textTop > IMAGE_PADDING && textTop < IMAGE_PADDING + IMAGE_WIDTH + Utils.dp2Px(12) ||
                    textBottom > IMAGE_PADDING && textBottom < IMAGE_PADDING + IMAGE_WIDTH + Utils.dp2Px(12)) {
                usableWidth = getWidth() - IMAGE_WIDTH;
            }else{
                usableWidth = getWidth();
            }
            count = mPaint.breakText(text, start, length, true, usableWidth, measureWidth);
            canvas.drawText(text, start, start + count, 0, yOffset, mPaint);
        }
    }
}
